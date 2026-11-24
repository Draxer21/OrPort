#!/usr/bin/env bash
# Abre el mundo preparado por server_test.py en el cliente y toma capturas de los escorpiones.
set -u
mkdir -p run/saves shots
rm -rf run/saves/smoke && cp -r run/world run/saves/smoke
cp .github/smoke/options.txt run/options.txt
Xvfb :99 -screen 0 1280x720x24 >/dev/null 2>&1 &
export DISPLAY=:99 LIBGL_ALWAYS_SOFTWARE=1
./gradlew runClient -PquickPlay=smoke --no-daemon > client.log 2>&1 &
pid=$!
joined=0
for i in $(seq 1 180); do
  if grep -qE "joined the game|Started 0? ?serving on|logged in with entity id" client.log; then joined=1; break; fi
  if ! kill -0 $pid 2>/dev/null; then break; fi
  sleep 5
done
if [ $joined = 1 ]; then
  sleep 25
  for n in 1 2 3 4 5 6; do import -window root "shots/escena_$n.png"; sleep 1.5; done
  # Vista de cerca del común: girar la cámara hacia abajo con el ratón.
  xdotool mousemove 640 360 mousemove_relative --sync 0 180 2>/dev/null || true
  sleep 2
  for n in 1 2 3; do import -window root "shots/cerca_$n.png"; sleep 1; done
fi
pkill -f "runClient" || true
kill $pid 2>/dev/null || true
sleep 5
pkill -f java || true
grep -nE "Exception|ERROR|orespawn.*(WARN|ERROR)" client.log | grep -viE "realms|telemetry|narrator|openal|sound|Couldn't connect" | head -60 > shots/errores_cliente.txt || true
cp client.log shots/client.log
if [ $joined != 1 ]; then echo "El cliente no llegó a entrar al mundo"; tail -80 client.log; exit 1; fi
if grep -qE "Exception.*orespawn|at port\.orespawn" client.log; then echo "Excepciones del mod en el cliente"; grep -nE -A5 "at port\.orespawn" client.log | head -60; exit 1; fi
echo "Capturas tomadas"
