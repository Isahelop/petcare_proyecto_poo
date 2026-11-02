package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private LocalDate fecha;
    private LocalTime hora;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Estado estado = Estado.PROGRAMADA;
    private Facturable servicio;

    /**
     * Metodo constructor que construye una nueva cita
     *
     * @param fecha       La fecha de la cita
     * @param hora        La hora de la cita
     * @param mascota     La mascota que recibirá la atención
     * @param veterinario El veterinario que atenderá la cita
     * @param consultorio El consultorio asignado para la cita
     */
    public Cita(LocalDate fecha, LocalTime hora, Mascota mascota,
                Veterinario veterinario, Consultorio consultorio) {
        setFecha(fecha);
        setHora(hora);
        setMascota(mascota);
        setVeterinario(veterinario);
        setConsultorio(consultorio);
    }

    /**
     * Metodo para agregar fecha a la cita
     *
     * @param fecha de la cita
     * @throws IllegalArgumentException si la fecha esta vacia
     */
    private void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        this.fecha = fecha;
    }

    /**
     * Metodo para agregar hora de la cita
     *
     * @param hora de la cita
     * @throws IllegalArgumentException si la hora esta vacia
     */
    private void setHora(LocalTime hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora no puede estar vacia");
        }
        this.hora = hora;
    }

    /**
     * Metodo para agregar mascota a la cita
     *
     * @param mascota de la cita
     * @throws IllegalArgumentException si la mascota esta vacia
     */
    private void setMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser vacia");
        }
        this.mascota = mascota;
    }

    /**
     * Metodo para agregar veterinario a la cita
     *
     * @param veterinario de la cita
     * @throws IllegalArgumentException si el veterinario esta vacio
     */
    private void setVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede estar vacio");
        }
        this.veterinario = veterinario;
    }

    /**
     * Metodo para asignar el consultorio a usar
     *
     * @param consultorio de la cita
     * @throws IllegalArgumentException si el consultorio esta vacio
     */
    private void setConsultorio(Consultorio consultorio) {
        if (consultorio == null) {
            throw new IllegalArgumentException("El consultorio no puede estar vacio");
        }
        this.consultorio = consultorio;
    }

    /**
     * Metodo para ver la fecha de la cita
     *
     * @return fecha de la cita
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Metodo para ver la hora de la cita
     *
     * @return hora de lacita
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Metodo para ver la mascota de la cita
     *
     * @return mascota de la cita
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Metodo para ver el veterinario de la cita
     *
     * @return veterinario de la cita
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Metodo para ver el consultorio de la cita
     *
     * @return consultorio de la cita
     */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /**
     * Metodo para ver el estado de la cita
     *
     * @return estado de la cita
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Metodo para ver el servicio de la cita
     *
     * @return servicio de la cita
     */
    public Facturable getServicio() {
        return servicio;
    }

    /**
     * Metodo que cambia el estado de la cita a atendida e indica que tipo de servicio es
     *
     * @param servicio de la cita
     * @throws IllegalArgumentException si la cita esta cancelada o si el servicio esta vacio
     */
    public void atenderCita(Facturable servicio) {
        if (this.estado == Estado.CANCELADA) {
            throw new IllegalArgumentException("No se atiende a una cita cancelada");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede estar vacio");
        }
        this.servicio = servicio;
        this.estado = Estado.ATENDIDA;
    }

    /**
     * Metodo que cambia el estado de la cita a cancelada
     *
     * @throws IllegalArgumentException si la cita esta atendida
     */
    public void cancelarCita() {
        if (estado == Estado.ATENDIDA) {
            throw new IllegalArgumentException("No se puede cancelar una cita atendida");
        }
        this.estado = Estado.CANCELADA;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "mascota=" + mascota +
                ", veterinario=" + veterinario +
                '}';
    }
}
