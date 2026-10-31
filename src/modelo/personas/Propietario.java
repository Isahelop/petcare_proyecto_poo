package modelo.personas;

import modelo.mascotas.Mascota;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Persona {
    private String direccion;
    private List<Mascota> mascotas = new ArrayList<>();

    /**
     *
     * @param nombre    del propietario
     * @param apellido  apellido del propietario
     * @param telefono  telefono del propietario
     * @param correo    correo del propietario
     * @param direccion dirección del propietario
     */
    public Propietario(String nombre, String apellido, String telefono, String correo, String direccion) {
        super(nombre, apellido, telefono, correo);
    }

    /**
     * Metodo para agregar una direccion a un propietario
     *
     * @param direccion del propietario
     * @throws IllegalArgumentException si la direccion esta vacia
     */
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La direccion no puede estar vacia");
        }
        this.direccion = direccion;
    }

    /**
     * Metodo para agregar una mascota al propietario
     *
     * @param mascota del propietario
     * @throws IllegalArgumentException si la mascota y si el propietario es diferente
     */
    public void agregarMascota(Mascota mascota) {
        if(mascota == null){
            throw new IllegalArgumentException("La mascota no puede estar vacia");
        }
        if(mascota.getPropietario() != this){
            throw new IllegalArgumentException("La mascota pertenece a otro propietario");
        }
        mascotas.add(mascota);
    }

    /**
     * Metodo para ver las mascotas que tiene el propietario
     *
     * @return la lista de las mascotas del propietario
     */
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    @Override
    public void describirFuncion() {
        System.out.println("Su funcion ser el dueño de la mascota a atender");
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "direccion='" + direccion + '\'' +
                ", mascotas=" + mascotas +
                "} " + super.toString();
    }
}
