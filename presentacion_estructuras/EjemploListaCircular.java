/**
 * Ejemplo 2: Lista Ligada Circular (Circular Singly Linked List) con Genéricos.
 * El último nodo apunta de regreso a la cabeza (no hay NULL al final).
 * Muy útil para algoritmos de planificación (Round-Robin) o buffers circulares.
 * 
 * TRUCO CLAVE: Mantenemos referencia a la COLA (último nodo).
 * La CABEZA siempre es cola.siguiente. Esto permite:
 *   - insertarInicio  → O(1)
 *   - insertarFinal   → O(1)
 *   - insertarEnPosicion → O(n)
 * 
 * Estructura visual:
 *   cola.siguiente = cabeza
 *   [cabeza] -> [nodo2] -> [nodo3] -> [cola] --┐
 *      ↑                                        |
 *      └────────────────────────────────────────┘
 */
public class EjemploListaCircular<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        public Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    // Mantenemos referencia a la COLA en vez de la cabeza.
    // La cabeza siempre es cola.siguiente.
    private Nodo<T> cola;
    private int tamano = 0;

    // ==========================================
    // INSERTAR AL INICIO: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al INICIO de la lista circular.
     * 
     * ¿Cómo funciona?
     *   - La cabeza en una lista circular con puntero a cola es: cola.siguiente
     *   - Para insertar al inicio:
     *     1. El nuevo nodo apunta a la cabeza actual (cola.siguiente)
     *     2. La cola apunta al nuevo nodo (que ahora es la nueva cabeza)
     *   - La cola NO cambia, solo cambia a quién apunta la cola.
     * 
     * ANTES:  [10] -> [20] -> [30] --→ (vuelve a 10)
     *          ↑ cabeza        ↑ cola
     * 
     * insertarInicio(5):
     *   nuevo.siguiente = cola.siguiente  →  5 apunta a 10
     *   cola.siguiente = nuevo            →  30 apunta a 5 (nueva cabeza)
     * 
     * DESPUÉS: [5] -> [10] -> [20] -> [30] --→ (vuelve a 5)
     *           ↑ cabeza               ↑ cola
     */
    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cola == null) {
            // Lista vacía: el nodo apunta a sí mismo (circular de un solo nodo)
            cola = nuevo;
            cola.siguiente = cola;
        } else {
            // El nuevo apunta a la cabeza actual
            nuevo.siguiente = cola.siguiente;
            // La cola ahora apunta al nuevo (nueva cabeza)
            cola.siguiente = nuevo;
            // NOTA: la cola NO cambia, solo cambia quién es la cabeza
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR AL FINAL: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al FINAL de la lista circular.
     * 
     * ¿Cómo funciona?
     *   - Insertar al final es casi idéntico a insertar al inicio,
     *     pero ADEMÁS movemos la cola al nuevo nodo.
     *   - Truco: insertamos al inicio y luego movemos la cola una posición adelante.
     * 
     * ANTES:  [10] -> [20] -> [30] --→ (vuelve a 10)
     *          ↑ cabeza        ↑ cola
     * 
     * insertarFinal(40):
     *   1. nuevo.siguiente = cola.siguiente   → 40 apunta a 10 (cabeza)
     *   2. cola.siguiente = nuevo             → 30 apunta a 40
     *   3. cola = nuevo                       → 40 es la nueva cola
     * 
     * DESPUÉS: [10] -> [20] -> [30] -> [40] --→ (vuelve a 10)
     *           ↑ cabeza                ↑ cola
     */
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cola == null) {
            cola = nuevo;
            cola.siguiente = cola;
        } else {
            nuevo.siguiente = cola.siguiente; // Apunta a la cabeza actual
            cola.siguiente = nuevo;           // La antigua cola apunta al nuevo
            cola = nuevo;                     // Actualizamos la cola al nuevo nodo
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR EN CUALQUIER POSICIÓN: O(n)
    // ==========================================
    /**
     * Inserta un nuevo nodo en la posición indicada (0-indexado).
     * 
     * ¿Cómo funciona?
     *   - Posición 0: insertarInicio.
     *   - Posición == tamaño: insertarFinal.
     *   - Otra posición: recorremos desde la cabeza (cola.siguiente) hasta
     *     el nodo ANTERIOR a la posición deseada y ajustamos punteros.
     * 
     * IMPORTANTE en listas circulares:
     *   - No hay NULL, así que no podemos usar "while (actual != null)".
     *   - Usamos un contador para saber cuándo parar.
     *   - Si insertamos en la última posición, debemos actualizar la cola.
     * 
     * Ejemplo: insertarEnPosicion(2, "X") en [10, 20, 30, 40]
     * 
     * ANTES:  [10] -> [20] -> [30] -> [40] --→ (vuelve a 10)
     *           0       1       2       3
     * 
     *   Paso 1: Recorremos hasta posición 1 (nodo 20 = anterior)
     *   Paso 2: nuevo.siguiente = anterior.siguiente  (X apunta a 30)
     *   Paso 3: anterior.siguiente = nuevo            (20 apunta a X)
     * 
     * DESPUÉS: [10] -> [20] -> [X] -> [30] -> [40] --→ (vuelve a 10)
     *            0       1      2      3       4
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

        // Recorremos desde la cabeza hasta el nodo ANTERIOR a la posición
        Nodo<T> nuevo = new Nodo<>(valor);
        Nodo<T> anterior = cola.siguiente; // Empezamos en la cabeza

        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }

        // Insertamos entre 'anterior' y 'anterior.siguiente'
        nuevo.siguiente = anterior.siguiente;
        anterior.siguiente = nuevo;
        tamano++;
    }

    // ==========================================
    // IMPRIMIR
    // ==========================================
    public void imprimir() {
        if (cola == null) {
            System.out.println("Lista Circular vacía.");
            return;
        }
        
        System.out.print("Lista Circular: ");
        Nodo<T> actual = cola.siguiente; // Empezamos en la cabeza
        do {
            System.out.print(actual.valor + " -> ");
            actual = actual.siguiente;
        } while (actual != cola.siguiente);
        System.out.println("(Vuelve al inicio)");
    }

    public static void main(String[] args) {
        System.out.println("=== LISTA CIRCULAR ===\n");
        EjemploListaCircular<Integer> lista = new EjemploListaCircular<>();

        // ── Insertar al final ──
        System.out.println("--- Insertar al FINAL ---");
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.imprimir();
        // 10 -> 20 -> 30 -> (Vuelve al inicio)

        // ── Insertar al inicio ──
        System.out.println("\n--- Insertar al INICIO ---");
        lista.insertarInicio(5);
        lista.imprimir();
        // 5 -> 10 -> 20 -> 30 -> (Vuelve al inicio)

        // ── Insertar en posición ──
        System.out.println("\n--- Insertar en POSICIÓN ---");
        lista.insertarEnPosicion(2, 15);
        lista.imprimir();
        // 5 -> 10 -> 15 -> 20 -> 30 -> (Vuelve al inicio)

        lista.insertarEnPosicion(0, 1);
        lista.imprimir();
        // 1 -> 5 -> 10 -> 15 -> 20 -> 30 -> (Vuelve al inicio)

        lista.insertarEnPosicion(6, 99);
        lista.imprimir();
        // 1 -> 5 -> 10 -> 15 -> 20 -> 30 -> 99 -> (Vuelve al inicio)
    }
}
