/**
 * Ejemplo 4: Lista Doblemente Ligada Circular (Circular Doubly Linked List) con Genéricos.
 * Máxima flexibilidad: la cabeza y la cola están conectadas en AMBAS direcciones.
 * 
 * Diferencia con la lista doble normal:
 *   - Lista doble:          NULL <- [cabeza] <-> ... <-> [cola] -> NULL
 *   - Lista doble CIRCULAR: [cabeza] <-> ... <-> [cola] <-> [cabeza]  (sin NULLs)
 * 
 * Ventaja: podemos ir de la cabeza a la cola en O(1) con cabeza.anterior,
 * y de la cola a la cabeza en O(1) con cola.siguiente.
 * 
 * Estructura visual:
 *   ┌──────────────────────────────────────────────┐
 *   │                                              ↓
 *   [cabeza] <-> [nodo2] <-> [nodo3] <-> [cola] ──┘
 *   └── ↑                                  ↑ ──────┘
 * 
 * Operaciones:
 *   - insertarInicio       → O(1)
 *   - insertarFinal        → O(1) (cabeza.anterior = cola)
 *   - insertarEnPosicion   → O(n)
 */
public class EjemploListaDobleCircular<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;  // Puntero al nodo que viene después
        Nodo<T> anterior;   // Puntero al nodo que viene antes

        public Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> cabeza;
    private int tamano = 0;

    // ==========================================
    // INSERTAR AL INICIO: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al INICIO de la lista doble circular.
     * 
     * ¿Cómo funciona?
     *   - En una lista doble circular, la cola es cabeza.anterior.
     *   - Para insertar al inicio:
     *     1. Obtenemos la cola (cabeza.anterior)
     *     2. Conectamos: cola <-> nuevo <-> cabeza_antigua
     *     3. Actualizamos cabeza al nuevo nodo
     * 
     * ANTES:  [A] <-> [B] <-> [C] <-> (vuelve a A)
     *          ↑ cabeza
     *          cola = cabeza.anterior = C
     * 
     * insertarInicio("X"):
     *   cola = cabeza.anterior         → cola = C
     *   nuevo.siguiente = cabeza       → X -> A
     *   nuevo.anterior = cola          → C <- X
     *   cabeza.anterior = nuevo        → X <- A
     *   cola.siguiente = nuevo         → C -> X
     *   cabeza = nuevo                 → X es la nueva cabeza
     * 
     * DESPUÉS: [X] <-> [A] <-> [B] <-> [C] <-> (vuelve a X)
     *           ↑ cabeza
     * 
     * Hay que actualizar 4 punteros + la referencia cabeza = 5 asignaciones.
     */
    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cabeza == null) {
            // Lista vacía: el nodo apunta a sí mismo en ambas direcciones
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            // Obtenemos la cola (el nodo anterior a la cabeza)
            Nodo<T> cola = cabeza.anterior;

            // Conectamos el nuevo nodo entre la cola y la cabeza antigua
            nuevo.siguiente = cabeza;    // nuevo -> cabeza_antigua
            nuevo.anterior = cola;       // cola <- nuevo
            cabeza.anterior = nuevo;     // nuevo <- cabeza_antigua
            cola.siguiente = nuevo;      // cola -> nuevo

            // Actualizamos la cabeza
            cabeza = nuevo;
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR AL FINAL: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al FINAL de la lista doble circular.
     * 
     * ¿Cómo funciona?
     *   - La cola en una lista doble circular es: cabeza.anterior
     *   - Insertar al final = insertar ANTES de la cabeza.
     *   - Es similar a insertarInicio pero NO movemos la cabeza.
     * 
     * ANTES:  [A] <-> [B] <-> [C] <-> (vuelve a A)
     *          ↑ cabeza        ↑ cola (= cabeza.anterior)
     * 
     * insertarFinal("X"):
     *   cola = cabeza.anterior         → cola = C
     *   nuevo.siguiente = cabeza       → X -> A
     *   nuevo.anterior = cola          → C <- X
     *   cola.siguiente = nuevo         → C -> X
     *   cabeza.anterior = nuevo        → X <- A
     *   (cabeza NO cambia)
     * 
     * DESPUÉS: [A] <-> [B] <-> [C] <-> [X] <-> (vuelve a A)
     *           ↑ cabeza                ↑ nueva cola
     * 
     * TRUCO: La diferencia con insertarInicio es que aquí NO actualizamos cabeza.
     */
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Nodo<T> cola = cabeza.anterior; // Accesible en O(1)

            // Conectamos el nuevo nodo entre la cola y la cabeza
            cola.siguiente = nuevo;      // cola -> nuevo
            nuevo.anterior = cola;       // cola <- nuevo
            nuevo.siguiente = cabeza;    // nuevo -> cabeza
            cabeza.anterior = nuevo;     // nuevo <- cabeza
            // La cabeza NO cambia (a diferencia de insertarInicio)
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR EN CUALQUIER POSICIÓN: O(n)
    // ==========================================
    /**
     * Inserta un nuevo nodo en la posición indicada (0-indexado).
     * 
     * En una lista doble circular, al insertar necesitamos actualizar
     * 4 punteros igual que en la lista doble normal.
     * 
     * DIFERENCIA CLAVE con la lista doble NO circular:
     *   - No hay NULLs, así que no debemos verificar si un puntero es null.
     *   - La "cola" siempre es cabeza.anterior (no necesitamos variable extra).
     *   - Insertar al final o al inicio son equivalentes a insertar "antes de cabeza"
     *     o "después de cola", y ambos son O(1).
     * 
     * Ejemplo: insertarEnPosicion(2, "X") en [A, B, C, D]
     * 
     * ANTES:  [A] <-> [B] <-> [C] <-> [D] <-> (vuelve a A)
     *          0       1       2       3
     * 
     *   Paso 1: Recorremos hasta posición 1 (nodo B = anterior)
     *   Paso 2: sigDeAnt = anterior.siguiente  (guardamos C)
     *   Paso 3: Conectamos: B <-> X <-> C  (4 punteros)
     * 
     * DESPUÉS: [A] <-> [B] <-> [X] <-> [C] <-> [D] <-> (vuelve a A)
     *           0       1       2       3       4
     */
    public void insertarEnPosicion(int posicion, T valor) {
        if (posicion < 0 || posicion > tamano) {
            System.out.println("⚠ Posición " + posicion + " inválida. "
                    + "Rango válido: [0, " + tamano + "]");
            return;
        }

        if (posicion == 0) {
            insertarInicio(valor);
            return;
        }

        if (posicion == tamano) {
            insertarFinal(valor);
            return;
        }

        // Recorremos hasta el nodo ANTERIOR a la posición deseada
        Nodo<T> nuevo = new Nodo<>(valor);
        Nodo<T> anterior = cabeza;

        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }

        // Guardamos el nodo que está después del anterior
        Nodo<T> sigDeAnt = anterior.siguiente;

        // Enlazamos (4 punteros)
        anterior.siguiente = nuevo;     // anterior -> nuevo
        nuevo.anterior = anterior;      // anterior <- nuevo
        nuevo.siguiente = sigDeAnt;     // nuevo -> sigDeAnt
        sigDeAnt.anterior = nuevo;      // nuevo <- sigDeAnt
        tamano++;
    }

    // ==========================================
    // RECORRIDOS
    // ==========================================

    // Recorrido hacia adelante (usando puntero 'siguiente')
    public void imprimirAdelante() {
        if (cabeza == null) {
            System.out.println("Lista Doble Circular vacía.");
            return;
        }

        System.out.print("Adelante: ");
        Nodo<T> actual = cabeza;
        do {
            System.out.print(actual.valor + " <-> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(Vuelve al inicio)");
    }

    // Recorrido hacia atrás (usando puntero 'anterior')
    public void imprimirAtras() {
        if (cabeza == null) {
            System.out.println("Lista Doble Circular vacía.");
            return;
        }

        System.out.print("Atrás:    ");
        Nodo<T> actual = cabeza.anterior; // Empezamos por la cola
        do {
            System.out.print(actual.valor + " <-> ");
            actual = actual.anterior;
        } while (actual != cabeza.anterior);
        System.out.println("(Vuelve al final)");
    }

    public static void main(String[] args) {
        System.out.println("=== LISTA DOBLEMENTE LIGADA CIRCULAR ===\n");
        EjemploListaDobleCircular<Character> lista = new EjemploListaDobleCircular<>();

        // ── Insertar al final ──
        System.out.println("--- Insertar al FINAL ---");
        lista.insertarFinal('A');
        lista.insertarFinal('B');
        lista.insertarFinal('C');
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // Adelante: A <-> B <-> C <-> (Vuelve al inicio)

        // ── Insertar al inicio ──
        System.out.println("\n--- Insertar al INICIO ---");
        lista.insertarInicio('Z');
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // Adelante: Z <-> A <-> B <-> C <-> (Vuelve al inicio)

        // ── Insertar en posición ──
        System.out.println("\n--- Insertar en POSICIÓN ---");
        lista.insertarEnPosicion(2, 'X');
        lista.imprimirAdelante();
        // Z <-> A <-> X <-> B <-> C <-> (Vuelve al inicio)

        lista.insertarEnPosicion(0, 'W');
        lista.imprimirAdelante();
        // W <-> Z <-> A <-> X <-> B <-> C <-> (Vuelve al inicio)

        lista.insertarEnPosicion(6, 'D');
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // W <-> Z <-> A <-> X <-> B <-> C <-> D <-> (Vuelve al inicio)
        // Ambos recorridos verifican la circularidad en ambas direcciones
    }
}
