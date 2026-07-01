import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Problema Integrador:
 * 1. Recorrer un árbol de tareas.
 * 2. Guardarlas en una Cola de Prioridad.
 * 3. Procesarlas y almacenarlas en un HashMap en O(1).
 */
public class EjemploIntegracion {

    static class Tarea {
        String id;
        int prioridad;

        public Tarea(String id, int prioridad) {
            this.id = id;
            this.prioridad = prioridad;
        }
    }

    static class NodoTarea {
        Tarea tarea;
        NodoTarea izq, der;

        public NodoTarea(Tarea tarea) {
            this.tarea = tarea;
        }
    }

    public static void flujoIntegracionTotal(NodoTarea raiz) {
        // Max-Heap: Ordena por prioridad (mayor a menor)
        PriorityQueue<Tarea> colaPrioridad = new PriorityQueue<>((t1, t2) -> 
            Integer.compare(t2.prioridad, t1.prioridad)
        );

        System.out.println("1. Recorriendo árbol y encolando...");
        recorridoPostorden(raiz, colaPrioridad);

        Map<String, String> estadoTareas = new HashMap<>();

        System.out.println("2. Procesando Cola de Prioridad (O(log n) por tarea)...");
        while (!colaPrioridad.isEmpty()) {
            Tarea tareaActual = colaPrioridad.poll(); 
            System.out.println("   Procesando tarea: " + tareaActual.id + " (Prioridad: " + tareaActual.prioridad + ")");
            // Guardar en O(1)
            estadoTareas.put(tareaActual.id, "COMPLETADA");
        }

        System.out.println("\n3. Estado final en HashMap (Consulta O(1)):");
        for (Map.Entry<String, String> entry : estadoTareas.entrySet()) {
            System.out.println("   Tarea " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static void recorridoPostorden(NodoTarea nodo, PriorityQueue<Tarea> pq) {
        if (nodo == null) return;
        recorridoPostorden(nodo.izq, pq);   
        recorridoPostorden(nodo.der, pq);   
        pq.offer(nodo.tarea);               
    }

    public static void main(String[] args) {
        // [T1: P=5]
        //   /    \
        // [T2:10] [T3:1]
        //  /
        // [T4:15]
        NodoTarea raiz = new NodoTarea(new Tarea("T1", 5));
        raiz.izq = new NodoTarea(new Tarea("T2", 10));
        raiz.der = new NodoTarea(new Tarea("T3", 1));
        raiz.izq.izq = new NodoTarea(new Tarea("T4", 15));

        flujoIntegracionTotal(raiz);
    }
}
