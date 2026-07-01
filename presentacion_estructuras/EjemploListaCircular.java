/**
 * Ejemplo 2: Lista Ligada Circular (Circular Singly Linked List) con Genéricos.
 * El último nodo apunta de regreso a la cabeza.
 * Muy útil para algoritmos de planificación (Round-Robin) o buffers.
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

    // A menudo es mejor mantener una referencia a la 'cola' en vez de la 'cabeza'.
    // Esto permite inserciones tanto al inicio como al final en O(1).
    private Nodo<T> cola;

    // Inserción al final en O(1) usando puntero a la cola
    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cola == null) {
            cola = nuevo;
            cola.siguiente = cola; // Apunta a sí mismo
        } else {
            nuevo.siguiente = cola.siguiente; // Apunta a la cabeza actual
            cola.siguiente = nuevo;
            cola = nuevo; // Actualizamos la cola al nuevo nodo
        }
    }

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
        EjemploListaCircular<Integer> lista = new EjemploListaCircular<>();
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);

        lista.imprimir(); // 10 -> 20 -> 30 -> (Vuelve al inicio)
    }
}
