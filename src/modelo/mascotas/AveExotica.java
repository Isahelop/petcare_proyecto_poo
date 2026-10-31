package modelo.mascotas;

import modelo.personas.Propietario;

public class AveExotica extends Mascota {
    private float longitudAlas;

    /**
     * Metodo constructor de ave exotica
     *
     * @param nombre       de ave exotica
     * @param edad         de ave exotica
     * @param peso         de ave exotica
     * @param propietario  de ave exotica
     * @param longitudAlas de ave exotica
     */
    public AveExotica(String nombre, int edad, float peso, Propietario propietario, float longitudAlas) {
        super(nombre, edad, peso, propietario);
        setLongitudAlas(longitudAlas);
    }

    /**
     * Metodo para agregar longitud de alas a la ave exotica
     *
     * @param longitudAlas del ave exotica
     * @throws IllegalArgumentException si la longitud es 0 o negativa
     */
    public void setLongitudAlas(float longitudAlas) {
        if (longitudAlas <= 0) {
            throw new IllegalArgumentException("La longitud de las alas no puede ser 0 o negativa");
        }
        this.longitudAlas = longitudAlas;
    }

    @Override
    public String toString() {
        return "AveExotica{" +
                "longitudAlas=" + longitudAlas +
                "} " + super.toString();
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + "Sonido: Fiuuuuu");
    }
}
