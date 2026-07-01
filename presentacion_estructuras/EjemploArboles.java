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
    }

    public static void main(String[] args) {
        System.out.println("=== ÁRBOL CON INTEGER ===");
        ArbolBinarioBusqueda<Integer> bstEnteros = new ArbolBinarioBusqueda<>();
        
        // Insertamos valores
        int[] valores = {50, 30, 20, 40, 70, 60, 80};
        for (int v : valores) {
            bstEnteros.insertar(v);
        }
        
        System.out.print("Inorden (Ordenado): ");
        bstEnteros.inOrden(bstEnteros.raiz); // 20 30 40 50 60 70 80
        System.out.println();
        
        System.out.println("\n=== ÁRBOL CON STRING ===");
        ArbolBinarioBusqueda<String> bstStrings = new ArbolBinarioBusqueda<>();
        
        // El árbol de Strings se ordenará alfabéticamente automáticamente
        String[] palabras = {"Pera", "Manzana", "Banano", "Uva", "Naranja"};
        for (String p : palabras) {
            bstStrings.insertar(p);
        }
        
        System.out.print("Inorden (Alfabético): ");
        bstStrings.inOrden(bstStrings.raiz); // Banano Manzana Naranja Pera Uva 
        System.out.println();
    }
}
