public class PriorityQueue<E> {
    PriorityQueue<Paciente> pacientes = new PriorityQueue<>();

    public boolean add(Paciente nuevoPaciente) {
        return pacientes.add(nuevoPaciente);
    }
    public E remove() {
        return (E) pacientes.remove();
    }
    public E peek() {
        return (E) pacientes.peek();
    }
}