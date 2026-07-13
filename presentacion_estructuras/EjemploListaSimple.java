/**
 * Ejemplo 1: Lista Ligada Simple (Singly Linked List) con Genéricos.
 * Cada nodo apunta al siguiente. Un solo sentido.
 * 
 * Operaciones implementadas:
 *   - insertarInicio(valor)      → O(1)
 *   - insertarFinal(valor)       → O(n) porque debemos recorrer hasta el final
 *   - insertarEnPosicion(pos, v) → O(n) porque debemos recorrer hasta la posición
 *   - buscar(valor)              → O(n)
 */
public class EjemploListaSimple<T> {

    // Nodo Genérico: solo tiene referencia al SIGUIENTE (una dirección)
    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        public Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;  // Referencia al primer nodo de la lista
    private int tamano = 0;  // Llevamos el conteo para validar posiciones

    // ==========================================
    // INSERTAR AL INICIO: O(1)
    // ==========================================
    /**
     * Inserta un nuevo nodo al INICIO de la lista.
     * 
     * ¿Cómo funciona?
     *   1. Creamos un nuevo nodo con el valor dado.
     *   2. El "siguiente" del nuevo nodo apunta a la cabeza actual.
     *   3. Actualizamos la cabeza para que sea el nuevo nodo.
     * 
     * ANTES: cabeza -> [A] -> [B] -> [C] -> NULL
     * insertarInicio("X")
     * DESPUÉS: cabeza -> [X] -> [A] -> [B] -> [C] -> NULL
     * 
     * Complejidad: O(1) — No importa cuántos elementos tenga la lista,
     * siempre hacemos las mismas operaciones (crear nodo, ajustar punteros).
     */
    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = cabeza;  // El nuevo nodo apunta a la antigua cabeza
        cabeza = nuevo;            // La cabeza ahora es el nuevo nodo
        tamano++;
    }

    // ==========================================
    // INSERTAR AL FINAL: O(n)
    // ==========================================
    /**
     * Inserta un nuevo nodo al FINAL de la lista.
     * 
     * ¿Cómo funciona?
     *   1. Si la lista está vacía → el nuevo nodo se convierte en la cabeza.
     *   2. Si NO está vacía → recorremos la lista hasta encontrar el último nodo
     *      (aquel cuyo "siguiente" es null) y lo enlazamos al nuevo nodo.
     * 
     * ANTES: cabeza -> [A] -> [B] -> [C] -> NULL
     * insertarFinal("X")
     * DESPUÉS: cabeza -> [A] -> [B] -> [C] -> [X] -> NULL
     * 
     * Complejidad: O(n) — Necesitamos recorrer toda la lista para llegar al final.
     * (Podríamos hacerlo O(1) si mantuviéramos un puntero a la cola,
     *  pero en una lista simple básica no se suele hacer.)
     */
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        // Caso especial: lista vacía → el nuevo nodo es la cabeza
        if (cabeza == null) {
            cabeza = nuevo;
            tamano++;
            return;
        }

        // Recorremos hasta el último nodo (el que tiene siguiente == null)
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        // Enlazamos el último nodo al nuevo
        actual.siguiente = nuevo;
        tamano++;
    }

    // ==========================================
    // INSERTAR EN CUALQUIER POSICIÓN: O(n)
    // ==========================================
    /**
     * Inserta un nuevo nodo en la posición indicada (0-indexado).
     * 
     * ¿Cómo funciona?
     *   - Posición 0: equivale a insertarInicio.
     *   - Posición == tamaño: equivale a insertarFinal.
     *   - Cualquier otra posición: recorremos hasta el nodo ANTERIOR a la posición
     *     deseada y ajustamos los punteros.
     * 
     * Ejemplo: insertarEnPosicion(2, "X") en la lista [A, B, C, D]
     * 
     * ANTES:  cabeza -> [A] -> [B] -> [C] -> [D] -> NULL
     *                    0      1      2      3
     * 
     *   Paso 1: Recorremos hasta el nodo en posición 1 (el nodo "B"), que es
     *           el ANTERIOR a donde queremos insertar.
     *   Paso 2: nuevo.siguiente = nodoAnterior.siguiente  (X apunta a C)
     *   Paso 3: nodoAnterior.siguiente = nuevo             (B apunta a X)
     * 
     * DESPUÉS: cabeza -> [A] -> [B] -> [X] -> [C] -> [D] -> NULL
     *                     0      1      2      3      4
     * 
     * Complejidad: O(n) — En el peor caso recorremos toda la lista.
     * 
     * @param posicion índice donde insertar (0-indexado)
     * @param valor    el valor a insertar
     */
    public void insertarEnPosicion(int posicion, T valor) {
        // Validación: la posición debe estar entre 0 y tamaño (inclusive)
        if (posicion < 0 || posicion > tamano) {
            System.out.println("⚠ Posición " + posicion + " inválida. "
                    + "Rango válido: [0, " + tamano + "]");
            return;
        }

        // Caso especial: insertar en posición 0 = insertar al inicio
        if (posicion == 0) {
            insertarInicio(valor);
            return;
        }

        // Caso especial: insertar en la última posición = insertar al final
        if (posicion == tamano) {
            insertarFinal(valor);
            return;
        }

        // Caso general: recorremos hasta el nodo ANTERIOR a la posición deseada
        Nodo<T> nuevo = new Nodo<>(valor);
        Nodo<T> anterior = cabeza;

        // Avanzamos (posicion - 1) veces para llegar al nodo anterior
        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }

        // Insertamos el nuevo nodo entre 'anterior' y 'anterior.siguiente'
        nuevo.siguiente = anterior.siguiente;  // El nuevo apunta al que estaba después
        anterior.siguiente = nuevo;            // El anterior ahora apunta al nuevo
        tamano++;
    }

    // ==========================================
    // BÚSQUEDA: O(n)
    // ==========================================
    public boolean buscar(T valor) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.valor.equals(valor)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    // Recorrido e impresión
    public void imprimir() {
        Nodo<T> actual = cabeza;
        System.out.print("Lista Simple: ");
        while (actual != null) {
            System.out.print(actual.valor + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        System.out.println("=== LISTA LIGADA SIMPLE ===\n");
        EjemploListaSimple<String> lista = new EjemploListaSimple<>();

        // ── Insertar al inicio ──
        System.out.println("--- Insertar al INICIO ---");
        lista.insertarInicio("Charlie");
        lista.insertarInicio("Bob");
        lista.insertarInicio("Alice");
        lista.imprimir();
        // Alice -> Bob -> Charlie -> NULL
        // (Alice se insertó de último al inicio, por eso queda primero)

        // ── Insertar al final ──
        System.out.println("\n--- Insertar al FINAL ---");
        lista.insertarFinal("Diana");
        lista.insertarFinal("Eve");
        lista.imprimir();
        // Alice -> Bob -> Charlie -> Diana -> Eve -> NULL

        // ── Insertar en posición específica ──
        System.out.println("\n--- Insertar en POSICIÓN ---");
        lista.insertarEnPosicion(2, "NUEVO_EN_POS_2");
        lista.imprimir();
        // Alice -> Bob -> NUEVO_EN_POS_2 -> Charlie -> Diana -> Eve -> NULL

        lista.insertarEnPosicion(0, "INICIO_POS_0");
        lista.imprimir();
        // INICIO_POS_0 -> Alice -> Bob -> NUEVO_EN_POS_2 -> Charlie -> Diana -> Eve -> NULL

        // ── Búsqueda ──
        System.out.println("\n--- BÚSQUEDA ---");
        System.out.println("¿Existe 'Charlie'? " + lista.buscar("Charlie"));  // true
        System.out.println("¿Existe 'Zara'?    " + lista.buscar("Zara"));     // false
    }
}
