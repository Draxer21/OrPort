# OreSpawn 0.3.3 · Proyecto fuente para Minecraft 26.2
Esta entrega contiene el proyecto editable. La última compilación distribuida es la 0.3.2.

## Compilar en Windows
Con Java 25, abre una terminal en la carpeta proyecto y ejecuta `gradlew.bat build`. Gradle descarga las dependencias la primera vez. El mod compilado se encuentra en `build/libs`. Usa NeoForge 26.2.0.93.

## Escorpiones
El común aparece en grupos y el emperador, solo en desiertos y sabanas. También se pueden invocar con `/summon orespawn:scorpion` y `/summon orespawn:emperor_scorpion`. Sus fósiles se hidratan con agua. Cada uno dispone de huevo y jaula. La escama garantizada del emperador se usa para la guarda de Big Bertha. Su receta actual es una adaptación con los ingredientes ya disponibles.

El emperador regenera dos puntos ocasionalmente, puede invocar escorpiones y su picadura tiene una probabilidad de un tercio de aplicar veneno. El común carece de este efecto. Los modelos proceden del JAR clásico y las animaciones se adaptaron al port. Sigue pendiente probarlos en cliente y servidor con NeoForge.

## Qué probar en supervivencia
- **Basilisco.** Custodia su laberinto en el bioma minero o se invoca hidratando su fósil en la mesa. Su mirada causa fatiga minera VI y su mordedura puede envenenar. Entra a la dimensión minera con una hormiga roja y usa `/locate structure orespawn:basilisk_maze` para localizar la entrada en terreno nuevo.
- **T-Rex.** Da un diente, un marco, siete piezas de carne de vacuno y de dos a cinco pepitas de cada metal. La cantidad de uranio y titanio coincide.
- **Alien, Kyuubi y gusano grande.** El Alien lleva ojos de araña, pedernal, mapa vacío, reloj y brújula. Kyuubi deja carbón y bloques de redstone y cuarzo. El gusano grande suelta diamantes, metales, cuero, carne podrida, tierra, marco y un diente.
- **Mothra y Brutalfly.** Al morir liberan veinte polillas o veinte mariposas respectivamente. Cada uno deja 53 pepitas de oro. Mothra también deja estrella del Nether, varas de blaze, marco y escama.
- **Luciérnaga y chinche.** La luciérnaga brilla y deja la antorcha extrema. La chinche provoca náuseas a los seres vivos cercanos al morir. La termita busca madera y puede multiplicarse cerca de ella. La modificación de madera por termitas y castores respeta la regla de daño de criaturas al terreno.
- **Compañeros.** Para domesticar a Camarasaurus o Velocity Raptor, usa manzanas. Spyro acepta carne de vacuno cruda y Gamma Metroid, lingotes de hierro. Pueden hacer falta varios intentos. Los corazones indican éxito. Con la mano vacía, el dueño alterna entre esperar y seguir. Si están heridos, su alimento los cura. Las jaulas compatibles conservan dueño, orden y salud de una mascota.
- **Cría y ordeño.** Alimenta dos vacas rojas adultas con trigo y usa un balde para ordeñar una adulta. Baryonyx y Camarasaurus se reproducen con manzanas doradas. Dos Velocity Raptor pueden reproducirse con manzanas y dos Gamma Metroid con hierro. Pon a los compañeros en modo seguir.
- **Hormigas rojas.** Clic derecho con la mano principal vacía para viajar entre el mundo principal y la dimensión minera. En la primera llegada se busca suelo seguro y aparece una hormiga de regreso si hace falta.
- **Fósiles.** Cada uno de los cuatro minerales fósiles de gusano admite un balde de agua en la mesa para recuperar el huevo. La receta devuelve el balde vacío según las reglas de fabricación de Minecraft.
- **Equipo.** Las herramientas y la armadura Ultimate recuperan sus encantamientos al entrar sin encantamientos al inventario. El pico de esmeralda recibe Toque de seda. La azada Ultimate prepara e hidrata hasta nueve parcelas y admite un bloque de desnivel. La pala de amatista tiene receta.
- **Doom Worm.** Atraviesa el terreno y sus cien segmentos siguen el recorrido de la cabeza. El atributo de salud del motor llega a 1024. El daño normal se ajusta para que 300 puntos consuman una décima parte de su vida efectiva. La orden de muerte directa conserva su efecto.

## Contenido adaptado a partir de texturas del JAR antiguo
El archivo 1.12.2 contiene imágenes de objetos que esa compilación nunca registró. Para esta edición se añadieron recetas y valores propios para Big Bertha, el arco Ultimate, los equipos de rubí y ojo de tigre y las palomitas. Los materiales se pueden obtener en supervivencia. El libro de recetas desbloquea las fórmulas al encontrar un ingrediente.
 
| Objeto | Cómo se obtiene |
|---|---|
| Hoja de Big Bertha | Dos dientes de T-Rex en el centro superior e inferior. En la fila central, espada Ultimate, diente de gusano y espada Ultimate. |
| Guarda de Big Bertha | Receta sin forma con un lingote de titanio y una escama de polilla, de basilisco y de escorpión emperador. |
| Empuñadura de Big Bertha | Combina un gran martillo, una garra de mantis, una baba verde y un lingote de uranio en cualquier orden. |
| Big Bertha | Hoja, guarda y empuñadura en una columna, de arriba abajo. |
| Arco Ultimate | Arco, hilo, titanio y uranio. Dispara flechas y recibe Poder V e Irrompibilidad III. |
| Rubí y ojo de tigre | Busca sus menas al explorar terreno nuevo. Cada gema tiene bloque de almacenamiento, cinco herramientas y cuatro piezas de armadura. |
| Palomitas | Cocina maíz en un horno. |

