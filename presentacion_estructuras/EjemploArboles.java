/**
 * Ejemplo de un Árbol Binario de Búsqueda (BST) implementado con Genéricos (Generics).
 * <T extends Comparable<T>> asegura que cualquier tipo que guardemos en el árbol
 * (como Integer, String, etc.) pueda compararse consigo mismo para mantener el orden.
 */
public class EjemploArboles {

    // Nodo Genérico
    static class NodoArbol<T extends Comparable<T>> {
        T valor;
        NodoArbol<T> izq, der;

        public NodoArbol(T valor) {
            this.valor = valor;
        }
    }

    // Árbol Binario de Búsqueda Genérico
    static class ArbolBinarioBusqueda<T extends Comparable<T>> {
        NodoArbol<T> raiz;

        // ==========================================
        // Inserción en BST: O(log n) promedio, O(n) peor caso
        // ==========================================
        public void insertar(T valor) {
            raiz = insertarRecursivo(raiz, valor);
        }

        private NodoArbol<T> insertarRecursivo(NodoArbol<T> actual, T valor) {
            if (actual == null) {
                return new NodoArbol<>(valor);
            }
            
            // Usamos compareTo() en lugar de < o >
            // compareTo() devuelve < 0 si 'valor' es menor que 'actual.valor'
            if (valor.compareTo(actual.valor) < 0) {
                actual.izq = insertarRecursivo(actual.izq, valor);
            } 
            // compareTo() devuelve > 0 si 'valor' es mayor que 'actual.valor'
            else if (valor.compareTo(actual.valor) > 0) {
                actual.der = insertarRecursivo(actual.der, valor);
            }
            
            return actual;
        }

        // ==========================================
        // Búsqueda en BST: O(log n) promedio, O(n) peor caso
        // ==========================================

        /**
         * Busca un valor en el árbol.
         * Retorna true si el valor existe, false si no.
         */
        public boolean buscar(T valor) {
            return buscarRecursivo(raiz, valor);
        }

        /**
         * Lógica recursiva de búsqueda:
         * - Si el nodo actual es null, el valor NO está en el árbol.
         * - Si el valor es igual al nodo actual, lo encontramos.
         * - Si el valor es menor, buscamos en el subárbol izquierdo.
         * - Si el valor es mayor, buscamos en el subárbol derecho.
         */
        private boolean buscarRecursivo(NodoArbol<T> actual, T valor) {
            // Caso base: llegamos a un nodo nulo → no existe
            if (actual == null) {
                return false;
            }

            int comparacion = valor.compareTo(actual.valor);

            if (comparacion == 0) {
                // ¡Encontrado!
                return true;
            } else if (comparacion < 0) {
                // El valor buscado es menor → ir a la izquierda
                return buscarRecursivo(actual.izq, valor);
            } else {
                // El valor buscado es mayor → ir a la derecha
                return buscarRecursivo(actual.der, valor);
            }
        }

        // ==========================================
        // Eliminación en BST: O(log n) promedio, O(n) peor caso
        // ==========================================

        /**
         * Elimina un valor del árbol.
         * Hay 3 casos posibles al eliminar un nodo:
         *   Caso 1: El nodo es una HOJA (sin hijos) → simplemente se elimina.
         *   Caso 2: El nodo tiene UN solo hijo → se reemplaza por su hijo.
         *   Caso 3: El nodo tiene DOS hijos → se reemplaza por su SUCESOR INORDEN
         *           (el menor del subárbol derecho) y luego se elimina ese sucesor.
         */
        public void eliminar(T valor) {
            raiz = eliminarRecursivo(raiz, valor);
        }

        private NodoArbol<T> eliminarRecursivo(NodoArbol<T> actual, T valor) {
            // Caso base: si el nodo es null, el valor no existe en el árbol
            if (actual == null) {
                return null;
            }

            int comparacion = valor.compareTo(actual.valor);

            if (comparacion < 0) {
                // El valor a eliminar es MENOR → buscar en el subárbol izquierdo
                actual.izq = eliminarRecursivo(actual.izq, valor);
            } else if (comparacion > 0) {
                // El valor a eliminar es MAYOR → buscar en el subárbol derecho
                actual.der = eliminarRecursivo(actual.der, valor);
            } else {
                // ¡Encontramos el nodo a eliminar!

                // CASO 1 y CASO 2: El nodo tiene 0 o 1 hijo
                // Si no tiene hijo izquierdo, retornamos el derecho (puede ser null → caso hoja)
                if (actual.izq == null) {
                    return actual.der;
                }
                // Si no tiene hijo derecho, retornamos el izquierdo
                if (actual.der == null) {
                    return actual.izq;
                }

                // CASO 3: El nodo tiene DOS hijos
                // Buscamos el SUCESOR INORDEN: el nodo más pequeño del subárbol derecho.
                // ¿Por qué? Porque es el valor más cercano y mayor al nodo actual,
                // lo que mantiene la propiedad del BST al reemplazarlo.
                T sucesor = encontrarMinimo(actual.der);

                // Reemplazamos el valor del nodo actual por el valor del sucesor
                actual.valor = sucesor;

                // Eliminamos el sucesor de su posición original (subárbol derecho)
                actual.der = eliminarRecursivo(actual.der, sucesor);
            }

            return actual;
        }

