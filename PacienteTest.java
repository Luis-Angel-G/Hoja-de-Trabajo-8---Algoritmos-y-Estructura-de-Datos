import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Clase de pruebas unitarias para la clase {@link Paciente}.
 * Verifica el correcto funcionamiento de los métodos y comportamientos de la clase.
 */
public class PacienteTest {

    /**
     * Prueba el constructor y los métodos getter de la clase {@link Paciente}.
     * Verifica que los valores iniciales se asignen correctamente.
     */
    @Test
    public void testConstructorAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        Paciente paciente = new Paciente("Juan Perez", "Dolor de cabeza", "A", now);

        assertEquals("Juan Perez", paciente.getNombreDelPaciente(), "Nombre del paciente incorrecto.");
        assertEquals("Dolor de cabeza", paciente.getDescripcionDelSintoma(), "Descripción del síntoma incorrecta.");
        assertEquals("A", paciente.getCodigoDeEmergencia(), "Código de emergencia incorrecto.");
        assertEquals(now, paciente.getCurrentDateTime(), "Fecha y hora de registro incorrecta.");
    }

    /**
     * Prueba el método {@link Paciente#compareTo(Paciente)} para comparar pacientes
     * por el código de emergencia.
     * Verifica que los pacientes con códigos de emergencia más altos tengan menor prioridad.
     */
    @Test
    public void testCompareToByCodigoDeEmergencia() {
        LocalDateTime now = LocalDateTime.now();
        Paciente paciente1 = new Paciente("Juan Perez", "Dolor de cabeza", "A", now);
        Paciente paciente2 = new Paciente("Maria Lopez", "Fiebre", "B", now);

        assertTrue(paciente1.compareTo(paciente2) < 0, "Paciente1 debería tener mayor prioridad que Paciente2.");
        assertTrue(paciente2.compareTo(paciente1) > 0, "Paciente2 debería tener menor prioridad que Paciente1.");
    }

    /**
     * Prueba el método {@link Paciente#compareTo(Paciente)} para comparar pacientes
     * con el mismo código de emergencia pero diferentes fechas/hora de registro.
     * Verifica que los pacientes registrados antes tengan mayor prioridad.
     */
    @Test
    public void testCompareToByCurrentDateTime() {
        LocalDateTime earlier = LocalDateTime.now().minusHours(1);
        LocalDateTime later = LocalDateTime.now();
        Paciente paciente1 = new Paciente("Juan Perez", "Dolor de cabeza", "A", earlier);
        Paciente paciente2 = new Paciente("Maria Lopez", "Fiebre", "A", later);

        assertTrue(paciente1.compareTo(paciente2) < 0, "Paciente1 debería tener mayor prioridad por fecha/hora.");
        assertTrue(paciente2.compareTo(paciente1) > 0, "Paciente2 debería tener menor prioridad por fecha/hora.");
    }

    /**
     * Prueba el método {@link Paciente#toString()}.
     * Verifica que la representación en cadena del paciente sea correcta.
     */
    @Test
    public void testToString() {
        LocalDateTime now = LocalDateTime.now();
        Paciente paciente = new Paciente("Juan Perez", "Dolor de cabeza", "A", now);

        String expected = "Juan Perez, Dolor de cabeza, A";
        assertEquals(expected, paciente.toString(), "La representación en cadena del paciente es incorrecta.");
    }
}