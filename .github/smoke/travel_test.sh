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
fallos=0
check() { if [ "$2" = 1 ]; then echo "[OK ] $1 ${3:-}"; else echo "[FALLO] $1 ${3:-}"; fallos=$((fallos + 1)); fi; }
# Inmoviliza la hormiga más cercana de la especie y coloca al jugador 1,5 bloques al norte, mirándola.
congelar() {
  comando "/clear @s"
  comando "/effect give @e[type=orespawn:$1,distance=..64,sort=nearest,limit=1] minecraft:slowness 600 255 true"
  sleep 4
}
# Lee la posición de la hormiga inmóvil y coloca al jugador 1,5 bloques al norte, mirándola.
apuntar() {
  local sel="@e[type=orespawn:$1,distance=..64,sort=nearest,limit=1]"
  local before=$(wc -l < $LOG)
  comando "/data get entity $sel Pos"
  sleep 1
  local pos=$(tail -n +$((before + 1)) $LOG | grep -oE "\[-?[0-9.]+d, -?[0-9.]+d, -?[0-9.]+d\]" | tail -1 | tr -d '[]d,')
  read -r ax ay az <<< "$pos"
  echo "hormiga en $ax $ay $az"
  comando "/tp @s $ax $ay $(python3 -c "print($az - 1.5)") 0 46.3"
  sleep 4
}
clic() {
  local ant=$1 expect=$2 tag=$3
  local before=$(grep -c "viajó a $expect" $LOG) ok=1
  for intento in 1 2 3 4; do
    apuntar $ant
    [ $intento = 1 ] && import -window root "shots/${tag}_antes.png"
    xdotool mousemove 640 360 click 3
    for i in $(seq 1 5); do
      [ "$(grep -c "viajó a $expect" $LOG)" -gt "$before" ] && { ok=0; break; }
      sleep 2
    done
    [ $ok = 0 ] && { echo "clic aceptado en el intento $intento"; break; }
  done
  # Tiempo para que el cliente cargue la dimensión antes de volver a usar el chat.
  sleep 20
  import -window root "shots/${tag}_despues.png"
  return $ok
}
viaje() {
  local ant=$1 dim=$2 nx=$3 tag=$4
  # Jugador junto al hormiguero (x=$nx, z=26), sin hormigas de esa especie en el mundo.
  comando "/execute in minecraft:overworld run tp @s $nx -60 22 0 30"
  sleep 8
  comando "/gamerule random_tick_speed 1024"
  sleep 6
  comando "/gamerule random_tick_speed 3"
  local nacidas=$(cuenta "@e[type=orespawn:$ant,distance=..20]")
  check "$tag: el hormiguero genera hormigas con el jugador cerca" $([ "$nacidas" -ge 1 ] && echo 1 || echo 0) "hormigas=$nacidas"
  [ "$nacidas" -ge 1 ] || return
  congelar $ant
  clic $ant "$dim" "${tag}_ida"; check "$tag: ida a $dim con una hormiga del hormiguero" $([ $? = 0 ] && echo 1 || echo 0)
  sleep 4
  # En el destino no había ninguna hormiga de esta especie; la de vuelta pudo alejarse mientras cargaba.
  grep -o "Hormiga de vuelta $ant en $dim.*" $LOG | tail -1
  local vuelta=$(cuenta "@e[type=orespawn:$ant,distance=..48]")
  check "$tag: aparece una hormiga de vuelta al llegar" $([ "$vuelta" -ge 1 ] && echo 1 || echo 0) "hormigas=$vuelta"
  [ "$vuelta" -ge 1 ] || return
  import -window root "shots/${tag}_llegada.png"
  congelar $ant
  clic $ant "minecraft:overworld" "${tag}_vuelta"; check "$tag: regreso al mundo principal con esa hormiga" $([ $? = 0 ] && echo 1 || echo 0)
}
if [ $joined = 1 ]; then
  sleep 20
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
