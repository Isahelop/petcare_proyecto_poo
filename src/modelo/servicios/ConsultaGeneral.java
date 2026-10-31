package modelo.servicios;

public class ConsultaGeneral implements Facturable {
    private double tarifaBase;

    /**
     * Metodo constructor de la consulta general
     * @param tarifaBase
     */
    public ConsultaGeneral(double tarifaBase){
        setTarifaBase(tarifaBase);
    }

    /**
     * Metodo para agregar la tarifa base a una consulta general
     * @param tarifaBase
     * @throws IllegalArgumentException si la tarifa es 0 o negativo
     */
    public void setTarifaBase(double tarifaBase) {
        if(tarifaBase <= 0){
            throw new IllegalArgumentException("La tarifa base no puede ser 0 ni negativa");
        }
        this.tarifaBase = tarifaBase;
    }

    @Override
    public double calcularCosto() {
        return this.tarifaBase;
    }
}
