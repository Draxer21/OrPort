# Se ejecuta desde travel_test.sh con el cliente de Minecraft abierto.
apuntar_termita() {
  local sel=$1
  comando "/data merge entity $sel {NoAI:1b,NoGravity:1b,Motion:[0d,0d,0d]}"
  comando "/tp $sel ~ ~1.53 ~2"
  comando "/tp @s ~ ~ ~ 0 0"
}

probar_cristal() {
  local sel linea ranura objeto bloqueado i
  comando "/gamemode survival"
  comando "/effect give @s minecraft:resistance 600 4 true"
  comando "/clear @s"
  xdotool key 1
  comando "/forceload add 80 80"
  comando "/fill 80 79 80 86 79 86 minecraft:stone"
  comando "/tp @s 82.5 80 82.5 0 0"
  comando "/setblock 84 79 82 orespawn:termite_nest"
  sleep 8
  sel='@e[type=orespawn:termite,distance=..16,sort=nearest,limit=1]'
  if [ "$(cuenta "$sel")" -lt 1 ]; then check "sale una termita del termitero" 0; return; fi
  check "sale una termita del termitero" 1
  comando "/tag $sel add crystal_test"
  sel='@e[type=orespawn:termite,tag=crystal_test,limit=1]'
  # Validar el mensaje de rechazo y el objeto en la misma ranura después del clic.
  for ranura in inventory.7 armor.head weapon.offhand; do
    objeto=minecraft:diamond
    [ "$ranura" = armor.head ] && objeto=minecraft:iron_helmet
    comando "/item replace entity @s $ranura with $objeto"
    apuntar_termita "$sel"
    linea=$(wc -l < "$LOG")
    xdotool click 3
    sleep 2
    bloqueado=0
    if tail -n +$((linea+1)) "$LOG" | grep -q "Empty your inventory"; then
      linea=$(wc -l < "$LOG")
      comando "/execute if dimension minecraft:overworld if items entity @s $ranura $objeto"
      if tail -n +$((linea+1)) "$LOG" | grep -q 'Test passed'; then bloqueado=1; fi
    fi
    check "Cristal rechaza y conserva $ranura" "$bloqueado"
    comando "/item replace entity @s $ranura with minecraft:air"
  done
  apuntar_termita "$sel"
  linea=$(wc -l < "$LOG")
  xdotool click 3
  for i in $(seq 1 30); do
    if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a orespawn:crystal con termite"; then break; fi
    sleep 2
  done
  if ! tail -n +$((linea+1)) "$LOG" | grep -q "viajó a orespawn:crystal con termite"; then
    check "entrada a Cristal con inventario vacío" 0; return
  fi
  check "entrada a Cristal con inventario vacío" 1
  sleep 5
  sel='@e[type=orespawn:termite,distance=..32,sort=nearest,limit=1]'
  if [ "$(cuenta "$sel")" -lt 1 ]; then check "termita de regreso" 0; return; fi
  check "termita de regreso" 1
  comando "/tag $sel add crystal_return"
  import -window root "shots/cristal_llegada.png"
  comando '/execute at @e[type=minecraft:marker,tag=crystal_sample,limit=1] run tp @s ~ ~ ~ 0 90'
  sleep 2
  xdotool mousedown 1; sleep 2; xdotool mouseup 1
  sleep 2
  local muestra='@s[nbt={Inventory:[{id:"orespawn:crystal_grass"}]}]'
  check "recoge césped de cristal con la mano" "$([ "$(cuenta "$muestra")" -gt 0 ] && echo 1 || echo 0)"
  # Pasar el bloque recogido a la mano secundaria y comprobar que la principal queda libre.
  xdotool key 1; sleep 1; xdotool key f; sleep 1
  local desde=$(wc -l < "$LOG")
  comando "/execute unless items entity @s weapon.mainhand * if items entity @s weapon.offhand orespawn:crystal_grass"
  if ! tail -n +$((desde+1)) "$LOG" | grep -q 'Test passed'; then
    check "mano vacía y botín conservado antes de regresar" 0; return
  fi
  # Evitar laderas, agua y criaturas que tapen el clic. Solo se prepara la cámara en Cristal.
  comando "/fill 80 209 80 86 209 86 orespawn:crystal_stone"
  comando "/fill 80 210 80 86 213 86 minecraft:air"
  comando "/tp @s 82.5 210 82.5 0 0"
  sel='@e[type=orespawn:termite,tag=crystal_return,limit=1]'
  linea=$(wc -l < "$LOG")
  for intento in 1 2 3; do
    apuntar_termita "$sel"
    xdotool click 3
    for i in $(seq 1 12); do
      if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a minecraft:overworld con termite"; then break 2; fi
      sleep 2
    done
  done
  if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a minecraft:overworld con termite"; then
    check "regreso de Cristal con el bloque recogido" "$([ "$(cuenta "$muestra")" -gt 0 ] && echo 1 || echo 0)"
  else
    check "regreso de Cristal con el bloque recogido" 0
  fi
}

probar_vacas_cristal() {
  comando "/execute in orespawn:crystal run fill 79 219 79 88 219 88 orespawn:crystal_grass"
  comando "/execute in orespawn:crystal run tp @s 82.5 220 82.5 0 20"
  comando "/gamemode survival"
  xdotool key 1
  for x in 82.5 84.5; do
    comando "/summon orespawn:crystal_cow $x 220 84.5 {NoAI:1b,Tags:[\"familia\"]}"
  done
  comando "/item replace entity @s weapon.mainhand with minecraft:bucket"
  xdotool click 3; sleep 2
  check "la vaca de cristal adulta da leche" "$([ "$(cuenta '@s[nbt={Inventory:[{id:"minecraft:milk_bucket"}]}]')" -gt 0 ] && echo 1 || echo 0)"
  comando "/item replace entity @s weapon.mainhand with minecraft:wheat 2"
  for x in 82.5 84.5; do
    comando "/tp @s $x 220 82.5 0 20"
    xdotool click 3; sleep 2
  done
  local cria='@e[type=orespawn:crystal_cow,tag=!familia,y=220,x=83,z=84,distance=..5]'
  check "dos vacas de cristal alimentadas con trigo producen una cría" "$([ "$(cuenta "$cria")" -eq 1 ] && echo 1 || echo 0)"
  import -window root "shots/familia_cristal_llegada.png"
  comando "/execute in minecraft:overworld run tp @s 82.5 80 82.5"
}
