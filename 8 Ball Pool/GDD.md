# Game Design Document (GDD) - 8 Ball Pool
**Autor:** Frank Jamir Urbina Gonzalez

## 1. Concepto Central
> Un juego de visió y calculo, el billar, una bola tiene que chocar a otras con la intención de que entre en la tronera.

## 2. Bucle Principal y Mecánicas (Reglas del Sistema)
*Explica las reglas lógicas sin ambigüedades.*
* **Condición de victoria:** El jugar tiene que introducir todas las bolas correspondiente en las diferentes troneras.
* **Condición de derrota:** El jugador pierde si introduce la bola número ocho antes de tiempo. 
* **Controles:** Lo esencial es el ratón ua que se usara para la trayectoria de la bola blanca y tambien para la fuerza del tiro.
* **Física y Movimiento:** Las bolas se mueven libremente por la mesa (no por casillas) y en una vista desde arriba, por lo que no 
                           les afecta la gravedad. Las bolas se frenan poco a poco por el roce con el tapete hasta detenerse. 
                           Cuando chocan contra los bordes de la mesa, rebotan cambiando de dirección, y si chocan entre ellas, 
                           se empujan transfiriéndose la fuerza del impacto.

## 3. Entidades (Mapeo de Clases)
*Enumera los objetos físicos o lógicos que existen en el juego. Esto será vital para el diagrama de clases en Lenguaje Unificado de Modelado (UML).*
* **Entidad 1 Bola:** Guarda su posición en la mesa, su velocidad actual, su número (o si es la blanca) y si ya entró en una tronera. Puede moverse, frenarse, rebotar y chocar con otras bolas.
* **Entidad 2 Taco:** Guarda el ángulo en el que apunta y la fuerza que el jugador le aplica al mantener presionado el ratón. Puede rotar alrededor de la bola blanca y golpear la bola.
* **Entidad 3 (Mesa / Gestor del Juego):**  Guarda la puntuación, el turno del jugador actual, las posiciones de las troneras y la lista de bolas que quedan en juego. Controla si el jugador ganó, perdió o si el turno debe cambiar

## 4. Máquina de Estados (Flujo de Pantallas)
*¿Por qué fases pasa el programa desde que arranca hasta que se cierra?*
1. **Estado INICIO:**Se ve el título del juego "8 Ball Pool", un fondo decorativo con la mesa y un botón o mensaje de "Hacer clic para jugar" para empezar la partida.
2. **Estado JUGANDO:**La partida está activa. Se muestra la mesa con todas las bolas, el taco y la puntuación actual. El jugador puede apuntar, disparar y ver el movimiento de las bolas en tiempo real.
3. **Estado FIN DEL JUEGO (GAME OVER):** Se detiene la partida y muestra un mensaje indicando quién ganó o si perdiste por meter la bola 8 antes de tiempo. Incluye la puntuación final y un botón para "Reiniciar partida".

## 5. La Instrucción Inicial (Prompt para la Inteligencia Artificial)
*Redacta el párrafo de instrucciones exactas que le darás a la Inteligencia Artificial (IA) en el futuro para que empiece a programar este juego en Java Swing. Sé directo, técnico y utiliza verbos de acción.*
> "Actúa como un desarrollador experto en el lenguaje Java. Créame un juego de [Tu Juego] usando la biblioteca gráfica Java Swing..."