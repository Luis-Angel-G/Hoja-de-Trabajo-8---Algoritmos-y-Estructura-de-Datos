import java.util.ArrayList;
import java.util.PriorityQueue;

public class VectorHeap<E extends Comparable<E>> extends PriorityQueue<E> {
    protected ArrayList<E> pacientes;

    public VectorHeap() {
        pacientes = new ArrayList<>();
        pacientes.add(null);
    }

    protected static int parent(int i) {
        return i / 2;
    }

    protected static int left(int i) {
        return 2 * i;
    }

    protected static int right(int i) {
        return 2 * i + 1;
    }

}