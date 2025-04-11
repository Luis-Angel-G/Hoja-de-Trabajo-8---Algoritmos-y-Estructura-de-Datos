import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Clase de pruebas unitarias para la clase {@link PriorityQueue}.
 * Verifica el correcto funcionamiento de las operaciones de una cola de prioridad.
 */
public class PriorityQueueTest {

    /**
     * Prueba el método {@link PriorityQueue#add(Object)}.
     * Verifica que un paciente se agregue correctamente a la cola de prioridad.
     */
    @Test
    public void testAdd() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        Paciente paciente = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        assertTrue(queue.add(paciente), "El paciente debería ser agregado correctamente.");
    }

    /**
     * Prueba el método {@link PriorityQueue#remove()}.
     * Verifica que el paciente con mayor prioridad sea eliminado correctamente.
     */
    @Test
    public void testRemove() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        Paciente paciente1 = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        Paciente paciente2 = new Paciente("Maria", "Fiebre", "B", LocalDateTime.now());
        queue.add(paciente1);
        queue.add(paciente2);

        Paciente removed = queue.remove();
        assertEquals(paciente1, removed, "El paciente con mayor prioridad debería ser eliminado.");
    }

    /**
     * Prueba el método {@link PriorityQueue#peek()}.
     * Verifica que el paciente con mayor prioridad sea devuelto sin ser eliminado.
     */
    @Test
    public void testPeek() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        Paciente paciente1 = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        Paciente paciente2 = new Paciente("Maria", "Fiebre", "B", LocalDateTime.now());
        queue.add(paciente1);
        queue.add(paciente2);

        Paciente peeked = queue.peek();
        assertEquals(paciente1, peeked, "El paciente con mayor prioridad debería ser devuelto sin ser eliminado.");
    }

    /**
     * Prueba el método {@link PriorityQueue#remove()} en una cola vacía.
     * Verifica que se lance una excepción {@link java.util.NoSuchElementException}.
     */
    @Test
    public void testRemoveFromEmptyQueue() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        assertThrows(java.util.NoSuchElementException.class, () -> {
            queue.remove();
        }, "Debería lanzar NoSuchElementException si la cola está vacía.");
    }

    /**
     * Prueba el método {@link PriorityQueue#peek()} en una cola vacía.
     * Verifica que devuelva {@code null}.
     */
    @Test
    public void testPeekFromEmptyQueue() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        assertNull(queue.peek(), "Debería devolver null si la cola está vacía.");
    }
}