"""Prueba de humo del servidor dedicado de NeoForge por RCON.

Se ejecuta en GitHub Actions con el servidor ya arrancado (./gradlew runServer).
Sale con código 1 si falla alguna comprobación obligatoria. Las comprobaciones de IA
dependen del azar, así que se informan sin hacer fallar la prueba.
"""
import re
import socket
import struct
import sys
import time

HOST, PORT, PASSWORD = "127.0.0.1", 25575, "smoke"
Y = -60  # superficie del mundo plano por defecto


class Rcon:
    def __init__(self):
        self.sock = socket.create_connection((HOST, PORT), timeout=30)
        self.req = 0
        self._send(3, PASSWORD)

    def _send(self, kind, body):
        self.req += 1
        data = struct.pack("<ii", self.req, kind) + body.encode() + b"\x00\x00"
        self.sock.sendall(struct.pack("<i", len(data)) + data)
        size = struct.unpack("<i", self._read(4))[0]
        payload = self._read(size)
        return payload[8:-2].decode(errors="replace")

    def _read(self, n):
        out = b""
        while len(out) < n:
            chunk = self.sock.recv(n - len(out))
            if not chunk:
                raise ConnectionError("RCON cerrado")
            out += chunk
        return out

    def cmd(self, command):
        reply = self._send(2, command)
        print(f"> {command}\n  {reply}")
        return reply


results = []


def check(name, ok, detail="", required=True):
    results.append((name, ok, required))
    mark = "OK " if ok else ("FALLO" if required else "AVISO")
    print(f"[{mark}] {name} {detail}")


def count(rc, selector):
    reply = rc.cmd(f"execute if entity {selector}")
    m = re.search(r"count: (\d+)", reply, re.I)
    return int(m.group(1)) if m else 0


def number(reply):
    m = re.search(r"(-?\d+(?:\.\d+)?)", reply.split("is", 1)[-1] if " is " in reply else reply)
    return float(m.group(1)) if m else None


