/**
 * Ejemplo 3: Lista Doblemente Ligada (Doubly Linked List) con Genéricos.
 * Cada nodo tiene DOS punteros: uno al siguiente y otro al anterior.
 * Permite iteración bidireccional y eliminación eficiente.
 * 
 * Ventaja sobre la lista simple: podemos recorrer en AMBAS direcciones
 * y eliminar un nodo en O(1) si ya tenemos la referencia a él.
 * 
 * Estructura visual:
 *   NULL <- [cabeza] <-> [nodo2] <-> [nodo3] <-> [cola] -> NULL
 * 
 * Operaciones:
 *   - insertarInicio       → O(1)
 *   - insertarFinal        → O(1) (tenemos puntero a la cola)
 *   - insertarEnPosicion   → O(n)
 */
public class EjemploListaDoble<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;  // Puntero al nodo que viene después
        Nodo<T> anterior;   // Puntero al nodo que viene antes

        public Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo<T> cabeza;  // Primer nodo
    private Nodo<T> cola;    // Último nodo (permite insertarFinal en O(1))
    private int tamano = 0;

    // ==========================================
    // INSERTAR AL INICIO: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al INICIO de la lista doble.
     * 
     * ¿Cómo funciona?
     *   1. Creamos un nuevo nodo.
     *   2. El "siguiente" del nuevo apunta a la cabeza actual.
     *   3. El "anterior" de la cabeza actual apunta al nuevo.
     *   4. Actualizamos la cabeza al nuevo nodo.
     * 
     * ANTES:  NULL <- [A] <-> [B] <-> [C] -> NULL
     *                  ↑ cabeza        ↑ cola
     * 
     * insertarInicio("X"):
     *   nuevo.siguiente = cabeza (A)       → X apunta a A
     *   cabeza.anterior = nuevo            → A apunta atrás a X
     *   cabeza = nuevo                     → X es la nueva cabeza
     * 
     * DESPUÉS: NULL <- [X] <-> [A] <-> [B] <-> [C] -> NULL
     *                   ↑ cabeza               ↑ cola
     * 
     * CLAVE de listas dobles: siempre hay que actualizar AMBOS punteros
     * (siguiente Y anterior) para mantener la doble dirección.
     */
    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cabeza == null) {
            // Lista vacía: el nuevo nodo es tanto cabeza como cola
            cabeza = cola = nuevo;
        } else {
            // El nuevo apunta hacia adelante a la antigua cabeza
            nuevo.siguiente = cabeza;
            // La antigua cabeza apunta hacia atrás al nuevo
            cabeza.anterior = nuevo;
            // Actualizamos la cabeza
            cabeza = nuevo;
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR AL FINAL: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al FINAL de la lista doble.
     * 
     * ¿Cómo funciona?
     *   1. Creamos un nuevo nodo.
     *   2. El "anterior" del nuevo apunta a la cola actual.
     *   3. El "siguiente" de la cola actual apunta al nuevo.
     *   4. Actualizamos la cola al nuevo nodo.
     * 
     * ANTES:  NULL <- [A] <-> [B] <-> [C] -> NULL
     *                  ↑ cabeza        ↑ cola
     * 
     * insertarFinal("X"):
     *   nuevo.anterior = cola (C)          → X apunta atrás a C
     *   cola.siguiente = nuevo             → C apunta adelante a X
     *   cola = nuevo                       → X es la nueva cola
     * 
     * DESPUÉS: NULL <- [A] <-> [B] <-> [C] <-> [X] -> NULL
     *                   ↑ cabeza                ↑ cola
     * 
     * Complejidad: O(1) porque tenemos acceso directo a la cola.
     */
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;  // La cola apunta al nuevo
            nuevo.anterior = cola;   // El nuevo apunta atrás a la cola
            cola = nuevo;            // Actualizamos la cola
        }
        tamano++;
    }

    // ==========================================
    // INSERTAR EN CUALQUIER POSICIÓN: O(n)
    // ==========================================
    /**
     * Inserta un nuevo nodo en la posición indicada (0-indexado).
     * 
     * En una lista doble, al insertar en una posición intermedia necesitamos
     * actualizar CUATRO punteros (2 del nuevo nodo + 2 de los vecinos):
     * 
     * Ejemplo: insertarEnPosicion(2, "X") en [A, B, C, D]
     * 
     * ANTES:  NULL <- [A] <-> [B] <-> [C] <-> [D] -> NULL
     *                  0       1       2       3
     *                          ↑ant    ↑sigDeAnt
     * 
     *   Paso 1: Recorremos hasta posición 1 (nodo B = anterior)
     *   Paso 2: sigDeAnt = anterior.siguiente  (guardamos C)
     *   Paso 3: anterior.siguiente = nuevo     (B -> X)
     *   Paso 4: nuevo.anterior = anterior      (X <- B)
     *   Paso 5: nuevo.siguiente = sigDeAnt     (X -> C)
     *   Paso 6: sigDeAnt.anterior = nuevo      (X <- C)
     * 
     * DESPUÉS: NULL <- [A] <-> [B] <-> [X] <-> [C] <-> [D] -> NULL
     *                   0       1       2       3       4
     * 
     * @param posicion índice donde insertar (0-indexado)
     * @param valor    el valor a insertar
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

        // Caso general: recorremos hasta el nodo ANTERIOR a la posición
        Nodo<T> nuevo = new Nodo<>(valor);
        Nodo<T> anterior = cabeza;

        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }

        // Guardamos el nodo que está después del anterior
        Nodo<T> sigDeAnt = anterior.siguiente;

        // Enlazamos el nuevo nodo (4 punteros a actualizar)
        anterior.siguiente = nuevo;     // anterior -> nuevo
        nuevo.anterior = anterior;      // anterior <- nuevo
        nuevo.siguiente = sigDeAnt;     // nuevo -> sigDeAnt
        sigDeAnt.anterior = nuevo;      // nuevo <- sigDeAnt
        tamano++;
    }

    // ==========================================
    // RECORRIDOS
    // ==========================================

    // Recorrido de izquierda a derecha (usando puntero 'siguiente')
    public void imprimirAdelante() {
        Nodo<T> actual = cabeza;
        System.out.print("Adelante: NULL <- ");
        while (actual != null) {
            System.out.print(actual.valor);
            if (actual.siguiente != null) System.out.print(" <-> ");
            actual = actual.siguiente;
        }
        System.out.println(" -> NULL");
    }

    // Recorrido de derecha a izquierda (usando puntero 'anterior')
    public void imprimirAtras() {
        Nodo<T> actual = cola;
        System.out.print("Atrás:   NULL <- ");
        while (actual != null) {
            System.out.print(actual.valor);
            if (actual.anterior != null) System.out.print(" <-> ");
            actual = actual.anterior;
        }
        System.out.println(" -> NULL");
    }

    public static void main(String[] args) {
        System.out.println("=== LISTA DOBLEMENTE LIGADA ===\n");
        EjemploListaDoble<Double> lista = new EjemploListaDoble<>();

        // ── Insertar al final ──
        System.out.println("--- Insertar al FINAL ---");
        lista.insertarFinal(1.1);
        lista.insertarFinal(2.2);
        lista.insertarFinal(3.3);
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // Adelante: NULL <- 1.1 <-> 2.2 <-> 3.3 -> NULL
        // Atrás:   NULL <- 3.3 <-> 2.2 <-> 1.1 -> NULL

        // ── Insertar al inicio ──
        System.out.println("\n--- Insertar al INICIO ---");
        lista.insertarInicio(0.0);
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // Adelante: NULL <- 0.0 <-> 1.1 <-> 2.2 <-> 3.3 -> NULL

        // ── Insertar en posición ──
        System.out.println("\n--- Insertar en POSICIÓN ---");
        lista.insertarEnPosicion(2, 1.5);
        lista.imprimirAdelante();
        // Adelante: NULL <- 0.0 <-> 1.1 <-> 1.5 <-> 2.2 <-> 3.3 -> NULL

        lista.insertarEnPosicion(0, -1.0);
        lista.imprimirAdelante();
        // Adelante: NULL <- -1.0 <-> 0.0 <-> 1.1 <-> 1.5 <-> 2.2 <-> 3.3 -> NULL

        lista.insertarEnPosicion(6, 9.9);
        lista.imprimirAdelante();
        lista.imprimirAtras();
        // Verificamos que ambos recorridos muestran los mismos datos en orden inverso
    }
}
