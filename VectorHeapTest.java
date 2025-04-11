import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

// Filepath: VectorHeapTest.java

public class VectorHeapTest {

    @Test
    public void testHeapInitialization() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        assertTrue(heap.obtenerPacientes().isEmpty(), "Heap should initialize as empty.");
    }

    @Test
    public void testAddElements() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(1);

        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(1); // Smallest element at the root
        expectedOrder.add(5);
        expectedOrder.add(20);
        expectedOrder.add(10);

        assertEquals(expectedOrder, heap.obtenerPacientes(), "Heap should maintain min-heap property after adding elements.");
    }

    @Test
    public void testRemoveElements() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(1);

        assertEquals(1, heap.remove(), "Removing should return the smallest element.");
        assertEquals(5, heap.remove(), "Next smallest element should be returned.");
        assertEquals(10, heap.remove(), "Heap should continue returning elements in order.");
        assertEquals(20, heap.remove(), "Last element should be the largest.");
        assertNull(heap.remove(), "Removing from an empty heap should return null.");
    }

    @Test
    public void testPeek() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(1);

        assertEquals(1, heap.peek(), "Peek should return the smallest element without removing it.");
        assertEquals(1, heap.peek(), "Peek should not modify the heap.");
    }

    @Test
    public void testEmptyHeap() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        assertNull(heap.peek(), "Peek on an empty heap should return null.");
        assertNull(heap.remove(), "Remove on an empty heap should return null.");
    }
}