        /**
         * Encuentra el valor mínimo en un subárbol.
         * En un BST, el mínimo siempre está en el nodo más a la izquierda.
         */
        private T encontrarMinimo(NodoArbol<T> nodo) {
            // Recorremos hacia la izquierda hasta llegar al último nodo
            while (nodo.izq != null) {
                nodo = nodo.izq;
            }
            return nodo.valor;
        }

        // ==========================================
        // RECORRIDOS (Toman O(n) porque visitan cada nodo)
        // ==========================================
        
        // 1. INORDEN (Izquierda -> Raíz -> Derecha)
        public void inOrden(NodoArbol<T> nodo) {
            if (nodo != null) {
                inOrden(nodo.izq);
                System.out.print(nodo.valor + " ");
                inOrden(nodo.der);
            }
        }

        // 2. PREORDEN (Raíz -> Izquierda -> Derecha)
        public void preOrden(NodoArbol<T> nodo) {
            if (nodo != null) {
                System.out.print(nodo.valor + " ");
                preOrden(nodo.izq);
                preOrden(nodo.der);
            }
        }

        // 3. POSTORDEN (Izquierda -> Derecha -> Raíz)
        public void postOrden(NodoArbol<T> nodo) {
            if (nodo != null) {
                postOrden(nodo.izq);
                postOrden(nodo.der);
                System.out.print(nodo.valor + " ");
            }
        }

        // ================================================================
        // ANCESTRO COMÚN MÁS CERCANO (Lowest Common Ancestor - LCA)
        // ================================================================

        /**
         * LCA (Lowest Common Ancestor) — Ancestro Común más Cercano
         * 
         * DEFINICIÓN: Dados dos nodos P y Q en un árbol, el LCA es el nodo
         * más profundo (más alejado de la raíz) que es ancestro de AMBOS.
         * 
         * EJEMPLO con el árbol:
         *              50
         *            /    \
         *          30      70
         *         /  \    /  \
         *       20   40  60   80
         * 
         *   - LCA(20, 40) = 30  → Ambos son hijos de 30
         *   - LCA(20, 60) = 50  → Uno está a la izquierda y otro a la derecha de 50
         *   - LCA(60, 80) = 70  → Ambos están en el subárbol derecho de 70
         *   - LCA(20, 30) = 30  → Un nodo PUEDE ser ancestro de sí mismo
         * 
         * ALGORITMO para BST (aprovechamos la propiedad de orden):
         *   1. Si AMBOS valores son MENORES que el nodo actual → el LCA está a la IZQUIERDA
         *   2. Si AMBOS valores son MAYORES que el nodo actual → el LCA está a la DERECHA
         *   3. Si uno es menor y otro mayor (o uno es igual) → el nodo actual ES el LCA
         *      porque es el punto donde los caminos hacia P y Q se "separan"
         * 
         * COMPLEJIDAD: O(log n) promedio en un BST balanceado, O(n) peor caso (árbol degenerado)
         * 
         * @param p primer valor a buscar
         * @param q segundo valor a buscar
         * @return el valor del ancestro común más cercano, o null si no existe
         */
        public T ancestroComun(T p, T q) {
            // Primero verificamos que ambos valores existan en el árbol
            if (!buscar(p) || !buscar(q)) {
                System.out.println("⚠ Uno o ambos valores no existen en el árbol.");
                return null;
            }
            // Delegamos a la función recursiva
            NodoArbol<T> resultado = ancestroComunRecursivo(raiz, p, q);
            return (resultado != null) ? resultado.valor : null;
        }

