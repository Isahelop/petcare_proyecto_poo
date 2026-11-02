package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Propietario;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clinica {
    private final String nombre;
    private List<Consultorio> consultorios;
    private List<Veterinario> veterinarios;
    private List<Propietario> propietarios;
    private List<Comprobante> comprobantes;
    private List<Cita> citas;

    /**
     * Metodo constructor de la clinica
     *
     * @param nombre de la clinica
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public Clinica(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.propietarios = new ArrayList<>();
        this.comprobantes = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    /**
     * Metodo para agregar un consultorio a la clinica
     *
     * @param numero del consultorio a agregar
     * @throws IllegalArgumentException si el consultorio ya fue agregado
     */
    public void agregarConsultorio(int numero) {
        for (Consultorio c : consultorios) {
            if (c.getNumero() == numero) {
                throw new IllegalArgumentException("El consultorio ya existe");
            }
        }
        consultorios.add(new Consultorio(numero));
    }

    /**
     * Metodo auxiliar para comparar veterinarios por cédula
     */
    private boolean mismoVeterinario(Veterinario a, Veterinario b) {
        if (a == null || b == null) return false;
        return a.getCedulaProfesional().equals(b.getCedulaProfesional());
    }

    /**
     * Metodo para agregar un veterinario a la clinica
     *
     * @param veterinario de la clinica
     * @throws IllegalArgumentException si el veterinario esta vacio o ya fue agregado
     */
    public void agregarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede estar vacio");
        }
        for (Veterinario v : veterinarios) {
            if (mismoVeterinario(v, veterinario)) {
                throw new IllegalArgumentException("El veterinario ya fue agregado");
            }
        }
        veterinarios.add(veterinario);
    }

    /**
     * Metodo para agregar un propietario a la clinica
     *
     * @param propietario de la clinica
     * @throws IllegalArgumentException si el propietario esta vacio o ya fue agregado
     */
    public void agregarPropietario(Propietario propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("El propietario no puede estar vacio");
        }
        if (propietarios.contains(propietario)) {
            throw new IllegalArgumentException("El propietario ya fue agregado");
        }
        propietarios.add(propietario);
    }

    /**
     * Metodo para agendar una cita
     *
     * @param cita de la clinica
     * @throws IllegalArgumentException si la cita esta vacia, el veterinario no esta registrado,
     *                                  el consultorio no esta registrado, el veterinario esta ocupado en ese horario
     *                                  o el consultorio esta ocupado en ese horario
     */
    public void agendarCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede estar vacia");
        }
        if (cita.getEstado() != Estado.PROGRAMADA) {
            throw new IllegalArgumentException("No se puede agendar una cita cancelada");
        }
        if (!obtenerMascotas().contains(cita.getMascota())) {
            throw new IllegalArgumentException("La mascota no pertenece a esta clínica");
        }

        boolean vetRegistrado = false;
        for (Veterinario v : veterinarios) {
            if (mismoVeterinario(v, cita.getVeterinario())) {
                vetRegistrado = true;
                break;
            }
        }
        if (!vetRegistrado) {
            throw new IllegalArgumentException("El veterinario no está registrado en esta clínica");
        }

        if (!consultorios.contains(cita.getConsultorio())) {
            throw new IllegalArgumentException("El consultorio no pertenece a esta clínica");
        }
        if (veterinarioOcupado(cita.getVeterinario(), cita.getFecha(), cita.getHora())) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita programada en ese horario");
        }
        if (consultorioOcupado(cita.getConsultorio(), cita.getFecha(), cita.getHora())) {
            throw new IllegalArgumentException("El consultorio ya está ocupado en ese horario");
        }

        citas.add(cita);
    }

    /**
     * Metodo para saber si el veterinario esta ocupado
     *
     * @param veterinario de la cita
     * @param fecha       de la cita
     * @param hora        de la cita
     * @return verdadero o falso
     */
    private boolean veterinarioOcupado(Veterinario veterinario, LocalDate fecha, LocalTime hora) {
        for (Cita c : citas) {
            if (c.getEstado() == Estado.PROGRAMADA &&
                    mismoVeterinario(c.getVeterinario(), veterinario) &&
                    c.getFecha().equals(fecha) &&
                    c.getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para saber si el consultorio esta ocupado
     *
     * @param consultorio a ocupar para la cita
     * @param fecha       de la cita
     * @param hora        de la cita
     * @return verdadero o falso
     */
    private boolean consultorioOcupado(Consultorio consultorio, LocalDate fecha, LocalTime hora) {
        for (Cita c : citas) {
            if (c.getEstado() == Estado.PROGRAMADA &&
                    c.getConsultorio().equals(consultorio) &&
                    c.getFecha().equals(fecha) &&
                    c.getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para atender una cita
     *
     * @param cita     de la clinica
     * @param servicio tipo de servicio de la cita
     * @throws IllegalArgumentException si la cita no esta registrada en la clinica
     */
    public void atenderCita(Cita cita, Facturable servicio) {
        if (!citas.contains(cita)) {
            throw new IllegalArgumentException("La cita no está registrada en esta clínica");
        }
        cita.atenderCita(servicio);
        cita.getMascota().getHistorialClinico().agregarCita(cita);
    }


    /**
     * Metodo para agregar comprobante a la clinica
     *
     * @param comprobante a agregar a la clinica
     * @throws IllegalArgumentException si el comprobante es nulo o la cita ya fue agregada
     */
    public void agregarComprobante(Comprobante comprobante) {
        if (comprobante == null) {
            throw new IllegalArgumentException("El comprobante no puede ser nulo");
        }
        if (!citas.contains(comprobante.getCita())) {
            throw new IllegalArgumentException("La cita del comprobante no pertenece a esta clínica");
        }
        for (Comprobante comp : comprobantes) {
            if (comp.getCita().equals(comprobante.getCita())) {
                throw new IllegalArgumentException("La cita de este comprobante ya ha sido agregada");
            }
        }
        comprobantes.add(comprobante);
    }

    /**
     * Metodo para calcular el total por veterinario
     *
     * @param veterinario a saber el total cobrado
     * @return total de los servicios por veterinario
     */
    public double calcularTotalCobrado(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        List<Facturable> servicios = new ArrayList<>();

        for (Comprobante c : comprobantes) {
            if (mismoVeterinario(c.getCita().getVeterinario(), veterinario)) {
                servicios.add(c.getCita().getServicio());
            }
        }
        return sumarCostosServicios(servicios);
    }

    /**
     * Metodo para calcular el total por mascota
     *
     * @param mascota a saber el total cobrado
     * @return total de los servicios por mascota
     */
    public double calcularTotalCobrado(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        List<Facturable> servicios = new ArrayList<>();

        for (Comprobante c : comprobantes) {
            if (c.getCita().getMascota() == mascota) {
                servicios.add(c.getCita().getServicio());
            }
        }
        return sumarCostosServicios(servicios);
    }

    /**
     * Metodo que suma todos los servicios atendidos
     *
     * @param servicios lista
     * @return total de los servicios
     */
    private double sumarCostosServicios(List<Facturable> servicios) {
        double total = 0.0;
        for (Facturable f : servicios) {
            if (f != null) {
                total += f.calcularCosto();
            }
        }
        return total;
    }

    /**
     * Metodo para obtener todas las mascotas de la clinica
     *
     * @return todas las mascotas
     */
    public List<Mascota> obtenerMascotas() {
        List<Mascota> todasLasMascotas = new ArrayList<>();
        for (Propietario p : propietarios) {
            todasLasMascotas.addAll(p.getMascotas());
        }
        return todasLasMascotas;
    }

    /**
     * Metodo para dar de baja a una mascota
     * <p>
     * Nota sobre el Recolector de Basura (Garbage Collector):
     * - El historial clínico se destruye junto con la mascota (relación de composición).
     * - La mascota seguirá existiendo en memoria (el recolector no la liberará) mientras
     * las citas atendidas y los comprobantes mantengan una referencia hacia ella.
     *
     * @param mascota de un propietario de la clinica
     */
    public void darBajaMascota(Mascota mascota) {
        if (mascota == null || !obtenerMascotas().contains(mascota)) {
            throw new IllegalArgumentException("La mascota es nula o no pertenece a la clínica");
        }

        for (Cita c : citas) {
            if (c.getEstado() == Estado.PROGRAMADA && c.getMascota() == mascota) {
                c.cancelarCita();
            }
        }

        mascota.getPropietario().eliminarMascota(mascota);
    }

    /**
     * Metodo para buscar un consultorio
     *
     * @param numero
     * @return el consultorio buscado
     */
    public Consultorio buscarConsultorio(int numero) {
        for (Consultorio c : consultorios) {
            if (c.getNumero() == numero) return c;
        }
        throw new IllegalArgumentException("Consultorio no encontrado");
    }

    /**
     * Metodo para buscar un veterinario
     *
     * @param cedula del veterinario a buscar
     * @return el veterinario buscado
     */
    public Veterinario buscarVeterinario(String cedula) {
        for (Veterinario v : veterinarios) {
            if (v.getCedulaProfesional().equals(cedula)) return v;
        }
        throw new IllegalArgumentException("Veterinario no encontrado");
    }

    /**
     * Metodo para buscar una mascota
     *
     * @param nombre de la mascota a buscar
     * @return la mascota buscada
     */
    public Mascota buscarMascota(String nombre) {
        for (Mascota m : obtenerMascotas()) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        throw new IllegalArgumentException("Mascota no encontrada");
    }

    /**
     * Metodo para obtener todos los veterinarios de una clinica
     *
     * @return Lista no modificable de los veterinarios
     */
    public List<Veterinario> obtenerVeterinarios() {
        return Collections.unmodifiableList(veterinarios);
    }

    /**
     * Metodo para obtener todas las citas de una clinica
     *
     * @return Lista no modificable de las citas
     */
    public List<Cita> obtenerCitas() {
        return Collections.unmodifiableList(citas);
    }

    /**
     * Metodo para obtener el nombre de la clinica
     *
     * @return el nombre de la clinica
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para buscar un propietario ya registrado por su telefono
     *
     * @param telefono el numero de telefono del propietario
     * @return el propietario encontrado
     * @throws IllegalArgumentException si no se encuentra ningun propietario con ese telefono
     */
    public Propietario buscarPropietario(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
        for (Propietario p : propietarios) {
            // Asume que Propietario hereda getTelefono() de la clase abstracta Persona
            if (p.getTelefono().equals(telefono)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Propietario no encontrado");
    }

    /**
     * Metodo para obtener todos los propietarios de la clinica
     *
     * @return una lista no modificable de los propietarios registrados
     */
    public List<Propietario> obtenerPropietarios() {
        return Collections.unmodifiableList(propietarios);
    }
}