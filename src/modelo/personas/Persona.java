package modelo.personas;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    /**
     * Metodo constructor
     *
     * @param nombre   de la persona
     * @param apellido de la persona
     * @param telefono de la persona
     * @param correo   de la persona
     */
    public Persona(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    /**
     * Metodo permite acceder  al nombre de la persona
     *
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return el apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo permite acceder  al telefono de la persona
     *
     * @return el telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo permite acceder  al correo de la persona
     *
     * @return el correo de la persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Metodo para agregar nombre a una persona
     *
     * @param nombre de la persona
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
    }

    /**
     * Metodo para agregar un apellido a una persona
     *
     * @param apellido de la persona
     * @throws IllegalArgumentException si el apellido esta vacio
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        this.apellido = apellido;
    }

    /**
     * Metodo para agregar un telefono a la persona
     *
     * @param telefono de la persona
     * @throws IllegalArgumentException si el telefono esta vacio
     */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
        this.telefono = telefono;
    }

    /**
     * Metodo para agregar un correo a la persona
     *
     * @param correo de la persona
     * @throws IllegalArgumentException si el correo esta vacio
     */
    public void setCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacio");
        }
        this.correo = correo;
    }

    public abstract void describirFuncion();

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
