package modelo.mascotas;

import modelo.personas.Propietario;

public abstract class Mascota {
    private final String nombre;
    private int edad;
    private float peso;
    private final Propietario propietario;

    /**
     * Metodo constructor de la mascota
     *
     * @param nombre      de la mascota
     * @param edad        de la mascota
     * @param peso        de la mascota
     * @param propietario de la mascota
     * @throws IllegalArgumentException si el nombre o el propietario estan vacios
     */
    public Mascota(String nombre, int edad, float peso, Propietario propietario) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
        setEdad(edad);
        setPeso(peso);
        if (propietario == null) {
            throw new IllegalArgumentException("El propietario no puede estar vacio");
        }
        this.propietario = propietario;
    }

    /**
     * Metodo para agregar la de edad a una mascota
     *
     * @param edad de la mascota
     * @throws IllegalArgumentException si la edad es 0 o negativo
     */
    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad no puede ser 0 o negativo");
        }
        this.edad = edad;
    }

    /**
     * Metodo para agregar el peso a una mascota
     *
     * @param peso de la mascota
     * @throws IllegalArgumentException si el peso es 0 o negativo
     */
    public void setPeso(float peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso no puede ser 0 o negativa");
        }
        this.peso = peso;
    }

    /**
     * Metodo para ver el nombre de la mascota
     *
     * @return nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para ver la edad de la mascota
     *
     * @return edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo para ver el peso de la mascota
     *
     * @return peso de la mascota
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Metodo para ver el propietario de la mascota
     *
     * @return propietario de la mascota
     */
    public Propietario getPropietario() {
        return propietario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", propietario=" + propietario +
                '}';
    }

    /**
     * Metodo que contiene el sonido de la mascota
     */
    public abstract void emitirSonido();
}
