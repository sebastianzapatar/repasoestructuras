<div align="center">
  <img src="https://static.wikia.nocookie.net/vocaloid/images/6/60/Miku_V4X.png/revision/latest" alt="Hatsune Miku" width="200"/>
  
  # Repaso Interactivo de Estructuras de Datos 📚
  *Material de estudio interactivo y colecciones en Java con Genéricos, inspirado en el enfoque STEAM School de la Universidad EIA.*
</div>

---

## 📌 ¿De qué trata este repositorio?

Este repositorio es una herramienta pedagógica diseñada para repasar las estructuras de datos fundamentales antes de entrar a temas avanzados. Se compone de dos partes principales:

1. **Una Presentación Interactiva (HTML/JS)** usando `Reveal.js`.
2. **Código de Ejemplo Completo en Java** utilizando Genéricos (`<T>`), listo para ser compilado y ejecutado.

El enfoque pedagógico **no es** enseñar a construir un arreglo paso a paso desde cero, sino comprender el **"Cuándo usar qué"**, analizando ventajas, desventajas y **Complejidad Algorítmica (Big O)** en escenarios de la vida real (como bases de datos, historiales de navegador o sistemas de alta concurrencia).

---

## 🚀 Contenido de la Presentación (`index.html`)

Al abrir el archivo `index.html` en cualquier navegador web, te encontrarás con unas diapositivas oscuras y modernas (Estilo STEAM EIA) que incluyen:

* **Estructuras Lineales**: Comparativas entre Vectores, Listas, Pilas, Colas y Colas de Prioridad.
* **Diagramas Vectoriales (SVGs)**: Dibujos explicativos de Listas Simples, Circulares, Dobles y Dobles Circulares.
* **Retos tipo LeetCode**: Desafíos de entrevistas técnicas para poner a prueba el conocimiento (Ej. Detectar ciclos en listas).
* **Árboles Binarios e Interactividad**:
  * Implementación interactiva visual de **Árboles Binarios de Búsqueda (BST)**.
  * Implementación interactiva visual de **Árboles AVL** (Auto-balanceo en tiempo real al insertar).
  * Animaciones en vivo de **Recorridos de Árboles** (Preorden, Inorden, Postorden) donde verás cada nodo iluminarse paso a paso.
* **Selección de Estructuras y Big O**: Ejemplos del mundo real (Ej. Búsquedas rápidas vs Inserciones masivas).

---

## 💻 Código Fuente Java (`/presentacion_estructuras`)

Todo el código está diseñado usando **Genéricos de Java (`<T>`)** para mostrar buenas prácticas de programación orientada a objetos y estructuras tipadas.

### Archivos Disponibles:

#### 🔗 Listas Ligadas
* `EjemploListaSimple.java`: Nodos con un solo sentido.
* `EjemploListaCircular.java`: El último nodo vuelve a apuntar a la cabeza.
* `EjemploListaDoble.java`: Iteración bidireccional (`siguiente` y `anterior`).
* `EjemploListaDobleCircular.java`: Máxima flexibilidad, doblemente enlazada en ciclo continuo.

#### 🥞 Pilas y Colas
* `EjemploPilasColas.java`: Demostración usando las clases nativas de Java (`Stack` y `Queue`/`LinkedList`).

#### 🌳 Árboles
* `EjemploArboles.java`: Implementación desde cero de un Árbol Binario de Búsqueda (BST) y sus tres recorridos.
* `EjemploArbolAVL.java`: Implementación completa de un Árbol Auto-Balanceado con sus rotaciones simples y dobles para garantizar `O(log n)`.

#### 🧩 Integración
* `EjemploIntegracion.java`: Un ejercicio avanzado que combina dos estructuras: Recorre un árbol binario y almacena los resultados en una Cola de Prioridad.

---

## ⚙️ ¿Cómo usarlo?

1. **Presentación**: Simplemente haz doble clic en `index.html` para abrirlo en Chrome, Firefox o Edge. Utiliza las flechas de tu teclado para navegar (hay diapositivas horizontales y verticales).
2. **Ejecutar Java**: Puedes compilar y ejecutar cualquier archivo desde la terminal.
   ```bash
   cd presentacion_estructuras
   javac EjemploArbolAVL.java
   java EjemploArbolAVL
   ```

---
*Miku aprueba estudiar Estructuras de Datos. ¡Mucho éxito en el curso de Compiladores!* 🎵
