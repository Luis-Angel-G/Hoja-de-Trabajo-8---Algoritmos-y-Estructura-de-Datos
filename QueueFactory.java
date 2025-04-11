import java.util.PriorityQueue;
import java.util.Queue;

public class QueueFactory {
    public static <E extends Comparable<E>> Queue<E> getQueue(String cola) {
        return switch (cola) {
            case "priorityqueue" -> new PriorityQueue<>();
            case "vectorheap" -> new VectorHeap<>();
            default -> throw new IllegalArgumentException("Tipo de cola no soportado");
        };
    }
}
