public class PriorityQueue<E> {
    PriorityQueue<Paciente> pacientes = new PriorityQueue<>();

    public boolean add(Paciente nuevoPaciente) {
        return pacientes.add(nuevoPaciente);
    }
    public Paciente remove() {
        return pacientes.remove();
    }
    public Paciente peek() {
        return pacientes.peek();
    }
}