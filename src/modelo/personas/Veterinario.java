package modelo.personas;

public class Veterinario extends Persona {
    private final String cedulaProfesional;
    private final String especialidad;

    /**
     * Metodo constructor del veterinario
     *
     * @param nombre            del veterinario
     * @param apellido          del veterinario
     * @param telefono          del veterinario
     * @param correo            del veterinario
     * @param cedulaProfesional del veterinario
     * @param especialidad      del veterinario
     * @throws IllegalArgumentException si la cedula profesional o la especialidad esta vacia
     */
    public Veterinario(String nombre, String apellido, String telefono, String correo,
                       String cedulaProfesional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        if (cedulaProfesional == null || cedulaProfesional.isBlank()) {
            throw new IllegalArgumentException("La cedula profesional no puede estar vacia");
        }
        this.cedulaProfesional = cedulaProfesional;

        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacia");
        }
        this.especialidad = especialidad;
    }

    /**
     * Metodo permite acceder a la cedula del veterinario
     *
     * @return cedulaProfesional del veterinario
     */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    @Override
    public void describirFuncion() {
        System.out.println("Su funcion es atender la citas de las mascotas que lleguen a la clinica");
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "cedulaProfesional='" + cedulaProfesional + '\'' +
                ", especialidad='" + especialidad + '\'' +
                "} " + super.toString();
    }
}
