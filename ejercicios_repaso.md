# Taller de Repaso: Estructuras de Datos y Complejidad

Este taller contiene 20 ejercicios de razonamiento, diseño y análisis orientados al enfoque STEAM. Su objetivo no es memorizar código, sino aplicar la lógica de las Estructuras de Datos en la resolución de problemas reales.

---

## Sección 1: Estructuras Lineales (Vectores, Listas, Pilas, Colas)

1. **Gestor de Historial:** Un navegador web tiene las funciones "Atrás" y "Adelante". ¿Qué estructura(s) de datos usarías para implementar ambas funciones simultáneamente y cómo interactuarían al navegar por diferentes páginas?
2. **Atención Bancaria:** Se requiere implementar un sistema de atención por turnos en un banco donde los clientes regulares son atendidos en estricto orden de llegada, pero las mujeres embarazadas y adultos mayores siempre tienen máxima prioridad de atención, independientemente de cuándo llegaron. ¿Qué estructura utilizarías y por qué?
3. **Lista Circular:** Tienes una Lista Doblemente Ligada Circular. Escribe el pseudocódigo genérico o explica paso a paso la lógica para insertar un nuevo nodo exactamente en el medio de la lista. ¿Cuál es la complejidad temporal de esta operación y por qué?
4. **Malabarismo de Estructuras:** Imagina que tienes una Pila `P` con los elementos `[1, 2, 3, 4, 5]` (donde 5 está en el tope). Usando **solamente** operaciones estándar de Pila (push, pop, peek) y una única Cola `C` auxiliar (con enqueue, dequeue), ¿cómo invertirías el orden de los elementos en la Pila para que quede `[5, 4, 3, 2, 1]`?
5. **Vectores vs Listas:** Explica en qué escenario práctico preferirías utilizar un Vector (Array Dinámico) por encima de una Lista Ligada Simple, y en qué escenario preferirías lo opuesto. Describe un ejemplo real de software o sistema para cada caso.

---

## Sección 2: Árboles (BST y AVL)

6. **Reconstrucción de BST:** Dado el siguiente recorrido en preorden `[10, 5, 2, 7, 15, 12, 20]` de un Árbol Binario de Búsqueda (BST) donde los elementos se insertaron en ese exacto orden, dibuja en papel el árbol resultante.
7. **Balanceo de AVL:** Un Árbol AVL es un árbol binario auto-balanceable. Dibuja paso a paso cómo quedaría un árbol AVL tras insertar secuencialmente los números: `30, 20, 10, 40, 50, 25`. Detalla en qué momentos ocurren las rotaciones (simples o dobles) y sobre qué nodos.
8. **Utilidad de los Recorridos:** Explica cómo podrías usar los diferentes recorridos estándar (Preorden, Inorden, Postorden) para: 
   - a) Hacer una copia exacta de un árbol.
   - b) Imprimir los datos ordenados de menor a mayor.
   - c) Eliminar todos los nodos del árbol liberando memoria de forma segura (de las hojas a la raíz).
9. **Altura Máxima:** Se tiene un Árbol Binario (que no necesariamente es de búsqueda). Propón un algoritmo recursivo (en pseudocódigo o texto descriptivo) para encontrar la altura máxima del árbol. ¿Cuál es la complejidad temporal y espacial de tu algoritmo?
10. **Ancestro Común (LCA):** Tienes un Árbol Binario de Búsqueda y necesitas encontrar el Ancestro Común Más Cercano de dos nodos dados `A` y `B`. Describe la lógica de un algoritmo que resuelva esto aprovechando inteligentemente las reglas matemáticas del BST para no tener que buscar en todo el árbol.

---

## Sección 3: Selección de Estructuras (¿Cuándo usar qué?)

11. **Caso Streaming:** Una empresa de streaming de video necesita guardar el registro del segundo exacto en el que un usuario pausó su película para reanudarla después. Tienen millones de usuarios accediendo a través de su `ID_Usuario` constantemente. ¿Qué estructura de datos es la más eficiente para realizar estas lecturas y escrituras velozmente y en O(1)?
12. **Caso Bolsa de Valores:** Se reciben 5 millones de transacciones por segundo en un servidor financiero, pero el motor de bases de datos solo puede procesar 3 millones por segundo. Los excedentes deben ser almacenados temporalmente y procesados en el **mismo orden exacto** en el que llegaron en cuanto haya capacidad. ¿Qué estructura de datos usarías como "buffer" de transacciones?
13. **Caso Undo/Redo:** Quieres implementar el mecanismo "Ctrl+Z" (Deshacer) en un editor de texto. El usuario puede deshacer hasta las últimas 50 acciones realizadas. ¿Qué estructura es la ideal para modelar este comportamiento temporal?
14. **Caso Motor de Búsqueda (Autocompletado):** En un buscador en línea, los usuarios escriben un prefijo (ej. "estruc") y el sistema debe sugerir todas las palabras válidas que comiencen por esas letras ("estructura", "estructural"). Sabiendo que buscar en listas sería muy lento para millones de palabras, ¿qué adaptación de los árboles (o qué estructura jerárquica orientada a texto) diseñarías o usarías para esto?
15. **Caso Videojuegos (IA):** En un videojuego, se debe calcular qué jugador dentro de un mapa de mundo abierto está más cerca de un "bot" enemigo para que este lo ataque. El bot constantemente le pide al motor del juego: "dame el jugador a menor distancia". Sabiendo que las posiciones (y distancias) cambian 60 veces por segundo, ¿qué estructura usarías para que el bot obtenga siempre el menor valor en `O(log n)` o `O(1)` sin tener que re-ordenar un arreglo completo en cada frame?

---

## Sección 4: Complejidad Algorítmica (Big O Notation)

16. **Análisis Cuadrático:** Un algoritmo de emparejamiento recorre una lista de `N` usuarios, y por cada usuario, recorre de nuevo toda la lista para comparar sus perfiles con el resto. ¿Cuál es su complejidad temporal en Big O? Si para `N = 1,000` toma 1 segundo, ¿cuánto tiempo tomaría si `N` crece a `100,000`?
17. **Degradación de Hashes:** Las operaciones de búsqueda en un Hash Table (Diccionario/Mapa) tienen una complejidad temporal promedio de `O(1)`, pero en el peor de los casos pueden degradarse a `O(N)`. Explica matemáticamente o conceptualmente bajo qué circunstancias ocurre este "peor de los casos" (colisiones) y qué mecanismos internos existen para evitarlo.
18. **Comparativa de Búsqueda:** Compara detalladamente la complejidad temporal (peor caso) de buscar un elemento específico en las siguientes tres estructuras: 
   - a) Una Lista Ligada Simple no ordenada. 
   - b) Un Vector (Array Dinámico) previamente ordenado. 
   - c) Un Árbol Binario de Búsqueda (BST) fuertemente desbalanceado (una línea recta).
19. **El Peligro Exponencial:** Tienes un algoritmo recursivo (como el cálculo ingenuo de Fibonacci) cuya complejidad temporal es `O(2^N)`. Si para `N = 10` el algoritmo tarda aproximadamente 1 segundo en ejecutarse, ¿cuánto tardará aproximadamente para `N = 20`? Explica por qué este tipo de complejidad es inadmisible en sistemas de producción a gran escala.
20. **Análisis de Código:** Determina la complejidad temporal total en notación Big O del siguiente fragmento de código. Explica brevemente tu razonamiento (asume que `N` es el tamaño del problema):
   ```java
   for (int i = 1; i <= N; i *= 2) {
       for (int j = 0; j < N; j++) {
           System.out.println(i + " - " + j);
       }
   }
   ```
