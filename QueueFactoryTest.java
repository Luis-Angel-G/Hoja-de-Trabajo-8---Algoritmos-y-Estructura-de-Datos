import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase {@link QueueFactory}.
 * Verifica que se creen correctamente las instancias de colas de prioridad según el tipo especificado.
 */
public class QueueFactoryTest {

    /**
     * Prueba el método {@link QueueFactory#getQueue(String)} con el tipo "priorityqueue".
     * Verifica que se devuelva una instancia de {@link PriorityQueue}.
     */
    @Test
    public void testGetPriorityQueue() {
        Queue<String> queue = QueueFactory.getQueue("priorityqueue");
        assertNotNull(queue, "La cola no debería ser nula.");
        assertTrue(queue instanceof PriorityQueue, "La cola debería ser una instancia de PriorityQueue.");
    }

    /**
     * Prueba el método {@link QueueFactory#getQueue(String)} con el tipo "vectorheap".
     * Verifica que se devuelva una instancia de {@link VectorHeap}.
     */
    @Test
    public void testGetVectorHeap() {
        Queue<String> queue = QueueFactory.getQueue("vectorheap");
        assertNotNull(queue, "La cola no debería ser nula.");
        assertTrue(queue instanceof VectorHeap, "La cola debería ser una instancia de VectorHeap.");
    }

    /**
     * Prueba el método {@link QueueFactory#getQueue(String)} con un tipo no válido.
     * Verifica que se lance una excepción {@link IllegalArgumentException} con el mensaje correcto.
     */
    @Test
    public void testInvalidQueueType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            QueueFactory.getQueue("invalidtype");
        });
        assertEquals("Tipo de cola no soportado", exception.getMessage(), "El mensaje de excepción debería ser correcto.");
    }
}
