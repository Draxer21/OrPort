#!/usr/bin/env bash
# Prueba de supervivencia de las hormigas viajeras, con un jugador real en el cliente:
#  1. el servidor solo deja dos hormigueros; no hay hormigas preparadas en ninguna dimensión;
#  2. con el jugador al lado, cada hormiguero debe generar su primera hormiga;
#  3. el jugador viaja con esa hormiga y regresa con la que aparece al llegar.
# Los comandos solo aceleran los ticks aleatorios, inmovilizan a la hormiga elegida y orientan la cámara.
set -u
if [ ! -f run/world/level.dat ]; then echo "No hay mundo del servidor; se omite"; exit 1; fi
export DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1
pgrep Xvfb >/dev/null || (Xvfb :99 -screen 0 1280x720x24 >/dev/null 2>&1 &)
sleep 2
mkdir -p shots
pip install --quiet nbtlib
LOG=client_viajes.log
rm -rf run/saves/viajes && cp -r run/world run/saves/viajes
python3 .github/smoke/place_player.py run/saves/viajes
./gradlew runClient -PquickPlay=viajes --no-daemon > $LOG 2>&1 &
pid=$!
joined=0
for i in $(seq 1 90); do
  if grep -qE "joined the game|logged in with entity id" $LOG; then joined=1; break; fi
  kill -0 $pid 2>/dev/null || break
  if [ $((i % 6)) = 0 ] && [ $i -ge 12 ]; then xdotool mousemove 798 429 click 1 2>/dev/null || true; fi
  sleep 5
done
comando() {
  xdotool key t; sleep 1
  xdotool type --delay 30 "$1"; sleep 0.5
  xdotool key Return; sleep 1.5
}
# Ejecuta "execute if entity <selector>" desde el jugador y devuelve el recuento que muestra el chat.
cuenta() {
  local before out
  for intento in 1 2 3; do
    before=$(wc -l < $LOG)
    comando "/execute if entity $1"
    sleep 1
    out=$(tail -n +$((before + 1)) $LOG | grep -oE "Test (passed. Count: [0-9]+|failed)" | tail -1)
    [ -n "$out" ] && break
    sleep 5
  done
  echo "$out" | grep -oE "[0-9]+" || echo 0
}
esperar_chat() {
  local desde=$1 texto=$2
  for espera in 1 2 3 4 5 6 7 8; do
    if tail -n +$((desde+1)) "$LOG" | grep -Fq "$texto"; then return 0; fi
    sleep 1
  done
  return 1
}
fallos=0
check() { if [ "$2" = 1 ]; then echo "[OK ] $1 ${3:-}"; else echo "[FALLO] $1 ${3:-}"; fallos=$((fallos + 1)); fi; }
# Seleccionar una hormiga nacida en el mundo y mantener la misma bajo la mira.
clic_hormiga() {
  local ant=$1 destino=$2 tag=$3 linea intento
  local cerca="@e[type=orespawn:$ant,distance=..48,sort=nearest,limit=1]"
  local sel="@e[type=orespawn:$ant,tag=$tag,limit=1]"
  comando "/tag $cerca add $tag"
  comando "/clear @s"
  comando "/data merge entity $sel {NoAI:1b,NoGravity:1b,Motion:[0d,0d,0d]}"
  # Mantener la cámara fuera del alcance de los escorpiones de la escena inferior.
  comando "/forceload add 80 80"
  comando "/fill 80 209 80 86 209 86 minecraft:stone"
  comando "/fill 80 210 80 86 213 86 minecraft:air"
  comando "/tp $sel 82.5 211.57 84.5"
  comando "/tp @s 82.5 210 82.5 0 0"
  linea=$(wc -l < "$LOG")
  for intento in 1 2 3; do
    comando "/tp $sel 82.5 211.57 84.5"
    comando "/tp @s 82.5 210 82.5 0 0"
    [ "$intento" = 1 ] && import -window root "shots/${tag}_antes.png"
    xdotool click 3
    for i in $(seq 1 12); do
      if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a $destino con $ant"; then
        sleep 5
        local desde=$(wc -l < "$LOG")
        comando "/execute if dimension $destino if entity @s"
        if esperar_chat "$desde" 'Test passed'; then
          import -window root "shots/${tag}_despues.png"
          return 0
        fi
        return 1
      fi
      sleep 2
    done
  done
  return 1
}

