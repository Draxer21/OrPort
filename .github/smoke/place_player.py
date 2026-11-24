"""Coloca al jugador de un mundo de un jugador mirando a una hormiga.

Uso: python3 place_player.py <level.dat> <dimensión> <x> <y> <z> <yaw> <pitch>
"""
import sys
import nbtlib
from nbtlib.tag import Compound, Double, Float, Int, List, String

path, dim, x, y, z, yaw, pitch = sys.argv[1:]
level = nbtlib.load(path)
data = level["Data"] if "Data" in level else level[""]["Data"]
data["Player"] = Compound({
    "Pos": List[Double]([Double(float(x)), Double(float(y)), Double(float(z))]),
    "Rotation": List[Float]([Float(float(yaw)), Float(float(pitch))]),
    "Dimension": String(dim),
    "playerGameType": Int(1),
})
level.save()
print("jugador en", dim, x, y, z, yaw, pitch)
