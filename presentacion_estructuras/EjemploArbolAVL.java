/**
 * Ejemplo de un Árbol AVL (Árbol Binario de Búsqueda Auto-Balanceable).
 * A diferencia de un BST normal, este asegura que la altura de sus subárboles 
 * nunca difiera en más de 1, garantizando O(log n) para todas las operaciones.
 */
public class EjemploArbolAVL<T extends Comparable<T>> {

    class NodoAVL {
        T valor;
        int altura;
        NodoAVL izq, der;

        public NodoAVL(T valor) {
            this.valor = valor;
            this.altura = 1;
        }
    }

    private NodoAVL raiz;

    // Obtener la altura de un nodo
    private int altura(NodoAVL N) {
        if (N == null) return 0;
        return N.altura;
    }

    // Obtener el factor de balance de un nodo
    private int getBalance(NodoAVL N) {
        if (N == null) return 0;
        return altura(N.izq) - altura(N.der);
    }

    // ==========================================
    // ROTACIONES
    // ==========================================
    
    // Rotación Simple a la Derecha (Caso Izquierda-Izquierda)
    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;

        // Rotar
        x.der = y;
        y.izq = T2;

        // Actualizar alturas
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;

        return x;
    }

    // Rotación Simple a la Izquierda (Caso Derecha-Derecha)
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        // Rotar
        y.izq = x;
        x.der = T2;

        // Actualizar alturas
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;

        return y;
    }

    // ==========================================
    // INSERCIÓN
    // ==========================================
    public void insertar(T valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, T valor) {
        // 1. Inserción normal de BST
        if (nodo == null) return new NodoAVL(valor);

        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izq = insertarRecursivo(nodo.izq, valor);
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.der = insertarRecursivo(nodo.der, valor);
        } else {
            return nodo; // No se permiten duplicados en este AVL
        }

        // 2. Actualizar la altura del nodo actual
        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));

        // 3. Obtener el factor de balance
        int balance = getBalance(nodo);

        // 4. Si el nodo está desbalanceado, hay 4 casos:

        // Caso Izquierda Izquierda
        if (balance > 1 && valor.compareTo(nodo.izq.valor) < 0)
            return rotacionDerecha(nodo);

        // Caso Derecha Derecha
        if (balance < -1 && valor.compareTo(nodo.der.valor) > 0)
            return rotacionIzquierda(nodo);

        // Caso Izquierda Derecha
        if (balance > 1 && valor.compareTo(nodo.izq.valor) > 0) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }

        // Caso Derecha Izquierda
        if (balance < -1 && valor.compareTo(nodo.der.valor) < 0) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }

        return nodo; // Devolver el nodo si está balanceado
    }

    // Recorrido Preorden
    public void preOrden(NodoAVL nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preOrden(nodo.izq);
            preOrden(nodo.der);
        }
    }

    // Recorrido Inorden (Para imprimir ordenado)
    public void inOrden(NodoAVL nodo) {
        if (nodo != null) {
            inOrden(nodo.izq);
            System.out.print(nodo.valor + " ");
            inOrden(nodo.der);
        }
    }

    // Recorrido Postorden
    public void postOrden(NodoAVL nodo) {
        if (nodo != null) {
            postOrden(nodo.izq);
            postOrden(nodo.der);
            System.out.print(nodo.valor + " ");
        }
    }

    public static void main(String[] args) {
        EjemploArbolAVL<Integer> arbol = new EjemploArbolAVL<>();

        System.out.println("Insertando 10, 20, 30...");
        /* 
           Sin AVL, 10 -> 20 -> 30 formaría una línea recta.
           Con AVL, debería hacer una rotación izquierda y 20 quedará en la raíz.
        */
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(30);

        System.out.print("Preorden del AVL (Debe ser 20 10 30): ");
        arbol.preOrden(arbol.raiz);
        System.out.println();
        
        System.out.println("Insertando 40, 50, 25...");
        arbol.insertar(40);
        arbol.insertar(50);
        arbol.insertar(25);
        
        System.out.print("Preorden tras más inserciones (auto-balanceado): ");
        arbol.preOrden(arbol.raiz);
        System.out.println();

        System.out.print("Inorden (Siempre ordenado): ");
        arbol.inOrden(arbol.raiz);
        System.out.println();

        System.out.print("Postorden: ");
        arbol.postOrden(arbol.raiz);
        System.out.println();
    }
}
