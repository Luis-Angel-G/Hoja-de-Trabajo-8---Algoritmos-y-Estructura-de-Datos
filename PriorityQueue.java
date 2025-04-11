/**
 * Implementación de una cola de prioridad genérica que gestiona elementos
 * en base a su prioridad. Utiliza una instancia de {@code PriorityQueue} para
 * almacenar y organizar los elementos.
 *
 * @param <E> El tipo de elementos que se almacenarán en la cola de prioridad.
 */
public class PriorityQueue<E> {
    /**
     * Cola de prioridad que almacena objetos de tipo {@code Paciente}.
     */
    PriorityQueue<Paciente> pacientes = new PriorityQueue<>();

    /**
     * Agrega un nuevo paciente a la cola de prioridad.
     *
     * @param nuevoPaciente El paciente a agregar.
     * @return {@code true} si el paciente fue agregado correctamente.
     */
    public boolean add(Paciente nuevoPaciente) {
        return pacientes.add(nuevoPaciente);
    }

    /**
     * Elimina y devuelve el paciente con mayor prioridad (el menor) de la cola.
     *
     * @return El paciente con mayor prioridad, o {@code null} si la cola está vacía.
     */
    public Paciente remove() {
        return pacientes.remove();
    }

    /**
     * Devuelve el paciente con mayor prioridad (el menor) sin eliminarlo de la cola.
     *
     * @return El paciente con mayor prioridad, o {@code null} si la cola está vacía.
     */
    public Paciente peek() {
        return pacientes.peek();
    }
}