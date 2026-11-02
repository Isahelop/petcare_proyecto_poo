package modelo.clinica;

public class Consultorio {
    private int numero;

    /**
     * Metodo constructor del consultorio
     *
     * @param numero del consultorio
     */
    public Consultorio(int numero) {
        setNumero(numero);
    }

    /**
     * Metodo para agregar un numero al consultorio
     *
     * @param numero del consultorio
     * @throws IllegalArgumentException si el numero es negativo
     */
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El numero debe ser mayor a 0");
        }
        this.numero = numero;
    }

    /**
     * Metodo para ver el numero del consultorio
     *
     * @return numero del consultorio
     */
    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Consultorio{" +
                "numero=" + numero +
                '}';
    }
}
