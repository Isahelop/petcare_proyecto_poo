# Análisis del sistema PetCare

## 1. Tabla de clases candidatas

| Clase candidata  | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
|------------------|---|---|---|---|
| Persona          | abstracta | nombre, apellido, telefono, correo | | |
| Propietario      | concreta | direccion | | |
| Veterinario      | concreta | cedulaProfesional, especialidad | | |
| Recepcionista    | concreta | turno | | |
| Mascota          | abstracta | nombre, edad, peso, propietario, historialClinico | | |
| Perro            | concreta | raza, nivelEnergia | | |
| Gato             | concreta | esDomestico | | |
| AveExotica       | concreta | longitudAlas | | |
| HistorialClinico | concreta | citasAtendidas (List<Cita>) | | |
| ConsultaGeneral  | concreta | tarifaBase | | |
| Vacunacion       | concreta | costoVacuna | | |
| Cirugia          | concreta | complejidad, horasQuirofano | | |
| Facturable       | interfaz | - | | |
| Clinica          | concreta | nombre, veterinarios (List<Veterinario>), consultorios (List<Consultorio>), citas (List<Cita>) | | |
| Consultorio      | concreta | numero | | |
| Cita             | concreta | fecha, hora, mascota, veterinario, consultorio, estado, servicio (Facturable) | | |
| Estado           | enum | PROGRAMADA, ATENDIDA, CANCELADA | | |
| Comprobante      | concreta | cita, total, totalComprobantes (static) | | |

## 2. Objetos de ejemplo

(pendiente)

## 3. Clases descartadas

(pendiente)

## 4. Justificación de relaciones

(pendiente)