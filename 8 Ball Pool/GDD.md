# Game Design Document (GDD) - [Nombre de tu Juego]
**Autor:** Frank Jamir Urbina Gonzalez

## 1. Concepto Central
> Un juego de visió y calculo, el billar, una bola tiene que chocar a otras con la intención de que entre en la tronera.

## 2. Bucle Principal y Mecánicas (Reglas del Sistema)
*Explica las reglas lógicas sin ambigüedades.*
* **Condición de victoria:** El jugar tiene que introducir todas las bolas correspondiente en las diferentes troneras.
* **Condición de derrota:** [¿Cómo muere o pierde el jugador?]
* **Controles:** [¿Qué teclas se usan y qué hacen exactamente?]
* **Física y Movimiento:** [¿Se mueve por casillas? ¿Rebota en los bordes? ¿La gravedad le afecta?]

## 3. Entidades (Mapeo de Clases)
*Enumera los objetos físicos o lógicos que existen en el juego. Esto será vital para el diagrama de clases en Lenguaje Unificado de Modelado (UML).*
* **Entidad 1 (Ejemplo: Jugador):** [Qué datos guarda y qué puede hacer]
* **Entidad 2 (Ejemplo: Enemigo u Obstáculo):** [Qué datos guarda y qué puede hacer]
* **Entidad 3 (Ejemplo: Tablero o Gestor):** [Controla los puntos y el estado general]

## 4. Máquina de Estados (Flujo de Pantallas)
*¿Por qué fases pasa el programa desde que arranca hasta que se cierra?*
1. **Estado INICIO:** [Qué se ve en pantalla. Ejemplo: "Título y mensaje de 'Pulsa Espacio para empezar'"]
2. **Estado JUGANDO:** [El bucle principal activo]
3. **Estado FIN DEL JUEGO (GAME OVER):** [Mensaje final, puntuación, y opción de 'Pulsa R para reiniciar']

## 5. La Instrucción Inicial (Prompt para la Inteligencia Artificial)
*Redacta el párrafo de instrucciones exactas que le darás a la Inteligencia Artificial (IA) en el futuro para que empiece a programar este juego en Java Swing. Sé directo, técnico y utiliza verbos de acción.*
> "Actúa como un desarrollador experto en el lenguaje Java. Créame un juego de [Tu Juego] usando la biblioteca gráfica Java Swing..."