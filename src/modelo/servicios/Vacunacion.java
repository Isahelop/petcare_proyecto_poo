package modelo.servicios;

public class Vacunacion implements Facturable {
    private double costoVacuna;
    private double costoAplicacion;

    /**
     * Metodo constructor de vacunacion
     *
     * @param costoVacuna
     * @param costoAplicacion
     */
    public Vacunacion(double costoVacuna, double costoAplicacion) {
        setCostoVacuna(costoVacuna);
        setCostoAplicacion(costoAplicacion);
    }

    /**
     * Metodo para agregar costo de vacuna
     *
     * @param costoVacuna
     * @throws IllegalArgumentException si el costo de la vacuna es 0 o negativo
     */
    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna <= 0) {
            throw new IllegalArgumentException("El costo de la vacuna no puede ser 0 ni negativo");
        }
        this.costoVacuna = costoVacuna;
    }

    /**
     * Metodo para agregar costo de aplicacion de la vacuna
     *
     * @param costoAplicacion
     * @throws IllegalArgumentException si es costo de aplicacion es negativo
     */
    public void setCostoAplicacion(double costoAplicacion) {
        if (costoAplicacion <= 0) {
            throw new IllegalArgumentException("El costo de aplicacion no puede ser negativo ");
        }
        this.costoAplicacion = costoAplicacion;
    }

    @Override
    public double calcularCosto() {
        return costoAplicacion + costoVacuna;
    }
}
