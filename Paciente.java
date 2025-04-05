import java.time.LocalDateTime;

class Paciente implements Comparable<Paciente> {
    private final String nombreDelPaciente;
    private final String descripcionDelSintoma;
    private final String codigoDeEmergencia;
    private LocalDateTime currentDateTime = LocalDateTime.now();

    public Paciente(String nombreDelPaciente, String descripcionDelSintoma, String codigoDeEmergencia, LocalDateTime currentDateTime) {
        this.nombreDelPaciente = nombreDelPaciente;
        this.descripcionDelSintoma = descripcionDelSintoma;
        this.codigoDeEmergencia = codigoDeEmergencia;
        this.currentDateTime = currentDateTime;
    }

    public String getNombreDelPaciente() {
        return nombreDelPaciente;
    }
    
    public String getDescripcionDelSintoma() {
        return descripcionDelSintoma;
    }
    
    public String getCodigoDeEmergencia() {
        return codigoDeEmergencia;
    }
    
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    @Override
    public int compareTo(Paciente paciente2) {
        if (this.codigoDeEmergencia.equals(paciente2.codigoDeEmergencia)) {
            return this.currentDateTime.compareTo(paciente2.currentDateTime);
        } else {
            return this.codigoDeEmergencia.compareTo(paciente2.codigoDeEmergencia);
        }
    }

    @Override
    public String toString() {
        return nombreDelPaciente + ", " + descripcionDelSintoma + ", " + codigoDeEmergencia;
    }
}