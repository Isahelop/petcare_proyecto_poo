package modelo.clinica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorialClinico {
    private List<Cita> citasAtendidas;

    /**
     * Metodo constructor sin parametros del historial
     * se crea la lista dentro del constructor
     */
    public HistorialClinico() {
        citasAtendidas = new ArrayList<>();
    }

    /**
     * Metodo para agregar una cita atendida al historial
     *
     * @param cita para el historial de la cita
     * @throws IllegalArgumentException si la cita esta vacia, no esta atendida
     *                                  o ya esta en la lista
     */
    public void agregarCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede estar vacia");
        }
        if (cita.getEstado() != Estado.ATENDIDA) {
            throw new IllegalArgumentException("La cita no esta atendida");
        }
        if (citasAtendidas.contains(cita)) {
            throw new IllegalArgumentException("La cita ya esta en la lista");
        }
        citasAtendidas.add(cita);
    }

    /**
     * Metodo para ver el historial de citas atendidas
     *
     * @return citas atendidas
     */
    public List<Cita> getCitasAtendidas() {
        return Collections.unmodifiableList(citasAtendidas);
    }

    @Override
    public String toString() {
        return "HistorialClinico{" +
                "citasAtendidas=" + citasAtendidas.size() +
                '}';
    }
}
