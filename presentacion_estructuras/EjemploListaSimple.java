/**
 * Ejemplo 1: Lista Ligada Simple (Singly Linked List) con Genéricos.
 * Cada nodo apunta al siguiente. Un solo sentido.
 */
public class EjemploListaSimple<T> {

    // Nodo Genérico
    static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        public Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;

    // Inserción al inicio: O(1)
    public void insertarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // Búsqueda: O(n)
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
        EjemploListaSimple<String> listaNombres = new EjemploListaSimple<>();
        listaNombres.insertarInicio("Alice");
        listaNombres.insertarInicio("Bob");
        listaNombres.insertarInicio("Charlie");
        
        listaNombres.imprimir(); // Charlie -> Bob -> Alice -> NULL
        System.out.println("¿Existe 'Alice'? " + listaNombres.buscar("Alice"));
    }
}
