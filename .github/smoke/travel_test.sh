#!/usr/bin/env bash
# Viajes con las hormigas: el jugador aparece mirando a una hormiga quieta y hace clic derecho.
set -u
export DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1
pgrep Xvfb >/dev/null || (Xvfb :99 -screen 0 1280x720x24 >/dev/null 2>&1 &)
sleep 2
mkdir -p shots
pip install --quiet nbtlib
# Pitch: hormiga 1,5 bloques al sur, ojos 1,62 por encima de sus pies.
PITCH=46.3
fallos=0
viaje() {
  local name=$1 dim=$2 x=$3 y=$4 z=$5 expect=$6
  rm -rf "run/saves/$name" && cp -r run/world "run/saves/$name"
  python3 .github/smoke/place_player.py "run/saves/$name/level.dat" "$dim" "$x" "$y" "$z" 0 $PITCH
  ./gradlew runClient -PquickPlay="$name" --no-daemon > "client_$name.log" 2>&1 &
  local pid=$! joined=0
  for i in $(seq 1 90); do
    if grep -qE "joined the game|logged in with entity id" "client_$name.log"; then joined=1; break; fi
    kill -0 $pid 2>/dev/null || break
    if [ $((i % 6)) = 0 ] && [ $i -ge 12 ]; then xdotool mousemove 798 429 click 1 2>/dev/null || true; fi
    sleep 5
  done
  grep -m2 -iE "Setting user|uuid" "client_$name.log" || true
  if [ $joined = 1 ]; then
    sleep 20
    import -window root "shots/${name}_antes.png"
    xdotool mousemove 640 360 click 3
    sleep 10
    import -window root "shots/${name}_despues.png"
  fi
  pkill -f runClient; kill $pid 2>/dev/null; sleep 5; pkill -f "fml.startup.Client" || true; sleep 3
  if grep -q "viajó a $expect" "client_$name.log"; then
    echo "[OK ] $name: $(grep -o "viajó a $expect.*" "client_$name.log" | head -1)"
  else
    echo "[FALLO] $name: no hubo viaje a $expect (joined=$joined)"
    grep -E "orespawn|Exception" "client_$name.log" | tail -15
    fallos=$((fallos + 1))
  fi
}
viaje arcoiris_ida   minecraft:overworld 20.5 -60 20.0 orespawn:villages
viaje arcoiris_vuelta orespawn:villages  20.5 151 20.0 minecraft:overworld
viaje inestable_ida  minecraft:overworld 30.5 -60 20.0 orespawn:danger
viaje inestable_vuelta orespawn:danger   20.5 -56 20.0 minecraft:overworld
for f in shots/*_antes.png shots/*_despues.png; do
  [ -e "$f" ] || continue
  convert "$f" -resize 800x -quality 70 "/tmp/$(basename "$f" .png).jpg"
  echo "IMG $(basename "$f" .png) $(base64 -w0 "/tmp/$(basename "$f" .png).jpg")"
done
exit $fallos
