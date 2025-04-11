import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Implementación de un heap mínimo utilizando un {@code ArrayList}.
 * Esta clase extiende {@code PriorityQueue} y permite gestionar elementos
 * en un orden basado en su prioridad.
 *
 * @param <T> El tipo de elementos que se almacenarán en el heap. Debe implementar {@code Comparable}.
 */
public class VectorHeap<T extends Comparable<T>> extends PriorityQueue<T> {
    /**
     * Lista que almacena los elementos del heap.
     * El índice 0 no se utiliza para simplificar los cálculos de índices.
     */
    protected ArrayList<T> pacientes;

    /**
     * Constructor que inicializa el heap vacío.
     */
    public VectorHeap() {
        pacientes = new ArrayList<>();
        pacientes.add(null); // Índice 0 reservado.
    }

    /**
     * Calcula el índice del padre de un nodo.
     *
     * @param i El índice del nodo hijo.
     * @return El índice del nodo padre.
     */
    protected static int padre(int i) {
        return i / 2;
    }

    /**
     * Calcula el índice del hijo izquierdo de un nodo.
     *
     * @param i El índice del nodo padre.
     * @return El índice del hijo izquierdo.
     */
    protected static int izquierda(int i) {
        return 2 * i;
    }

    /**
     * Calcula el índice del hijo derecho de un nodo.
     *
     * @param i El índice del nodo padre.
     * @return El índice del hijo derecho.
     */
    protected static int derecha(int i) {
        return 2 * i + 1;
    }

    /**
     * Agrega un nuevo elemento al heap y lo reordena para mantener la propiedad del heap mínimo.
     *
     * @param nuevoPaciente El elemento a agregar.
     * @return Un mensaje indicando que el elemento fue agregado.
     */
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

    /**
     * Elimina y devuelve el elemento con mayor prioridad (el menor) del heap.
     *
     * @return El elemento con mayor prioridad, o {@code null} si el heap está vacío.
     */
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

    /**
     * Devuelve el elemento con mayor prioridad (el menor) sin eliminarlo del heap.
     *
     * @return El elemento con mayor prioridad, o {@code null} si el heap está vacío.
     */
    public T verPacienteConMasPrioridad() {
        if (pacientes.size() <= 1) {
            return null;
        }
        return pacientes.get(1);
    }

    /**
     * Agrega un nuevo elemento al heap utilizando el método {@code agregar}.
     *
     * @param nuevoPaciente El elemento a agregar.
     * @return {@code true} si el elemento fue agregado correctamente.
     */
    @Override
    public boolean add(T nuevoPaciente) {
        return agregar(nuevoPaciente) != null;
    }

    /**
     * Elimina y devuelve el elemento con mayor prioridad utilizando el método {@code eliminarPacienteConMasPrioridad}.
     *
     * @return El elemento con mayor prioridad, o {@code null} si el heap está vacío.
     */
    @Override
    public T remove() {
        return eliminarPacienteConMasPrioridad();
    }

    /**
     * Devuelve el elemento con mayor prioridad utilizando el método {@code verPacienteConMasPrioridad}.
     *
     * @return El elemento con mayor prioridad, o {@code null} si el heap está vacío.
     */
    @Override
    public T peek() {
        return verPacienteConMasPrioridad();
    }

    /**
     * Devuelve una copia de la lista de elementos en el heap, excluyendo el índice 0.
     *
     * @return Una lista con los elementos del heap.
     */
    public ArrayList<T> obtenerPacientes() {
        ArrayList<T> copiaPacientes = new ArrayList<>(pacientes);
        copiaPacientes.remove(0);
        return copiaPacientes;
    }
}