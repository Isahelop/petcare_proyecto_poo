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
     */
    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad no puede ser 0 o negativa");
        }
        this.edad = edad;
    }

    /**
     * Metodo para agregar el peso a una mascota
     *
     * @param peso de la mascota
     */
    public void setPeso(float peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso no puede ser 0 o negativa");
        }
        this.peso = peso;
    }

    public abstract void EmitirSonido();
}
