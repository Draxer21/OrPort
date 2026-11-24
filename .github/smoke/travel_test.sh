#!/usr/bin/env bash
# Viajes con las hormigas en una sola sesión de cliente: /tp delante de cada hormiga quieta y clic derecho.
set -u
export DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1
pgrep Xvfb >/dev/null || (Xvfb :99 -screen 0 1280x720x24 >/dev/null 2>&1 &)
sleep 2
mkdir -p shots
pip install --quiet nbtlib
name=viajes
rm -rf "run/saves/$name" && cp -r run/world "run/saves/$name"
python3 .github/smoke/place_player.py "run/saves/$name"
./gradlew runClient -PquickPlay="$name" --no-daemon > client_viajes.log 2>&1 &
pid=$!
joined=0
for i in $(seq 1 90); do
  if grep -qE "joined the game|logged in with entity id" client_viajes.log; then joined=1; break; fi
  kill -0 $pid 2>/dev/null || break
  if [ $((i % 6)) = 0 ] && [ $i -ge 12 ]; then xdotool mousemove 798 429 click 1 2>/dev/null || true; fi
  sleep 5
done
comando() {
  xdotool key t; sleep 1
  xdotool type --delay 40 "$1"; sleep 0.5
  xdotool key Return; sleep 1
}
fallos=0
# Hormiga 1,5 bloques al sur; pitch 46,3 apunta a ella desde los ojos del jugador.
viaje() {
  local tag=$1 dim=$2 x=$3 y=$4 expect=$5
  comando "/execute in $dim run tp @s $x $y 20.0 0 46.3"
  sleep 6
  import -window root "shots/${tag}_antes.png"
  local before=$(grep -c "viajó a $expect" client_viajes.log)
  xdotool mousemove 640 360 click 3
  sleep 8
  import -window root "shots/${tag}_despues.png"
  if [ "$(grep -c "viajó a $expect" client_viajes.log)" -gt "$before" ]; then
    echo "[OK ] $tag: $(grep -o "viajó a $expect.*" client_viajes.log | tail -1)"
  else
    echo "[FALLO] $tag: no hubo viaje a $expect"; fallos=$((fallos + 1))
  fi
}
if [ $joined = 1 ]; then
  sleep 20
  viaje arcoiris_ida    minecraft:overworld 20.5 -60 orespawn:villages
  viaje arcoiris_vuelta orespawn:villages   20.5 151 minecraft:overworld
  viaje inestable_ida   minecraft:overworld 30.5 -60 orespawn:danger
  viaje inestable_vuelta orespawn:danger    20.5 -56 minecraft:overworld
  # Vistas de las dos dimensiones en modo espectador.
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
grep -E "viajó a|at port\.orespawn|Exception.*orespawn" client_viajes.log | head -20
pkill -f runClient; kill $pid 2>/dev/null; sleep 5; pkill -f java || true
for f in shots/*_antes.png shots/*_despues.png shots/*_paisaje.png; do
  [ -e "$f" ] || continue
  convert "$f" -resize 800x -quality 70 "/tmp/$(basename "$f" .png).jpg"
  echo "IMG $(basename "$f" .png) $(base64 -w0 "/tmp/$(basename "$f" .png).jpg")"
done
exit $fallos
