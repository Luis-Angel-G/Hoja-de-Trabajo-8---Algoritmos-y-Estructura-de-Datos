import java.util.ArrayList;
import java.util.PriorityQueue;

public class VectorHeap<T extends Comparable<T>> extends PriorityQueue<T> {
    protected ArrayList<T> pacientes;

    public VectorHeap() {
        pacientes = new ArrayList<>();
        pacientes.add(null);
    }

    protected static int padre(int i) {
        return i / 2;
    }

    protected static int izquierda(int i) {
        return 2 * i;
    }

    protected static int derecha(int i) {
        return 2 * i + 1;
    }

    public String agregar(T nuevoPaciente) {
        pacientes.add(nuevoPaciente);
        int posicion = pacientes.size() - 1;
        while (posicion > 1 && pacientes.get(posicion).compareTo(pacientes.get(padre(posicion))) < 0) {
            T pacienteConMasPrioridad = pacientes.get(posicion);
            pacientes.set(posicion, pacientes.get(padre(posicion)));
            pacientes.set(padre(posicion), pacienteConMasPrioridad);
            posicion = padre(posicion);
        }
        return "Paciente agregado: " + nuevoPaciente.toString();
    }

    public T eliminarPacienteConMasPrioridad() {
        if (pacientes.size() <= 1) {
            return null;
        }
        T pacienteEliminado = pacientes.get(1);
        pacientes.set(1, pacientes.get(pacientes.size() - 1));
        pacientes.remove(pacientes.size() - 1);
        int posicion = 1;
        while (izquierda(posicion) < pacientes.size()) {
            int izquierdo = izquierda(posicion);
            int derecho = derecha(posicion);
            int hijoMenor = izquierdo;

            if (derecho < pacientes.size() && pacientes.get(derecho).compareTo(pacientes.get(izquierdo)) < 0) {
                hijoMenor = derecho;
            }

            if (pacientes.get(posicion).compareTo(pacientes.get(hijoMenor)) > 0) {
                T pacienteConMenosPrioridad = pacientes.get(posicion);
                pacientes.set(posicion, pacientes.get(hijoMenor));
                pacientes.set(hijoMenor, pacienteConMenosPrioridad);
                posicion = hijoMenor;
            } else {
                break;
            }
        }
        return pacienteEliminado;
    }

    public T verPacienteConMasPrioridad() {
        if (pacientes.size() <= 1) {
            return null;
        }
        return pacientes.get(1);
    }

    @Override
    public boolean add(T nuevoPaciente) {
        return agregar(nuevoPaciente) != null;
    }

    @Override
    public T remove() {
        return eliminarPacienteConMasPrioridad();
    }

    @Override
    public T peek() {
        return verPacienteConMasPrioridad();
    }

    public ArrayList<T> obtenerPacientes() {
        ArrayList<T> copiaPacientes = new ArrayList<>(pacientes);
        copiaPacientes.remove(0);
        return copiaPacientes;
    }
}