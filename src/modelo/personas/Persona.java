package modelo.personas;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    /**
     *
     * @param nombre
     * @param apellido
     * @param telefono
     * @param correo
     */
    public Persona(String nombre, String apellido, String telefono, String correo){
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    /**
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
     *
     * @return el telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @return el correo de la persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @param nombre
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio ");
        }
        this.nombre = nombre;
    }

    /**
     *
     * @param apellido
     * @throws IllegalArgumentException si el apellido esta vacio
     */
    public void setApellido(String apellido) {
        if(apellido == null || apellido.isBlank()){
            throw new IllegalArgumentException("El apellido no puede estar vacio ");
        }
        this.apellido = apellido;
    }

    /**
     *
     * @param telefono
     * @throws IllegalArgumentException si el telefono esta vacio
     */
    public void setTelefono(String telefono) {
        if(telefono == null || telefono.isBlank()){
            throw new IllegalArgumentException("El telefono no puede estar vacio ");
        }
        this.telefono = telefono;
    }

    /**
     *
     * @param correo
     * @throws IllegalArgumentException si el correo esta vacio
     */
    public void setCorreo(String correo) {
        if(correo == null || correo.isBlank()){
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
