"""Busca nidos en chunks generados por el servidor en un Overworld normal y nuevo."""
import io
import time
import zlib
from pathlib import Path

import nbtlib
from server_test import Rcon

WORLD = Path("run/natural-nests")
BLOCKS = {"orespawn:rainbow_ant_block", "orespawn:unstable_ant_block"}


def chunk_nbt(folder, cx, cz):
    path = folder / f"r.{cx // 32}.{cz // 32}.mca"
    if not path.is_file():
        return None
    with path.open("rb") as region:
        region.seek(4 * ((cx % 32) + 32 * (cz % 32)))
        entry = int.from_bytes(region.read(4), "big")
        if not entry:
            return None
        region.seek((entry >> 8) * 4096)
        size = int.from_bytes(region.read(4), "big")
        compression = region.read(1)
        if compression != b"\x02":
            raise ValueError(f"Compresión inesperada en {path}: {compression!r}")
        raw = zlib.decompress(region.read(size - 1))
    # Evita interpretar NBT completo en los chunks sin ninguno de los dos IDs.
    if not any(name.encode() in raw for name in BLOCKS):
        return None
    return nbtlib.File.parse(io.BytesIO(raw))


def nests(chunk):
    for section in chunk["sections"]:
        states = section.get("block_states")
        if not states:
            continue
        palette = [str(state["Name"]) for state in states["palette"]]
        targets = {i for i, name in enumerate(palette) if name in BLOCKS}
        if not targets:
            continue
        bits = max(4, (len(palette) - 1).bit_length())
        per_long = 64 // bits
        words = states.get("data")
        for i in range(4096):
            idx = 0 if len(palette) == 1 else (int(words[i // per_long]) >> ((i % per_long) * bits)) & ((1 << bits) - 1)
            if idx in targets:
                yield palette[idx], int(chunk["xPos"]) * 16 + i % 16, int(section["Y"]) * 16 + i // 256, int(chunk["zPos"]) * 16 + (i // 16) % 16


def main():
    rc = Rcon()
    found = set()
    # Ambas ubicaciones existen en distintas versiones del formato de mundos.
    roots = [WORLD / "dimensions/minecraft/overworld/region", WORLD / "region"]
    try:
        for batch in range(16):
            cx, cz = 16 * (batch % 4), 16 * (batch // 4)
            x, z = cx * 16, cz * 16
            bounds = f"{x} {z} {x + 255} {z + 255}"
            rc.cmd("forceload add " + bounds)
            try:
                # Espera a que las 256 columnas estén completas antes de leer el guardado.
                pending = {(i, j) for i in range(cx, cx + 16) for j in range(cz, cz + 16)}
                deadline = time.monotonic() + 150
                while pending:
                    pending = {(i, j) for i, j in pending if "passed" not in rc._send(2, f"execute if loaded {i*16} 64 {j*16}")}
                    if pending and time.monotonic() > deadline:
                        raise RuntimeError(f"{len(pending)} chunks sin cargar en {bounds}")
                    if pending:
                        time.sleep(1)
                rc.cmd("save-all flush")
                root = next(path for path in roots if (path / f"r.{cx//32}.{cz//32}.mca").is_file())
                for i in range(cx, cx + 16):
                    for j in range(cz, cz + 16):
                        chunk = chunk_nbt(root, i, j)
                        if chunk is None:
                            continue
                        for name, px, py, pz in nests(chunk):
                            if name in found:
                                continue
                            reply = rc.cmd(f"execute if block {px} {py} {pz} {name}")
                            if "passed" not in reply:
                                raise RuntimeError(f"El servidor no confirmó {name} en {px} {py} {pz}")
                            found.add(name)
                            print(f"NIDO NATURAL: {name} en {px} {py} {pz}", flush=True)
                        if found == BLOCKS:
                            return
            finally:
                rc.cmd("forceload remove " + bounds)
        raise RuntimeError(f"Sin encontrar después de 4096 chunks: {sorted(BLOCKS-found)}")
    finally:
        rc.cmd("stop")


if __name__ == "__main__":
    main()
