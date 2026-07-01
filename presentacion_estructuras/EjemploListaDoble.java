/**
 * Ejemplo 3: Lista Doblemente Ligada (Doubly Linked List) con Genéricos.
 * Nodos apuntan al siguiente y al anterior.
 * Permite iteración bidireccional y eliminación en O(1).
 */
public class EjemploListaDoble<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;
        Nodo<T> anterior;

        public Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> cola;

    // Inserción al final en O(1)
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    // Recorrido de izquierda a derecha
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

    // Recorrido de derecha a izquierda
    public void imprimirAtras() {
        Nodo<T> actual = cola;
        System.out.print("Atrás: NULL <- ");
        while (actual != null) {
            System.out.print(actual.valor);
            if (actual.anterior != null) System.out.print(" <-> ");
            actual = actual.anterior;
        }
        System.out.println(" -> NULL");
    }

    public static void main(String[] args) {
        EjemploListaDoble<Double> lista = new EjemploListaDoble<>();
        lista.insertarFinal(1.1);
        lista.insertarFinal(2.2);
        lista.insertarFinal(3.3);

        lista.imprimirAdelante(); // NULL <- 1.1 <-> 2.2 <-> 3.3 -> NULL
        lista.imprimirAtras();    // NULL <- 3.3 <-> 2.2 <-> 1.1 -> NULL
    }
}
