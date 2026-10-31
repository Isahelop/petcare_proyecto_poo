package modelo.mascotas;

import modelo.personas.Propietario;

public class Perro extends Mascota {
    private final String raza;
    private int nivelEnergia;

    /**
     * Metodo constructor del perro
     *
     * @param nombre       del perro
     * @param edad         del perro
     * @param peso         del perro
     * @param propietario  del perro
     * @param raza         del perro
     * @param nivelEnergia del perro
     * @throws IllegalArgumentException si la raza esta vacia
     */
    public Perro(String nombre, int edad, float peso, Propietario propietario, String raza, int nivelEnergia) {
        super(nombre, edad, peso, propietario);
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("La raza no puede estar vacia");
        }
        this.raza = raza;
        setNivelEnergia(nivelEnergia);
    }

    /**
     * Metodo para agregar nivel de energia a un perro
     *
     * @param nivelEnergia del perro
     * @throws IllegalArgumentException si el nivel de energia esta vacio
     */
    public void setNivelEnergia(int nivelEnergia) {
        if (nivelEnergia < 0) {
            throw new IllegalArgumentException("El nivel de energia no puede ser negativo");
        }
        this.nivelEnergia = nivelEnergia;
    }

    @Override
    public void EmitirSonido() {
        System.out.println("Sonido: Guaaaao");
    }
}
