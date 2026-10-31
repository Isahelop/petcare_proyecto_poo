package modelo.mascotas;

import modelo.personas.Propietario;

public class Gato extends Mascota {
    private final boolean esDomestico;

    /**
     * Metodo constructor del gato
     *
     * @param nombre      del gato
     * @param edad        del gato
     * @param peso        del gato
     * @param propietario del gato
     * @param esDomestico true si es domestico
     */
    public Gato(String nombre, int edad, float peso, Propietario propietario, boolean esDomestico) {
        super(nombre, edad, peso, propietario);
        this.esDomestico = esDomestico;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "esDomestico=" + esDomestico +
                "} " + super.toString();
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + "Sonido: Miauw");
    }
}
