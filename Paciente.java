import java.time.LocalDateTime;

/**
 * Representa un paciente con información sobre su nombre, descripción del síntoma,
 * código de emergencia y la fecha/hora de registro.
 * Implementa la interfaz {@code Comparable} para permitir la comparación entre pacientes
 * basada en el código de emergencia y, en caso de empate, en la fecha/hora de registro.
 */
public class Paciente implements Comparable<Paciente> {
    private final String nombreDelPaciente;
    private final String descripcionDelSintoma;
    private final String codigoDeEmergencia;
    private LocalDateTime currentDateTime = LocalDateTime.now();

    /**
     * Constructor para inicializar un objeto {@code Paciente}.
     *
     * @param nombreDelPaciente    El nombre del paciente.
     * @param descripcionDelSintoma Una breve descripción del síntoma del paciente.
     * @param codigoDeEmergencia   El código de emergencia asociado al paciente.
     * @param currentDateTime      La fecha y hora de registro del paciente.
     */
    public Paciente(String nombreDelPaciente, String descripcionDelSintoma, String codigoDeEmergencia, LocalDateTime currentDateTime) {
        this.nombreDelPaciente = nombreDelPaciente;
        this.descripcionDelSintoma = descripcionDelSintoma;
        this.codigoDeEmergencia = codigoDeEmergencia;
        this.currentDateTime = currentDateTime;
    }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return El nombre del paciente.
     */
    public String getNombreDelPaciente() {
        return nombreDelPaciente;
    }

    /**
     * Obtiene la descripción del síntoma del paciente.
     *
     * @return La descripción del síntoma.
     */
    public String getDescripcionDelSintoma() {
        return descripcionDelSintoma;
    }

    /**
     * Obtiene el código de emergencia del paciente.
     *
     * @return El código de emergencia.
     */
    public String getCodigoDeEmergencia() {
        return codigoDeEmergencia;
    }

    /**
     * Obtiene la fecha y hora de registro del paciente.
     *
     * @return La fecha y hora de registro.
     */
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    /**
     * Compara este paciente con otro basado en el código de emergencia.
     * Si los códigos de emergencia son iguales, se compara por la fecha/hora de registro.
     *
     * @param paciente2 El paciente con el que se va a comparar.
     * @return Un valor negativo, cero o positivo si este paciente es menor, igual o mayor
     *         que el paciente especificado.
     */
    @Override
    public int compareTo(Paciente paciente2) {
        if (this.codigoDeEmergencia.equals(paciente2.codigoDeEmergencia)) {
            return this.currentDateTime.compareTo(paciente2.currentDateTime);
        } else {
            return this.codigoDeEmergencia.compareTo(paciente2.codigoDeEmergencia);
        }
    }

    /**
     * Devuelve una representación en forma de cadena del paciente.
     *
     * @return Una cadena que contiene el nombre, la descripción del síntoma y el código de emergencia.
     */
    @Override
    public String toString() {
        return nombreDelPaciente + ", " + descripcionDelSintoma + ", " + codigoDeEmergencia;
    }
}