viaje() {
  local ant=$1 dim=$2 nx=$3 tag=$4 nacidas vuelta
  comando "/execute in minecraft:overworld run tp @s $nx -60 22 0 30"
  sleep 8
  # Un nido ya colocado puede estar entre dos tandas (hasta 600 ticks).
  for espera in 1 2 3 4 5 6; do
    nacidas=$(cuenta "@e[type=orespawn:$ant,distance=..20]")
    [ "$nacidas" -ge 1 ] && break
    sleep 4
  done
  check "$tag: hormigas del hormiguero a velocidad normal" "$([ "$nacidas" -ge 1 ] && echo 1 || echo 0)" "hormigas=$nacidas"
  [ "$nacidas" -ge 1 ] || return
  if ! clic_hormiga "$ant" "$dim" "${tag}_ida"; then
    check "$tag: ida a $dim" 0; return
  fi
  check "$tag: ida a $dim" 1
  vuelta=$(cuenta "@e[type=orespawn:$ant,distance=..48]")
  check "$tag: hormiga de regreso en $dim" "$([ "$vuelta" -ge 1 ] && echo 1 || echo 0)" "hormigas=$vuelta"
  [ "$vuelta" -ge 1 ] || return
  import -window root "shots/${tag}_llegada.png"
  if clic_hormiga "$ant" minecraft:overworld "${tag}_vuelta"; then
    check "$tag: regreso al Overworld" 1
  else
    check "$tag: regreso al Overworld" 0
  fi
}
# Cliente real, escena aislada: la línea de visión debe atravesar la caja de la mariposa.
apuntar_mariposa() {
  local sel=$1
  comando "/data merge entity $sel {NoAI:1b,NoGravity:1b,Motion:[0d,0d,0d]}"
  comando "/execute at @s run tp $sel ~ ~1.43 ~2"
  comando "/tp @s ~ ~ ~ 0 0"
}
viajar_mariposa() {
  local sel=$1 dim=$2 linea intento
  linea=$(wc -l < "$LOG")
  for intento in 1 2 3; do
    apuntar_mariposa "$sel"
    xdotool click 3
    for i in $(seq 1 12); do
      if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a $dim con butterfly"; then return 0; fi
      sleep 2
    done
  done
  return 1
}
probar_mariposa() {
  local sel linea
  comando "/gamemode survival"
  comando "/effect give @s minecraft:resistance 600 4 true"
  comando "/clear @s"
  comando "/execute in minecraft:overworld run tp @s 82.5 80 82.5"
  comando "/forceload add 80 80"
  comando "/fill 80 79 80 86 79 86 minecraft:stone"
  comando "/tp @s 82.5 80 82.5 0 0"
  comando '/summon orespawn:butterfly 82.5 81.43 84.5 {NoAI:1b,NoGravity:1b,Tags:["chaos_test"]}'
  sel='@e[type=orespawn:butterfly,tag=chaos_test,limit=1]'
  comando "/item replace entity @s weapon.mainhand with minecraft:stick"
  apuntar_mariposa "$sel"
  linea=$(wc -l < "$LOG")
  xdotool click 3; sleep 3
  if tail -n +$((linea+1)) "$LOG" | grep -q "viajó a orespawn:chaos"; then
    check "mariposa: con objeto permanece en el Overworld" 0; return
  fi
  check "mariposa: con objeto permanece en el Overworld" 1
  comando "/clear @s"
  if ! viajar_mariposa "$sel" orespawn:chaos; then check "mariposa: ida a Caos" 0; return; fi
  check "mariposa: ida a Caos" 1
  sleep 6
  sel='@e[type=orespawn:butterfly,distance=..48,sort=nearest,limit=1]'
  if [ "$(cuenta "$sel")" -lt 1 ]; then check "mariposa de regreso cerca de la llegada" 0; return; fi
  check "mariposa de regreso cerca de la llegada" 1
  import -window root "shots/caos_llegada.png"
  # Despejar la línea de visión solo en la escena de prueba, después de comprobar la llegada.
  comando "/fill ~-1 ~ ~ ~1 ~2 ~3 minecraft:air"
  if viajar_mariposa "$sel" minecraft:overworld; then
    check "mariposa: regreso al Overworld" 1
  else
    check "mariposa: regreso al Overworld" 0
  fi
}
if [ $joined = 1 ]; then
  sleep 20
  source .github/smoke/crystal_travel.sh
  probar_cristal
  probar_vacas_cristal
  probar_mariposa
  antes=$(cuenta "@e[type=orespawn:rainbow_ant]"); antes2=$(cuenta "@e[type=orespawn:unstable_ant]")
  # El servidor ya comprobó que no quedó ninguna hormiga preparada; las que haya aquí salieron de los
  # hormigueros con la velocidad de ticks normal mientras el cliente cargaba.
  echo "[INFO] hormigas al entrar, nacidas sin acelerar: arcoíris=$antes inestables=$antes2"
  viaje rainbow_ant orespawn:villages 20.5 arcoiris
  viaje unstable_ant orespawn:danger 230.5 inestable
  comando "/gamemode spectator"
  comando "/execute in orespawn:villages run tp @s 16 110 -20 0 30"
  sleep 35
  import -window root "shots/villages_paisaje.png"
  comando "/execute in orespawn:danger run tp @s 40 -50 -40 0 10"
  sleep 12
  import -window root "shots/danger_paisaje.png"
else
  echo "[FALLO] el cliente no entró al mundo"; fallos=1
fi
grep -E "viajó a|\[CHAT\]|at port\.orespawn|Exception.*orespawn" $LOG | sed -E 's/^.*\]: //' | head -40
pkill -f runClient; kill $pid 2>/dev/null; sleep 5; pkill -f java || true
for f in shots/*_antes.png shots/*_despues.png shots/*_llegada.png shots/*_paisaje.png; do
  [ -e "$f" ] || continue
  convert "$f" -resize 800x -quality 70 "/tmp/$(basename "$f" .png).jpg"
  echo "IMG $(basename "$f" .png) $(base64 -w0 "/tmp/$(basename "$f" .png).jpg")"
done
echo "Fallos de supervivencia: $fallos"
exit $fallos
