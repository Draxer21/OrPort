"""Coloca al jugador "Dev" de un mundo de un jugador mirando a una hormiga.

Escribe playerdata/<uuid>.dat con el UUID sin conexión de Dev y, por si acaso, Data.Player en level.dat.
Uso: python3 place_player.py <carpeta del mundo> <dimensión> <x> <y> <z> <yaw> <pitch>
"""
import hashlib
import os
import sys
import uuid
import nbtlib
from nbtlib.tag import Compound, Double, Float, Int, List, String

world, dim, x, y, z, yaw, pitch = sys.argv[1:]
player = Compound({
    "Pos": List[Double]([Double(float(x)), Double(float(y)), Double(float(z))]),
    "Rotation": List[Float]([Float(float(yaw)), Float(float(pitch))]),
    "Dimension": String(dim),
    "playerGameType": Int(1),
})
raw = bytearray(hashlib.md5(b"OfflinePlayer:Dev").digest())
raw[6] = raw[6] & 0x0F | 0x30
raw[8] = raw[8] & 0x3F | 0x80
dev = str(uuid.UUID(bytes=bytes(raw)))
os.makedirs(os.path.join(world, "playerdata"), exist_ok=True)
nbtlib.File(Compound(player), gzipped=True).save(os.path.join(world, "playerdata", dev + ".dat"))
level = nbtlib.load(os.path.join(world, "level.dat"))
level["Data"]["Player"] = Compound(player)
level.save()
print("jugador", dev, "en", dim, x, y, z, yaw, pitch)
