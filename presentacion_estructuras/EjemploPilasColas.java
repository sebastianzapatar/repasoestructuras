import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Ejemplos de uso de estructuras lineales estándar de Java:
 * - Pilas (Stack / Deque)
 * - Colas (Queue / LinkedList)
 * - Colas de Prioridad (PriorityQueue)
 */
public class EjemploPilasColas {

    public static void main(String[] args) {
        
        // ==========================================
        // 1. PILA (Stack - LIFO)
        // Usando ArrayDeque que es más rápido que la clase Stack antigua
        // ==========================================
        System.out.println("=== PILA (LIFO) ===");
        Deque<String> pila = new ArrayDeque<>();
        
        pila.push("Página Inicio"); // O(1)
        pila.push("Página de Productos");
        pila.push("Detalle de Producto");
        
        // El último en entrar es el primero en salir (Botón 'Atrás' en navegador)
        System.out.println("Regresando a: " + pila.pop()); // Detalle de Producto
        System.out.println("Regresando a: " + pila.pop()); // Página de Productos
        
        
        // ==========================================
        // 2. COLA (Queue - FIFO)
        // ==========================================
        System.out.println("\n=== COLA (FIFO) ===");
        Queue<String> colaAtencion = new LinkedList<>();
        
        colaAtencion.offer("Cliente A"); // O(1)
        colaAtencion.offer("Cliente B");
        colaAtencion.offer("Cliente C");
        
        // El primero en llegar es el primero en ser atendido
        System.out.println("Atendiendo a: " + colaAtencion.poll()); // Cliente A
        System.out.println("Siguiente en la fila: " + colaAtencion.peek()); // Cliente B
        
        
        // ==========================================
        // 3. COLA DE PRIORIDAD (Min-Heap / Max-Heap)
        // ==========================================
        System.out.println("\n=== COLA DE PRIORIDAD ===");
        // Por defecto es un Min-Heap (ordena números de menor a mayor)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        pq.offer(50); // O(log n)
        pq.offer(10);
        pq.offer(30);
        
        // Siempre saldrá el menor número primero
        System.out.println("Elemento menor (Raíz): " + pq.poll()); // 10
        System.out.println("Siguiente elemento menor: " + pq.poll()); // 30
    }
}
