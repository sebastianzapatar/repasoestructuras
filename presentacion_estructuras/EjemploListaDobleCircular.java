/**
 * Ejemplo 4: Lista Doblemente Ligada Circular (Circular Doubly Linked List) con Genéricos.
 * Máxima flexibilidad. La cabeza y la cola están conectadas en ambas direcciones.
 */
public class EjemploListaDobleCircular<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;
        Nodo<T> anterior;

        public Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> cabeza;

    public void insertarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Nodo<T> cola = cabeza.anterior; // Accesible en O(1)
            
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
    }

    public void imprimirAdelante() {
        if (cabeza == null) return;
        
        System.out.print("Adelante: ");
        Nodo<T> actual = cabeza;
        do {
            System.out.print(actual.valor + " <-> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(Vuelve al inicio)");
    }

    public static void main(String[] args) {
        EjemploListaDobleCircular<Character> lista = new EjemploListaDobleCircular<>();
        lista.insertarFinal('X');
        lista.insertarFinal('Y');
        lista.insertarFinal('Z');

        lista.imprimirAdelante(); // X <-> Y <-> Z <-> (Vuelve al inicio)
    }
}