        /**
         * Función recursiva que encuentra el LCA.
         * 
         * La clave del algoritmo es:
         * - En un BST, todos los valores menores están a la izquierda
         *   y todos los mayores a la derecha.
         * - Si ambos valores que buscamos son menores que el nodo actual,
         *   SABEMOS que el LCA no puede ser este nodo ni estar a la derecha,
         *   así que bajamos a la izquierda.
         * - Análogamente si ambos son mayores, bajamos a la derecha.
         * - Cuando los valores se "separan" (uno a cada lado, o uno ES el nodo),
         *   encontramos el LCA.
         */
        private NodoArbol<T> ancestroComunRecursivo(NodoArbol<T> actual, T p, T q) {
            // Si llegamos a null, no hay ancestro
            if (actual == null) {
                return null;
            }

            // Comparamos p y q con el valor del nodo actual
            int compP = p.compareTo(actual.valor); // < 0 si p es menor, > 0 si p es mayor
            int compQ = q.compareTo(actual.valor); // < 0 si q es menor, > 0 si q es mayor

            // CASO 1: Ambos valores son MENORES que el nodo actual
            // → El LCA debe estar en el subárbol IZQUIERDO
            if (compP < 0 && compQ < 0) {
                return ancestroComunRecursivo(actual.izq, p, q);
            }

            // CASO 2: Ambos valores son MAYORES que el nodo actual
            // → El LCA debe estar en el subárbol DERECHO
            if (compP > 0 && compQ > 0) {
                return ancestroComunRecursivo(actual.der, p, q);
            }

            // CASO 3: Los valores están en lados DIFERENTES (uno menor, uno mayor)
            // O uno de los valores ES el nodo actual.
            // → ¡Este nodo actual ES el LCA!
            // Porque aquí es donde los caminos hacia p y q se bifurcan.
            return actual;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ÁRBOL BINARIO DE BÚSQUEDA - DEMOSTRACIÓN COMPLETA ===\n");
        ArbolBinarioBusqueda<Integer> bst = new ArbolBinarioBusqueda<>();
        
        // ── Inserción ──────────────────────────────────────
        // Construimos el siguiente árbol:
        //              50
        //            /    \
        //          30      70
        //         /  \    /  \
        //       20   40  60   80
        int[] valores = {50, 30, 20, 40, 70, 60, 80};
        System.out.println("Insertando valores: 50, 30, 20, 40, 70, 60, 80");
        for (int v : valores) {
            bst.insertar(v);
        }
        
        System.out.print("Inorden (ordenado):  ");
        bst.inOrden(bst.raiz);
        System.out.println();

        System.out.print("Preorden:            ");
        bst.preOrden(bst.raiz);
        System.out.println();

        System.out.print("Postorden:           ");
        bst.postOrden(bst.raiz);
        System.out.println();

        // ── Búsqueda ──────────────────────────────────────
        System.out.println("\n--- BÚSQUEDA ---");
        System.out.println("¿Existe 40? " + bst.buscar(40));  // true
        System.out.println("¿Existe 25? " + bst.buscar(25));  // false

        // ── Ancestro Común Más Cercano (LCA) ──────────────
        System.out.println("\n--- ANCESTRO COMÚN MÁS CERCANO (LCA) ---");

        // LCA(20, 40) = 30  → Ambos son hijos directos de 30
        System.out.println("LCA(20, 40) = " + bst.ancestroComun(20, 40));

        // LCA(20, 60) = 50  → 20 está a la izquierda de 50, 60 a la derecha
        System.out.println("LCA(20, 60) = " + bst.ancestroComun(20, 60));

        // LCA(60, 80) = 70  → Ambos están en el subárbol derecho bajo 70
        System.out.println("LCA(60, 80) = " + bst.ancestroComun(60, 80));

        // LCA(20, 30) = 30  → 30 es ancestro de 20 Y es él mismo
        System.out.println("LCA(20, 30) = " + bst.ancestroComun(20, 30));

        // LCA(20, 80) = 50  → Están en extremos opuestos, se unen en la raíz
        System.out.println("LCA(20, 80) = " + bst.ancestroComun(20, 80));

        // ── Eliminación ───────────────────────────────────
        System.out.println("\n--- ELIMINACIÓN ---");
        
        // Caso 1: Eliminar una HOJA (nodo sin hijos)
        System.out.println("Eliminando 20 (hoja)...");
        bst.eliminar(20);
        System.out.print("Inorden después: ");
        bst.inOrden(bst.raiz);
        System.out.println();

        // Caso 2: Eliminar nodo con UN hijo
        // Después de eliminar 20, el nodo 30 solo tiene hijo derecho (40)
        System.out.println("Eliminando 30 (un hijo)...");
        bst.eliminar(30);
        System.out.print("Inorden después: ");
        bst.inOrden(bst.raiz);
        System.out.println();

        // Caso 3: Eliminar nodo con DOS hijos
        System.out.println("Eliminando 50 (raíz, dos hijos)...");
        bst.eliminar(50);
        System.out.print("Inorden después: ");
        bst.inOrden(bst.raiz);
        System.out.println();

        // ── Demostración con Strings ──────────────────────
        System.out.println("\n\n=== ÁRBOL CON STRING ===");
        ArbolBinarioBusqueda<String> bstStrings = new ArbolBinarioBusqueda<>();
        
        String[] palabras = {"Pera", "Manzana", "Banano", "Uva", "Naranja"};
        for (String p : palabras) {
            bstStrings.insertar(p);
        }
        
        System.out.print("Inorden (Alfabético): ");
        bstStrings.inOrden(bstStrings.raiz);
        System.out.println();

        System.out.println("LCA(\"Banano\", \"Naranja\") = " + bstStrings.ancestroComun("Banano", "Naranja"));
        System.out.println("LCA(\"Banano\", \"Uva\") = " + bstStrings.ancestroComun("Banano", "Uva"));
    }
}
