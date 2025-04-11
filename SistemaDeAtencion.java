import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.Scanner;

/**
 * Clase principal que gestiona el sistema de atención de pacientes.
 * Permite cargar, guardar y gestionar pacientes en una cola de prioridad.
 */
public class SistemaDeAtencion {
    private final Queue<Paciente> pacientes;

    /**
     * Constructor que inicializa el sistema con una cola de prioridad específica.
     *
     * @param cola El tipo de cola a utilizar: "priorityqueue" o "vectorheap".
     */
    public SistemaDeAtencion(String cola) {
        this.pacientes = QueueFactory.getQueue(cola);
    }

    /**
     * Carga las fichas de pacientes desde un archivo llamado "pacientes.txt".
     * Si el archivo no existe, lo crea vacío.
     * Los datos deben estar en el formato: nombre, descripción, código de emergencia, fecha/hora.
     */
    public void cargarFichaDePacientes() {
        File archivo = new File("pacientes.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            if (!archivo.exists()) {
                System.out.println("No existe el archivo de las fichas, creando uno.");
                archivo.createNewFile();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        String[] datosDeFicha = linea.split(",");
                        if (datosDeFicha.length != 4) {
                            System.out.println("Línea con formato incorrecto: " + linea);
                            continue;
                        }
                        String nombreDelPaciente = datosDeFicha[0].trim();
                        String descripcionDelSintoma = datosDeFicha[1].trim();
                        String codigoDeEmergencia = datosDeFicha[2].trim();
                        LocalDateTime currentDateTime = LocalDateTime.parse(datosDeFicha[3].trim(), formatter);
                        Paciente paciente = new Paciente(nombreDelPaciente, descripcionDelSintoma, codigoDeEmergencia, currentDateTime);
                        pacientes.add(paciente);
                    } catch (Exception e) {
                        System.out.println("Error al procesar la línea: " + linea + ". Detalle: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda las fichas de pacientes en un archivo llamado "pacientes.txt".
     * Los datos se guardan en el formato: nombre, descripción, código de emergencia, fecha/hora.
     */
    public void guardarFichasDePacientes() {
        File archivo = new File("pacientes.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            if (!archivo.exists()) {
                System.out.println("No existe el archivo de las fichas, creando uno.");
                archivo.createNewFile();
            }
            StringBuilder contenido = new StringBuilder();
    
            if (pacientes instanceof VectorHeap) {
                VectorHeap<Paciente> vectorHeap = (VectorHeap<Paciente>) pacientes;
                for (Paciente paciente : vectorHeap.obtenerPacientes()) {
                    contenido.append(paciente.getNombreDelPaciente()).append(",")
                             .append(paciente.getDescripcionDelSintoma()).append(",")
                             .append(paciente.getCodigoDeEmergencia()).append(",")
                             .append(paciente.getCurrentDateTime().format(formatter)).append("\n");
                }
            } else {
                for (Paciente paciente : pacientes) {
                    contenido.append(paciente.getNombreDelPaciente()).append(",")
                             .append(paciente.getDescripcionDelSintoma()).append(",")
                             .append(paciente.getCodigoDeEmergencia()).append(",")
                             .append(paciente.getCurrentDateTime().format(formatter)).append("\n");
                }
            }
    
            java.nio.file.Files.writeString(archivo.toPath(), contenido.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Método principal que ejecuta el sistema de atención de pacientes.
     * Permite al usuario interactuar con el sistema mediante un menú.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el número de cola a usar:");
            System.out.println("1. PriorityQueue");
            System.out.println("2. VectorHeap");
            int col = scanner.nextInt();
            scanner.nextLine();
            if (col < 1 || col > 2) {
                System.out.println("Opción no válida. Saliendo del programa.");
                return;
            }
            String cola = col == 1 ? "priorityqueue" : "vectorheap";
            SistemaDeAtencion sistema = new SistemaDeAtencion(cola);
            sistema.cargarFichaDePacientes();
            boolean continuar = true;
            int opcion;

            while (continuar) {
                System.out.println("Bienvenido al sistema de atención de pacientes. Seleccione una opción:");
                System.out.println("1. Agregar nuevo paciente");
                System.out.println("2. Atender paciente con más prioridad");
                System.out.println("3. Ver siguiente paciente a atender");
                System.out.println("4. Salir");

                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion < 1 || opcion > 4) {
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    continue;
                }
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Ingrese el nombre del paciente:");
                        String nombreDelPaciente = scanner.nextLine();
                        System.out.println("Ingrese la descripción del síntoma:");
                        String descripcionDelSintoma = scanner.nextLine();
                        System.out.println("Ingrese el código de emergencia, de A a E:");
                        try {
                            String codigoDeEmergencia = scanner.nextLine().toUpperCase();
                            if (!codigoDeEmergencia.matches("[A-E]")) {
                                System.out.println("Código de emergencia no válido. Debe ser de A a E.");
                                continue;
                            }
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            Paciente nuevoPaciente = new Paciente(nombreDelPaciente, descripcionDelSintoma, codigoDeEmergencia, currentDateTime);
                            System.out.println(sistema.pacientes.add(nuevoPaciente));
                        } catch (Exception e) {
                            System.out.println("Error al agregar el paciente: " + e.getMessage());
                        }
                        sistema.guardarFichasDePacientes();
                    }
                    case 2 -> {
                        Paciente pacienteEliminado = sistema.pacientes.remove();
                        if (pacienteEliminado != null) {
                            System.out.println("Paciente eliminado: " + pacienteEliminado);
                        } else {
                            System.out.println("No hay pacientes en la lista.");
                        }
                        sistema.guardarFichasDePacientes();
                    }
                    case 3 -> {
                        Paciente pacienteConMasPrioridad = sistema.pacientes.peek();
                        if (pacienteConMasPrioridad != null) {
                            System.out.println(pacienteConMasPrioridad);
                        } else {
                            System.out.println("No hay pacientes en la lista.");
                        }
                    }
                    case 4 -> {
                        continuar = false;
                        System.out.println("Saliendo del sistema. Hasta luego.");
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }
}