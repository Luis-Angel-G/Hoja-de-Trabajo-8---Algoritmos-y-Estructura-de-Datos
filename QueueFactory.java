import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Clase fábrica para crear instancias de diferentes implementaciones de colas de prioridad.
 * Proporciona un método estático para obtener una cola basada en el tipo especificado.
 */
public class QueueFactory {

    /**
     * Devuelve una instancia de una cola de prioridad basada en el tipo especificado.
     *
     * @param <E>  El tipo de elementos que se almacenarán en la cola. Debe implementar {@code Comparable}.
     * @param cola El tipo de cola que se desea crear. Puede ser "priorityqueue" o "vectorheap".
     * @return Una instancia de {@code Queue<E>} basada en el tipo especificado.
     * @throws IllegalArgumentException Si el tipo de cola especificado no es soportado.
     */
    public static <E extends Comparable<E>> Queue<E> getQueue(String cola) {
        return switch (cola) {
            case "priorityqueue" -> new PriorityQueue<>();
            case "vectorheap" -> new VectorHeap<>();
            default -> throw new IllegalArgumentException("Tipo de cola no soportado");
        };
    }
}