## Alcance y verificación
Se incluyeron las 35 criaturas registradas, 35 huevos, 106 recetas y 25 tablas de botín de entidades. Los compañeros con botín condicionado por domesticación lo sueltan mediante su lógica de servidor. Las hormigas y algunos insectos carecían de botín propio en el JAR antiguo. Los dientes de gusano, la garra de mantis y la escama de polilla se conservan como materiales de progresión del port. Las texturas de los pájaros, mariposas y polillas varían entre individuos.
 
En las pruebas de versiones anteriores se construyeron las 35 especies, comprueba los huevos, genera la mazmorra y ejecuta el viaje de ida y vuelta con un jugador simulado. Comprueba muertes y botín de varias especies, enjambres, domesticación, cría de la vaca roja, azada de área, encantamiento de espada, equipo y daño efectivo del Doom Worm. El escenario plano del servidor de pruebas incorpora la definición minera del mod. Una prueba independiente evalúa 24 poses para cada una de las 35 especies y comprueba movimiento y coordenadas finitas. Falta comprobar visualmente en una partida la escala y las transiciones de cada modelo.
 
Varias especies todavía comparten parte de la IA de navegación y combate. Quedan diferencias con los ciclos de emboscada de los gusanos, la selección de presas, la generación de terreno y algunos ataques especiales del original. La jaula conserva el nombre y los datos indicados de mascotas; el estado individual de otras criaturas se recrea al liberarlas.
 
## Código fuente
El directorio proyecto contiene src, la configuración y el wrapper de Gradle. Con JDK 25 puedes ejecutar gradlew.bat build en Windows. El binario distribuido se compiló con javac de JDK 25 contra Minecraft 26.2 y NeoForge 26.2.0.93. El wrapper requiere descargar sus dependencias y su ejecución completa queda sin verificar.


## Mantis en 0.2.1
La mantis es un minijefe volador con barra de jefe, 120 puntos de vida, 10 de armadura y 16 de ataque base. Al derrotarla suelta una garra. La garra sirve para fabricar Big Bertha o como arma de mano principal. Un golpe normal completamente cargado causa 7 puntos contra un objetivo sin armadura y recupera 1 punto de salud del portador herido. El valor de 7 es un ajuste de esta edición.

## Combate clásico en 0.2.2
El T-Rex tiene 160 de vida, 22 de ataque y 14 de armadura. Mothra tiene 150 de vida, 12 de ataque y 8 de armadura. En Fácil lanza bolas pequeñas, en Normal elige al azar entre pequeñas y explosivas y en Difícil lanza explosivas. Recupera un punto de salud por disparo. El proyectil explosivo usa la implementación de Minecraft 26.2.



## Criaturas nuevas en 0.3.0
En supervivencia, el escarabajo Hércules y el Triffid aparecen como encuentros poco frecuentes en los bosques y selvas definidos por el port. Para probarlos con comandos usa /summon orespawn:hercules_beetle y /summon orespawn:triffid.

El Hércules tiene 250 puntos de vida, 30 de ataque y 19 de armadura. Su ataque levanta al objetivo. Suelta el gran martillo, un marco, de cuatro a once piezas de carne y puede soltar diamantes o equipo de diamante. Su equipo de diamante admite los sorteos de encantamientos del clásico. El martillo tiene 9000 de durabilidad e impulsa hacia arriba al enemigo golpeado.

El Triffid tiene 100 puntos de vida, 20 de ataque y 12 de armadura. Se abre para atacar. Al recibir un golpe se cierra durante 300 ticks y los golpes mientras está cerrado reinician la espera. Deja de cuatro a nueve unidades de baba verde y un marco. Para esta versión se adaptó como emboscador inmóvil.

Ambas criaturas incluyen su modelo y textura clásicos, pose animada y huevo. ENTIDADES.md contiene la comparación de registros. Las ubicaciones de aparición y la empuñadura de Big Bertha son adaptaciones temporales mientras avanzan las dimensiones y las demás criaturas.

## Laberinto del basilisco en 0.3.2

La pirámide de arenisca marca la entrada. Hay tres trazados obtenidos del generador clásico. Cada uno contiene tres basiliscos persistentes y tres cofres. La frecuencia y ubicación en la dimensión minera son adaptaciones. El fósil sigue disponible.

Los cofres sortean de cinco a diez recompensas. Veintitrés de las 31 entradas del clásico están disponibles. Los ocho resultados correspondientes a objetos pendientes dejan el sorteo vacío. La obsidiana ocupa el lugar de los bloques de teletransporte aleatorio. El equipo de esmeralda del basilisco y el de diamante del escarabajo Hércules recuperan sus sorteos independientes de encantamiento.

Se comprobó la conectividad del espacio interior de las tres plantillas, la ubicación de cofres y guardianes, las tablas y la integridad del paquete. La generación natural y la partida gráfica de esta versión quedan por comprobar en Minecraft 26.2.
