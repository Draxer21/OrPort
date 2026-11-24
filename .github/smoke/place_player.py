"""Activa los comandos en un mundo de un jugador para que la prueba pueda usar /tp desde el chat.

Uso: python3 place_player.py <carpeta del mundo>
"""
import os
import sys
import nbtlib
from nbtlib.tag import Byte

level = nbtlib.load(os.path.join(sys.argv[1], "level.dat"))
level["Data"]["allowCommands"] = Byte(1)
level.save()
print("comandos activados en", sys.argv[1])
