package modelo.clinica;


public class Comprobante {
    //cuenta los comprobantes de toda la clase, no de uno en particular
    private static int totalComprobantes;
    private final Cita cita;
    private final double total;

    /**
     * Metodo constructor para el comprobante
     *
     * @param cita del comprobante
     * @throws IllegalArgumentException si la cita esta vacia o no esta atendida
     */
    public Comprobante(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede estar vacia");
        }
        if (cita.getEstado() != Estado.ATENDIDA) {
            throw new IllegalArgumentException("La cita debe de estar atendida");
        }
        this.cita = cita;
        total = cita.getServicio().calcularCosto();
        totalComprobantes += 1;
    }

    /**
     * Metodo para ver el precio total de la cita
     *
     * @return total en el comprobante
     */
    public double getTotal() {
        return total;
    }

    /**
     * Metodo para ver el total de comprobantes
     *
     * @return total de comprobantes
     */
    public static int getTotalComprobantes() {
        return totalComprobantes;
    }

    /**
     * Metodo para ver la cita del comprobante
     *
     * @return cita del comprobante
     */
    public Cita getCita() {
        return cita;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "Mascota=" + cita.getMascota().getNombre() +
                "Veterinario=" + cita.getVeterinario().getNombre() +
                ", total=" + total +
                '}';
    }
}
