package modelo.personas;

public class Recepcionista extends Persona {
    private String turno;

    /**
     * Metodo constructor del recepcionista
     *
     * @param nombre   del recepcionista
     * @param apellido del recepcionista
     * @param telefono del recepcionista
     * @param correo   del recepcionista
     * @param turno    del recepcionista
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
    }

    /**
     * Metodo para agregar un turno a una persona
     *
     * @param turno del recepcionista
     * @throws IllegalArgumentException si el turno esta vacio
     */
    public void setTurno(String turno) {
        if (turno == null || turno.isBlank()) {
            throw new IllegalArgumentException("El turno no puede estar vacio");
        }
        this.turno = turno;
    }

    @Override
    public void describirFuncion() {
        System.out.println("Su funcion es atender a los propietarios que lleguen a la clinica");
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                "turno='" + turno + '\'' +
                "} " + super.toString();
    }
}
