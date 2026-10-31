package modelo.servicios;

public class Cirugia implements Facturable {
    private static final double costoBase = 500;
    private int complejidad;
    private double horasQuirofano;

    /**
     * Metodo constructor para cirugia
     * @param complejidad de la cirugia
     * @param horasQuirofano de la cirugia
     */
    public Cirugia(int complejidad, double horasQuirofano){
        setComplejidad(complejidad);
        setHorasQuirofano(horasQuirofano);
    }

    /**
     * Metodo para agregar complejidad a una cirugia
     * @param complejidad de la cirugia
     * @throws IllegalArgumentException si la complejidad es menor o igual a 0 y mayor a 10
     */
    public void setComplejidad(int complejidad) {
        if(complejidad <= 0 && complejidad > 10){
            throw new IllegalArgumentException("La complejidad no puede ser 0 o negativo y mayor de 10");
        }
        this.complejidad = complejidad;
    }

    /**
     * Metodo para agregar las horas de quirofano de una cirugia
     * @param horasQuirofano de la cirugia
     * @throws IllegalArgumentException si las horas son menor o igual a 0
     */
    public void setHorasQuirofano(double horasQuirofano) {
        if(horasQuirofano <= 0 ){
            throw new IllegalArgumentException("Las horas no pueden ser 0 o negativo");
        }
        this.horasQuirofano = horasQuirofano;
    }

    /**
     * Metodo para ver el costo base de la cirugia
     * @return costo base asignado de la cirugia
     */
    public static double getCostoBase() {
        return costoBase;
    }

    /**
     * Metodo para ver la complejidad de la cirugia
     * @return complejidad de la cirugia
     */
    public int getComplejidad() {
        return complejidad;
    }

    /**
     * Metodo para ver las horas de quirofano de la cirugia
     * @return horas de quirofano de la cirugia
     */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    @Override
    public double calcularCosto() {
        return costoBase * complejidad * horasQuirofano;
    }
}
