import java.util.ArrayList;
import java.util.PriorityQueue;

public class VectorHeap<E extends Comparable<E>> extends PriorityQueue<E> {
    protected ArrayList<E> pacientes;

    public VectorHeap() {
        pacientes = new ArrayList<>();
    }

    
}