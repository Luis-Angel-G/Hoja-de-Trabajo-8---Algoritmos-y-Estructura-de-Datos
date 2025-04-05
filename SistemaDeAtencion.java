import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SistemaDeAtencion {
    private final VectorHeap<Paciente> pacientes = new VectorHeap<>();
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
                        pacientes.agregar(paciente);
                    } catch (Exception e) {
                        System.out.println("Error al procesar la línea: " + linea + ". Detalle: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public void guardarFichasDePacientes() {
        File archivo = new File("pacientes.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            if (!archivo.exists()) {
                System.out.println("No existe el archivo de las fichas, creando uno.");
                archivo.createNewFile();
            }
            StringBuilder contenido = new StringBuilder();
            for (Paciente paciente : pacientes) {
                contenido.append(paciente.getNombreDelPaciente()).append(",")
                        .append(paciente.getDescripcionDelSintoma()).append(",")
                        .append(paciente.getCodigoDeEmergencia()).append(",")
                        .append(paciente.getCurrentDateTime().format(formatter)).append("\n");
            }
            java.nio.file.Files.writeString(archivo.toPath(), contenido.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SistemaDeAtencion sistema = new SistemaDeAtencion();
        sistema.cargarFichaDePacientes();
        try (Scanner scanner = new Scanner(System.in)) {
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
                            System.out.println(sistema.pacientes.agregar(nuevoPaciente));
                        } catch (Exception e) {
                            System.out.println("Error al agregar el paciente: " + e.getMessage());
                        }
                        sistema.guardarFichasDePacientes();
                    }
                    case 2 -> {
                        String pacienteEliminado = sistema.pacientes.eliminarPacienteConMasPrioridad();
                        if (pacienteEliminado != null) {
                            System.out.println("Paciente eliminado: " + pacienteEliminado);
                        } else {
                            System.out.println("No hay pacientes en la lista.");
                        }
                        sistema.guardarFichasDePacientes();
                    }
                    case 3 -> {
                        String pacienteConMasPrioridad = sistema.pacientes.verPacienteConMasPrioridad();
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