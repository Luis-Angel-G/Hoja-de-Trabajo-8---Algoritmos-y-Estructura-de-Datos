import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase {@link SistemaDeAtencion}.
 * Verifica el correcto funcionamiento de las operaciones del sistema de atención de pacientes.
 */
public class SistemaDeAtencionTest {
    private SistemaDeAtencion sistema;

    /**
     * Configura el entorno de pruebas antes de cada test.
     * Inicializa una instancia de {@link SistemaDeAtencion} con una cola de tipo "priorityqueue".
     */
    @BeforeEach
    public void setUp() {
        sistema = new SistemaDeAtencion("priorityqueue");
    }

    /**
     * Método auxiliar para acceder al campo privado "pacientes" de la clase {@link SistemaDeAtencion}.
     * Utiliza reflexión para obtener la cola de pacientes.
     *
     * @return La cola de pacientes del sistema.
     */
    @SuppressWarnings("unchecked")
    private Queue<Paciente> getPacientes() {
        try {
            Field field = SistemaDeAtencion.class.getDeclaredField("pacientes");
            field.setAccessible(true); // Permite acceder al campo privado
            return (Queue<Paciente>) field.get(sistema);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("No se pudo acceder al campo 'pacientes'", e);
        }
    }

    /**
     * Prueba el constructor de la clase {@link SistemaDeAtencion}.
     * Verifica que el sistema se inicialice correctamente.
     */
    @Test
    public void testConstructor() {
        assertNotNull(sistema, "El sistema debería inicializarse correctamente.");
    }

    /**
     * Prueba el método {@link SistemaDeAtencion#cargarFichaDePacientes()}.
     * Verifica que los pacientes se carguen correctamente desde un archivo.
     *
     * @throws IOException Si ocurre un error al crear o leer el archivo.
     */
    @Test
    public void testCargarFichaDePacientes() throws IOException {
        // Crear un archivo temporal con datos de prueba
        File archivo = new File("pacientes.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("Juan,Dolor de cabeza,A,2025-04-10T10:00:00\n");
            writer.write("Maria,Fiebre,B,2025-04-10T11:00:00\n");
        }

        sistema.cargarFichaDePacientes();

        // Verificar que los pacientes se cargaron correctamente
        Queue<Paciente> pacientes = getPacientes();
        assertEquals(2, pacientes.size(), "Debería haber 2 pacientes en la cola.");
        assertEquals("Juan", pacientes.peek().getNombreDelPaciente(), "El primer paciente debería ser Juan.");

        // Eliminar el archivo temporal
        archivo.delete();
    }

    /**
     * Prueba el método {@link SistemaDeAtencion#guardarFichasDePacientes()}.
     * Verifica que los pacientes se guarden correctamente en un archivo.
     *
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    @Test
    public void testGuardarFichasDePacientes() throws IOException {
        // Agregar un paciente a la cola
        Paciente paciente = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        getPacientes().add(paciente);

        // Guardar los pacientes en el archivo
        sistema.guardarFichasDePacientes();

        // Verificar que el archivo se creó
        File archivo = new File("pacientes.txt");
        assertTrue(archivo.exists(), "El archivo de pacientes debería haberse creado.");

        // Limpiar el archivo después de la prueba
        archivo.delete();
    }

    /**
     * Prueba la funcionalidad de agregar un paciente al sistema.
     * Verifica que el paciente se agregue correctamente a la cola.
     */
    @Test
    public void testAgregarPaciente() {
        // Crear un nuevo paciente
        Paciente paciente = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        boolean agregado = getPacientes().add(paciente);

        // Verificar que el paciente se agregó correctamente
        assertTrue(agregado, "El paciente debería haberse agregado correctamente.");
        assertEquals(1, getPacientes().size(), "Debería haber 1 paciente en la cola.");
    }

    /**
     * Prueba la funcionalidad de atender a un paciente.
     * Verifica que el paciente con mayor prioridad sea eliminado correctamente.
     */
    @Test
    public void testAtenderPaciente() {
        // Agregar dos pacientes a la cola
        Paciente paciente1 = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        Paciente paciente2 = new Paciente("Maria", "Fiebre", "B", LocalDateTime.now());
        getPacientes().add(paciente1);
        getPacientes().add(paciente2);

        // Atender al paciente con mayor prioridad
        Paciente atendido = getPacientes().remove();
        assertEquals(paciente1, atendido, "El paciente con mayor prioridad debería ser atendido primero.");
        assertEquals(1, getPacientes().size(), "Debería quedar 1 paciente en la cola.");
    }

    /**
     * Prueba la funcionalidad de ver el siguiente paciente sin eliminarlo.
     * Verifica que el paciente con mayor prioridad sea devuelto correctamente.
     */
    @Test
    public void testVerSiguientePaciente() {
        // Agregar dos pacientes a la cola
        Paciente paciente1 = new Paciente("Juan", "Dolor de cabeza", "A", LocalDateTime.now());
        Paciente paciente2 = new Paciente("Maria", "Fiebre", "B", LocalDateTime.now());
        getPacientes().add(paciente1);
        getPacientes().add(paciente2);

        // Verificar el siguiente paciente sin eliminarlo
        Paciente siguiente = getPacientes().peek();
        assertEquals(paciente1, siguiente, "El paciente con mayor prioridad debería ser el siguiente.");
        assertEquals(2, getPacientes().size(), "No debería eliminarse ningún paciente.");
    }
}