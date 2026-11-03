import modelo.clinica.*;
import modelo.mascotas.*;
import modelo.personas.*;
import modelo.servicios.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Clinica clinica = new Clinica("PetCare Coatzacoalcos");

    public static void main(String[] args) {
        // Inicializamos algunos consultorios por defecto para poder agendar citas
        clinica.agregarConsultorio(1);
        clinica.agregarConsultorio(2);
        clinica.agregarConsultorio(3);

        boolean salir = false;

        System.out.println("=== Bienvenido al sistema de " + clinica.getNombre() + " ===");

        while (!salir) {
            mostrarMenu();
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) throw new IllegalArgumentException("La opción no puede estar vacía.");

                int opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1 -> registrarDuenoYMascota();
                    case 2 -> registrarVeterinario();
                    case 3 -> agendarCita();
                    case 4 -> atenderOCancelarCita();
                    case 5 -> generarComprobante();
                    case 6 -> consultarHistorial();
                    case 7 -> listarMascotasYSonido();
                    case 8 -> verTotalCobradoVeterinario();
                    case 9 -> darDeBajaMascota();
                    case 10 -> {
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Opción fuera del menú. Ingrese un número del 1 al 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Se esperaba un número entero. Intente de nuevo.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                --- Menú Principal ---
                1. Registrar dueño y mascota (perro, gato o ave).
                2. Registrar veterinario y asignarlo a la clínica.
                3. Agendar una cita.
                4. Atender o cancelar una cita.
                5. Generar el comprobante de una cita atendida.
                6. Consultar el historial clínico de una mascota.
                7. Listar todas las mascotas y su sonido (demostración de polimorfismo).
                8. Ver el total cobrado por veterinario.
                9. Dar de baja una mascota.
                10. Salir.
                Seleccione una opción:""");
    }

    // --- OPCIÓN 1 ---
    private static void registrarDuenoYMascota() {
        System.out.print("¿Es un dueño nuevo (1) o existente (2)?: ");
        int tipoDueno = Integer.parseInt(scanner.nextLine().trim());
        Propietario propietario;

        if (tipoDueno == 1) {
            System.out.print("Nombre: "); String nombre = scanner.nextLine().trim();
            System.out.print("Apellido: "); String apellido = scanner.nextLine().trim();
            System.out.print("Teléfono: "); String telefono = scanner.nextLine().trim();
            System.out.print("Correo: "); String correo = scanner.nextLine().trim();
            System.out.print("Dirección: "); String direccion = scanner.nextLine().trim();

            propietario = new Propietario(nombre, apellido, telefono, correo, direccion);
            clinica.agregarPropietario(propietario);
            System.out.println("Dueño registrado con éxito.");
        } else if (tipoDueno == 2) {
            System.out.print("Ingrese el teléfono del dueño existente: ");
            propietario = clinica.buscarPropietario(scanner.nextLine().trim());
        } else {
            throw new IllegalArgumentException("Opción de dueño inválida.");
        }

        System.out.println("Tipo de mascota - (1) Perro, (2) Gato, (3) Ave Exótica:");
        int tipoMasc = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nombre de la mascota: "); String nombreM = scanner.nextLine().trim();
        System.out.print("Edad: "); int edad = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Peso: "); float peso = Float.parseFloat(scanner.nextLine().trim());

        Mascota mascota = null;
        switch (tipoMasc) {
            case 1 -> {
                System.out.print("Raza: "); String raza = scanner.nextLine().trim();
                System.out.print("Nivel de energía: "); int energia = Integer.parseInt(scanner.nextLine().trim());
                mascota = new Perro(nombreM, edad, peso,propietario, raza, energia);
            }
            case 2 -> {
                System.out.print("¿Es doméstico? (true/false): ");
                boolean esDomestico = Boolean.parseBoolean(scanner.nextLine().trim());
                mascota = new Gato(nombreM, edad, peso, propietario, esDomestico);
            }
            case 3 -> {
                System.out.print("Longitud de alas (cm): ");
                float alas = Float.parseFloat(scanner.nextLine().trim());
                mascota = new AveExotica(nombreM, edad, peso, propietario, alas);
            }
            default -> throw new IllegalArgumentException("Tipo de mascota inválido.");
        }

        propietario.agregarMascota(mascota);
        System.out.println("Mascota registrada y asignada al dueño.");
    }

    // --- OPCIÓN 2 ---
    private static void registrarVeterinario() {
        System.out.print("Nombre: "); String nombre = scanner.nextLine().trim();
        System.out.print("Apellido: "); String apellido = scanner.nextLine().trim();
        System.out.print("Teléfono: "); String telefono = scanner.nextLine().trim();
        System.out.print("Correo: "); String correo = scanner.nextLine().trim();
        System.out.print("Cédula Profesional: "); String cedula = scanner.nextLine().trim();
        System.out.print("Especialidad: "); String especialidad = scanner.nextLine().trim();

        Veterinario v = new Veterinario(nombre, apellido, telefono, correo, cedula, especialidad);
        clinica.agregarVeterinario(v);
        System.out.println("Veterinario registrado exitosamente.");
    }

    // --- OPCIÓN 3 ---
    private static void agendarCita() {
        System.out.print("Nombre de la mascota: ");
        Mascota mascota = clinica.buscarMascota(scanner.nextLine().trim());

        System.out.print("Cédula del veterinario: ");
        Veterinario vet = clinica.buscarVeterinario(scanner.nextLine().trim());

        System.out.print("Número de consultorio (ej. 1, 2, 3): ");
        Consultorio cons = clinica.buscarConsultorio(Integer.parseInt(scanner.nextLine().trim()));

        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("Hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(scanner.nextLine().trim());

        Cita cita = new Cita(fecha, hora, mascota, vet, cons);
        clinica.agendarCita(cita);
        System.out.println("Cita agendada correctamente.");
    }

    // --- OPCIÓN 4 ---
    private static void atenderOCancelarCita() {
        List<Cita> citas = clinica.obtenerCitas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        System.out.println("Citas programadas:");
        for (int i = 0; i < citas.size(); i++) {
            Cita c = citas.get(i);
            if (c.getEstado() == Estado.PROGRAMADA) {
                System.out.printf("[%d] Mascota: %s | Fecha: %s | Hora: %s\n",
                        i, c.getMascota().getNombre(), c.getFecha(), c.getHora());
            }
        }

        System.out.print("Seleccione el índice de la cita a modificar: ");
        int indice = Integer.parseInt(scanner.nextLine().trim());
        Cita citaSeleccionada = citas.get(indice);

        System.out.print("¿Qué desea hacer? (1) Atender, (2) Cancelar: ");
        int accion = Integer.parseInt(scanner.nextLine().trim());

        if (accion == 1) {
            System.out.print("Servicio a aplicar - (1) Consulta General, (2) Vacunación, (3) Cirugía: ");
            int serv = Integer.parseInt(scanner.nextLine().trim());
            Facturable servicio = null;

            switch (serv) {
                case 1 -> {
                    System.out.print("Tarifa base: ");
                    servicio = new ConsultaGeneral(Double.parseDouble(scanner.nextLine().trim()));
                }
                case 2 -> {
                    System.out.print("Costo vacuna: "); double cv = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Costo aplicación: "); double ca = Double.parseDouble(scanner.nextLine().trim());
                    servicio = new Vacunacion(cv, ca);
                }
                case 3 -> {
                    System.out.print("Complejidad: "); int comp = scanner.nextInt();
                    System.out.print("Horas quirófano: "); int hrs = Integer.parseInt(scanner.nextLine().trim());
                    servicio = new Cirugia(comp, hrs);
                }
                default -> throw new IllegalArgumentException("Servicio inválido.");
            }
            clinica.atenderCita(citaSeleccionada, servicio);
            System.out.println("Cita atendida con éxito.");
        } else if (accion == 2) {
            citaSeleccionada.cancelarCita();
        } else {
            System.out.println("Acción inválida.");
        }
    }

    // --- OPCIÓN 5 ---
    private static void generarComprobante() {
        List<Cita> citas = clinica.obtenerCitas();
        System.out.println("Citas atendidas disponibles para cobro:");
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).getEstado() == Estado.ATENDIDA) {
                System.out.printf("[%d] Mascota: %s | Servicio: %s\n",
                        i, citas.get(i).getMascota().getNombre(), citas.get(i).getServicio().getClass().getSimpleName());
            }
        }

        System.out.print("Seleccione el índice de la cita para el comprobante: ");
        int indice = Integer.parseInt(scanner.nextLine().trim());

        Comprobante comp = new Comprobante(citas.get(indice));
        clinica.agregarComprobante(comp);
        System.out.println("Comprobante generado. Total a pagar: $" + comp.getTotal());
    }

    // --- OPCIÓN 6 ---
    private static void consultarHistorial() {
        System.out.print("Nombre de la mascota: ");
        Mascota mascota = clinica.buscarMascota(scanner.nextLine().trim());

        List<Cita> historial = mascota.getHistorialClinico().getCitasAtendidas();
        if (historial.isEmpty()) {
            System.out.println("La mascota no tiene citas en su historial.");
        } else {
            System.out.println("--- Historial de " + mascota.getNombre() + " ---");
            for (Cita c : historial) {
                System.out.println("- Fecha: " + c.getFecha() + " | Atendió: Dr. " + c.getVeterinario().getApellido() +
                        " | Servicio: " + c.getServicio().getClass().getSimpleName());
            }
        }
    }

    // --- OPCIÓN 7 ---
    private static void listarMascotasYSonido() {
        List<Mascota> mascotas = clinica.obtenerMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }
        System.out.println("--- Lista de Mascotas (Polimorfismo) ---");
        for (Mascota m : mascotas) {
            System.out.print(m.getNombre() + " (" + m.getClass().getSimpleName() + ") hace: ");
            m.emitirSonido();
        }
    }

    // --- OPCIÓN 8 ---
    private static void verTotalCobradoVeterinario() {
        System.out.print("Cédula del veterinario: ");
        Veterinario vet = clinica.buscarVeterinario(scanner.nextLine().trim());
        double total = clinica.calcularTotalCobrado(vet);
        System.out.println("Total generado por el Dr(a). " + vet.getApellido() + ": $" + total);
    }

    // --- OPCIÓN 9 ---
    private static void darDeBajaMascota() {
        System.out.print("Nombre de la mascota a dar de baja: ");
        Mascota mascota = clinica.buscarMascota(scanner.nextLine().trim());
        clinica.darBajaMascota(mascota);
        System.out.println("La mascota " + mascota.getNombre() + " ha sido dada de baja del sistema.");
    }
}