def main():
    rc = Rcon()
    # Sin aparición natural ni radio aleatorio de aparición (nombres de 26.x y anteriores).
    for rule in ("spawn_mobs false", "doMobSpawning false", "respawn_radius 0", "spawn_radius 0", "spawnRadius 0"):
        rc.cmd(f"gamerule {rule}")
    rc.cmd("forceload add -64 -64 64 64")
    rc.cmd("kill @e[type=!player]")
    time.sleep(1)

    # 1. Invocación y estadísticas
    for mob, name in (("scorpion", "Scorpion"), ("emperor_scorpion", "Emperor")):
        reply = rc.cmd(f"summon orespawn:{mob} 0 {Y} {0 if mob == 'scorpion' else 20} {{NoAI:1b,Tags:[\"stat\"]}}")
        check(f"/summon orespawn:{mob}", "Summoned" in reply, reply)
    expected = {"scorpion": (15, 4, 10), "emperor_scorpion": (350, 35, 20)}
    for mob, (hp, atk, arm) in expected.items():
        sel = f"@e[type=orespawn:{mob},tag=stat,limit=1]"
        got = [number(rc.cmd(f"attribute {sel} minecraft:{a} get")) for a in ("max_health", "attack_damage", "armor")]
        check(f"estadísticas de {mob} (vida, ataque, armadura)", got == [hp, atk, arm], f"{got} esperado {[hp, atk, arm]}")
    rc.cmd("kill @e[tag=stat]")
    time.sleep(2)
    rc.cmd("kill @e[type=item]")

    # 2. Objetos registrados
    for item in ("scorpion_egg", "emperor_scorpion_egg", "scorpion_cage", "emperor_scorpion_cage",
                 "emperor_scorpion_scale", "scorpion_ore", "emperor_scorpion_ore"):
        reply = rc.cmd(f"summon item 0 {Y} -20 {{Item:{{id:\"orespawn:{item}\",count:1}},Tags:[\"probe\"]}}")
        check(f"objeto orespawn:{item}", "Summoned" in reply, reply)
    rc.cmd("kill @e[tag=probe]")

    # 3. Botín del emperador: la escama es segura
    rc.cmd(f"summon orespawn:emperor_scorpion 40 {Y} 0 {{NoAI:1b}}")
    time.sleep(1)
    rc.cmd("kill @e[type=orespawn:emperor_scorpion]")
    time.sleep(3)
    scale = count(rc, "@e[type=item,nbt={Item:{id:\"orespawn:emperor_scorpion_scale\"}}]")
    obsidian = count(rc, "@e[type=item,nbt={Item:{id:\"minecraft:obsidian\"}}]")
    frame = count(rc, "@e[type=item,nbt={Item:{id:\"minecraft:item_frame\"}}]")
    beef = count(rc, "@e[type=item,nbt={Item:{id:\"minecraft:beef\"}}]")
    check("botín del emperador: escama", scale >= 1, f"escamas={scale}")
    check("botín del emperador: marco, obsidiana y carne", frame >= 1 and obsidian >= 1 and beef >= 1,
          f"marco={frame} obsidiana={obsidian} carne={beef}")
    rc.cmd("kill @e[type=item]")

    # 4. Botín del común: 10 % por pepita de oro, uranio o titanio, de 0 a 2 unidades
    for i in range(40):
        rc.cmd(f"summon orespawn:scorpion {-40 + (i % 8) * 2} {Y} {-40 + (i // 8) * 2} {{NoAI:1b}}")
    time.sleep(1)
    rc.cmd("kill @e[type=orespawn:scorpion]")
    time.sleep(3)
    nuggets = sum(count(rc, f"@e[type=item,nbt={{Item:{{id:\"{n}\"}}}}]")
                  for n in ("minecraft:gold_nugget", "orespawn:uranium_nugget", "orespawn:titanium_nugget"))
    others = count(rc, "@e[type=item]") - nuggets
    check("botín del común: pepitas al matar 40", nuggets >= 1 and others == 0, f"montones de pepitas={nuggets} otros={others}")
    rc.cmd("kill @e[type=item]")

    # 5. Huevos desde un dispensador
    for mob, z in (("scorpion", 30), ("emperor_scorpion", 40)):
        rc.cmd(f"setblock 30 {Y} {z} dispenser[facing=up]{{Items:[{{Slot:0b,id:\"orespawn:{mob}_egg\",count:1}}]}}")
        rc.cmd(f"setblock 31 {Y} {z} redstone_block")
    time.sleep(2)
    check("huevo de escorpión", count(rc, f"@e[type=orespawn:scorpion,x=30,y={Y},z=30,distance=..8]") >= 1)
    check("huevo de emperador", count(rc, f"@e[type=orespawn:emperor_scorpion,x=30,y={Y},z=40,distance=..16]") >= 1)
    rc.cmd("kill @e[type=!player]")
    rc.cmd(f"fill 30 {Y} 30 31 {Y} 40 air")

    # 6. Jaulas: una llena libera al escorpión y deja una jaula vacía
    for mob, z in (("scorpion", -30), ("emperor_scorpion", -50)):
        rc.cmd(f"summon orespawn:thrown_critter_cage 0 {Y + 6} {z} "
               f"{{Item:{{id:\"orespawn:{mob}_cage\",count:1}},Motion:[0.0,-1.0,0.0]}}")
    time.sleep(3)
    check("jaula llena libera escorpión", count(rc, f"@e[type=orespawn:scorpion,x=0,y={Y},z=-30,distance=..8]") >= 1)
    emperors = count(rc, f"@e[type=orespawn:emperor_scorpion,x=0,y={Y},z=-50,distance=..16]")
    if not emperors:
        rc.cmd("execute as @e[type=orespawn:emperor_scorpion] run data get entity @s Pos")
        rc.cmd("execute as @e[type=item] run data get entity @s Pos")
    check("jaula llena libera emperador", emperors >= 1)
    check("jaula vacía devuelta", count(rc, "@e[type=item,nbt={Item:{id:\"orespawn:empty_cage\"}}]") >= 2)
    rc.cmd("kill @e[type=!player]")

    #    y una vacía que golpea a un escorpión puede capturarlo (40 % por intento)
    for i in range(12):
        x = -60 + i * 4
        rc.cmd(f"summon orespawn:scorpion {x} {Y} 50 {{NoAI:1b}}")
        rc.cmd(f"summon orespawn:thrown_critter_cage {x} {Y + 4} 50 "
               f"{{Item:{{id:\"orespawn:empty_cage\",count:1}},Motion:[0.0,-1.0,0.0]}}")
    time.sleep(3)
    caught = count(rc, "@e[type=item,nbt={Item:{id:\"orespawn:scorpion_cage\"}}]")
    check("jaula vacía captura escorpión", caught >= 1, f"capturas={caught}/12")
    rc.cmd("kill @e[type=!player]")

    # 7. IA (informativo): el común caza una vaca; el emperador invoca escorpiones
    rc.cmd(f"summon orespawn:scorpion 0 {Y} 0")
    rc.cmd(f"summon minecraft:cow 2 {Y} 0 {{NoAI:1b,Tags:[\"prey\"]}}")
    time.sleep(15)
    hurt = rc.cmd("data get entity @e[tag=prey,limit=1] Health")
    alive = count(rc, "@e[tag=prey]")
    check("el común ataca a una vaca cercana", alive == 0 or (number(hurt) or 10) < 10, hurt, required=False)
    rc.cmd("kill @e[type=!player]")
    rc.cmd(f"summon orespawn:emperor_scorpion 0 {Y} 0")
    rc.cmd(f"summon minecraft:villager 6 {Y} 0 {{NoAI:1b,Health:1000f,attributes:[{{id:\"minecraft:max_health\",base:1000d}}],Tags:[\"prey\"]}}")
    time.sleep(30)
    allies = count(rc, "@e[type=orespawn:scorpion]")
    check("el emperador invoca escorpiones", allies >= 1, f"escorpiones={allies}", required=False)
    poisoned = rc.cmd("data get entity @e[tag=prey,limit=1] active_effects")
    check("el emperador puede envenenar", "poison" in poisoned, required=False)
    rc.cmd("kill @e[type=!player]")

    # 8. Hormigas arcoíris e inestable, sus nidos y sus dimensiones
    for mob in ("rainbow_ant", "unstable_ant"):
        rc.cmd(f"summon orespawn:{mob} 0 {Y} -10 {{NoAI:1b,Tags:[\"ant\"]}}")
        got = number(rc.cmd(f"attribute @e[type=orespawn:{mob},limit=1] minecraft:max_health get"))
        check(f"/summon orespawn:{mob} (vida 1)", got == 1, f"vida={got}")
    rc.cmd("kill @e[tag=ant]")
    for item in ("rainbow_ant_egg", "unstable_ant_egg", "rainbow_ant_block", "unstable_ant_block"):
        reply = rc.cmd(f"summon item 0 {Y} -20 {{Item:{{id:\"orespawn:{item}\",count:1}},Tags:[\"probe\"]}}")
        check(f"objeto orespawn:{item}", "Summoned" in reply, reply)
    rc.cmd("kill @e[tag=probe]")
    #    Danger Dimension: plano de lecho de roca, tierra y césped con árboles retorcidos
    rc.cmd("execute in orespawn:danger run forceload add -32 -32 31 31")
    time.sleep(5)
    grass = rc.cmd("execute in orespawn:danger if block 5 -57 5 minecraft:grass_block")
    bedrock = rc.cmd("execute in orespawn:danger if block 5 -64 5 minecraft:bedrock")
    check("Danger Dimension: capas del clásico", "passed" in grass and "passed" in bedrock, f"{grass} / {bedrock}")
    logs = rc.cmd("execute in orespawn:danger run fill -16 -56 -16 15 -36 15 minecraft:stone replace minecraft:oak_log")
    check("Danger Dimension: árboles retorcidos", "successfully" in logs.lower(), logs)
    rc.cmd("execute in orespawn:danger run forceload add 48 48 95 71")
    time.sleep(3)
    ok = 0
    for i in range(3):
        x = 60 + i * 12
        rc.cmd(f"execute in orespawn:danger run fill {x - 4} -57 56 {x + 4} -57 64 minecraft:grass_block")
        rc.cmd(f"execute in orespawn:danger run fill {x - 4} -56 56 {x + 4} -30 64 minecraft:air")
        placed = rc.cmd(f"execute in orespawn:danger run place feature orespawn:scraggly_tree {x} -56 60")
        ok += "placed" in placed.lower() and "fail" not in placed.lower()
        trunk = rc.cmd(f"execute in orespawn:danger if block {x} -56 60 minecraft:oak_log")
        ok += 0 if "passed" in trunk.lower() else -1
    check("árbol retorcido en parcela de césped (3 de 3)", ok == 3, f"colocados={ok}")
    #    Villages: terreno normal y aldeas cada pocos chunks
    found = rc.cmd("execute in orespawn:villages run locate structure orespawn:village")
    m = re.search(r"\((\d+) blocks? away\)", found)
    check("Villages: aldea cercana", m is not None and int(m.group(1)) < 400, found)
    biome = rc.cmd("execute in orespawn:villages run locate biome orespawn:villages")
    check("Villages: bioma propio", "orespawn:villages" in biome and "nearest" in biome.lower(), biome)

    #    Hormigueros para la prueba de supervivencia del cliente; ninguna hormiga preparada.
    rc.cmd("kill @e[type=orespawn:rainbow_ant]")
    rc.cmd("kill @e[type=orespawn:unstable_ant]")
    rc.cmd(f"fill 15 {Y} 15 35 {Y + 4} 30 air")
    rc.cmd(f"setblock 20 {Y - 1} 26 orespawn:rainbow_ant_block")
    rc.cmd("forceload add 208 0 255 47")
    time.sleep(2)
    rc.cmd(f"setblock 230 {Y - 1} 26 orespawn:unstable_ant_block")
    nests = rc.cmd(f"execute if block 20 {Y - 1} 26 orespawn:rainbow_ant_block") + rc.cmd(f"execute if block 230 {Y - 1} 26 orespawn:unstable_ant_block")
    ants = count(rc, "@e[type=orespawn:rainbow_ant]") + count(rc, "@e[type=orespawn:unstable_ant]")
    check("hormigueros colocados y ninguna hormiga preparada", nests.count("passed") == 2 and ants == 0, f"hormigas={ants}")

    # Ambos nidos deben funcionar incluso bajo techo y sin acelerar los ticks.
    for x in (20, 230):
        rc.cmd(f"fill {x - 3} {Y + 3} 23 {x + 3} {Y + 3} 29 minecraft:stone")
    time.sleep(7)
    for mob, x in (("rainbow_ant", 20), ("unstable_ant", 230)):
        emerged = count(rc, f"@e[type=orespawn:{mob},x={x},y={Y},z=26,distance=..16]")
        check(f"{mob} sale del nido colocado", emerged >= 1, f"hormigas={emerged}")
        rc.cmd(f"fill {x - 3} {Y + 3} 23 {x + 3} {Y + 3} 29 air")

    # 9. Los escorpiones ignoran a las tres hormigas, como en el clásico (familia EntityAnt)
    for mob, x in (("scorpion", 50), ("emperor_scorpion", -50)):
        rc.cmd(f"summon orespawn:{mob} {x} {Y} -32")
        for i, ant in enumerate(("red_ant", "rainbow_ant", "unstable_ant")):
            rc.cmd(f"summon orespawn:{ant} {x - 2 + i * 2} {Y} -30 {{NoAI:1b,Tags:[\"bait\"]}}")
    time.sleep(15)
    for mob, x in (("scorpion", 50), ("emperor_scorpion", -50)):
        alive = count(rc, f"@e[tag=bait,x={x},y={Y},z=-30,distance=..4]")
        check(f"{mob} no ataca a las tres hormigas", alive == 3, f"vivas={alive}/3")
    rc.cmd("kill @e[tag=bait]")
    rc.cmd("kill @e[type=orespawn:scorpion]")
    rc.cmd("kill @e[type=orespawn:emperor_scorpion]")

    # 10. Escena para la prueba de cliente (capturas con Xvfb). El jugador aparece en 0,0 mirando al sur.
    rc.cmd("time set noon")
    rc.cmd("weather clear")
    rc.cmd(f"setworldspawn 0 {Y} 0 0 0")
    rc.cmd(f"summon orespawn:scorpion -1 {Y} 3 {{NoAI:1b,PersistenceRequired:1b,Rotation:[150f,0f],Tags:[\"scene\"]}}")
    rc.cmd(f"summon orespawn:scorpion 2 {Y} 5 {{PersistenceRequired:1b,Tags:[\"scene\"]}}")
    rc.cmd(f"summon orespawn:redcow -3.5 {Y} 5 {{NoAI:1b,Invulnerable:1b,PersistenceRequired:1b,Rotation:[120f,0f]}}")
    rc.cmd(f"summon orespawn:emperor_scorpion 2 {Y} 12 {{NoAI:1b,PersistenceRequired:1b,Rotation:[180f,0f],Tags:[\"scene\"]}}")
    time.sleep(2)
    check("escena de cliente preparada", count(rc, "@e[tag=scene]") == 3)
    rc.cmd("kill @e[type=item]")
    # Localizar un bloque generado por el mundo para que el cliente lo recoja en supervivencia.
    crystal = "execute in orespawn:crystal"
    rc.cmd(f"{crystal} run forceload add 32 32 143 143")
    sample = None
    for _ in range(40):
        if "passed" in rc.cmd(f"{crystal} if loaded 143 64 143"):
            break
        time.sleep(1)
    def mark_sample(x, z):
        surface = f"{crystal} positioned {x + 0.5} 80 {z + 0.5} positioned over world_surface"
        if "passed" not in rc.cmd(f"{surface} if block ~ ~-1 ~ orespawn:crystal_grass if block ~ ~-2 ~ orespawn:crystal_stone"):
            return False
        return "Summoned" in rc.cmd(f'{surface} run summon minecraft:marker ~ ~ ~ {{Tags:["crystal_sample"]}}')

    # Una semilla puede situar toda la primera zona bajo el océano.
    sample = next(((x, z) for x in range(36, 144, 8) for z in range(36, 144, 8) if mark_sample(x, z)), None)
    if sample is None:
        for x in (-1024, -512, 512, 1024):
            for z in (-1024, -512, 512, 1024):
                rc.cmd(f"{crystal} run forceload add {x} {z}")
                for _ in range(30):
                    if "passed" in rc.cmd(f"{crystal} if loaded {x} 80 {z}"):
                        break
                    time.sleep(1)
                sample = next(((x+dx, z+dz) for dx in (2, 8, 14) for dz in (2, 8, 14) if mark_sample(x+dx, z+dz)), None)
                if sample:
                    break
                rc.cmd(f"{crystal} run forceload remove {x} {z}")
            if sample:
                break
    check("césped natural sobre piedra de cristal", sample is not None, str(sample))
    rc.cmd("save-all flush")
    time.sleep(3)
    rc.cmd("stop")
    failed = [n for n, ok, req in results if req and not ok]
    print(f"\n{len(results) - len(failed)}/{len(results)} comprobaciones superadas")
    if failed:
        print("Fallos:", *failed, sep="\n - ")
        sys.exit(1)


if __name__ == "__main__":
    main()
