# Diagrama de Clases UML

```mermaid
classDiagram
    %% Paquete personas
    namespace Personas {
        
        class Persona{
            <<abstract>>
            -String nombre
            -String apellido
            -String telefono
            -String correo
            +Persona(nombre : String, apellido : String, telefono : String, correo : String)
            +describirFuncion()*  void
        }   
        class Propietario{
            -String direccion
            -List~Mascota~ mascotas
            +Propietario(nombre : String, apellido : String, telefono : String, correo : String, direccion : String )
            +describirFuncion()void 
            +setDireccion(direccion : String)  void 
            +agregarMascota(mascota : Mascota)  void
            +getMascotas() List mascotas
            +toString()
        }
        class Veterinario{
            -String cedulaProfesional
            -String especialidad
            +Veterinario(nombre : String, apellido : String, telefono : String, correo : String, cedulaProfesional : String, especialidad : String)
            +describirFuncion() : void  
            +toString()
        }
        class Recepcionista{
            -String turno
            +Recepcionista(nombre : String, apellido : String, telefono : String, correo : String, turno : String) 
            +describirFuncion() void 
            +setTurno(turno : String)  void 
            +toString()
        }
    }
   
    namespace Animales {
        class Mascota{
            <<abstract>>
            -String nombre
            -int edad
            -float peso
            -Propietario propietario
            +Mascota(nombre : String, edad : int, peso : float, propietario : Propietario)
            +emitirSonido()* void
        }
        class Perro{
            -String raza
            -int nivelEnergia+Perro(nombre : String,edad : int, peso : float, raza : String, nivelEnergia : int)
            +emitirSonido() void
        }    
        class Gato{
            -bool esDomestico
            +Gato(nombre : String,edad : int, peso : float,esDomestico : boolean)
            +emitirSonido() void
        }
        class AveExotica{
            -float logitudAlas
            +emitirSonido() void
        }
    }
    
    namespace Servicio {
        
        class Facturable{
            <<interface>>
            +calcularCosto() double
        }

        class ConsultaGeneral{
            -double tarifaBase
            +ConsultaGeneral(tarifaBase : double)
            +setTarifaBase(tarifaBase : double)  void
        }
        
        class Vacunacion{
            -double costoVacuna
            -double costoAplicacion
            +Vacunacion(costoVacuna : double, costoAplicacion : double)
            +setCostoVacuna(costoVacuna : double) : void
            +setCostoAplicacion(costoAplicacion : double) : void
        }
        
        class Cirugia{
            -int complejidad
            -int horasQuirofano
            +Cirugia(complejidad int, horasQuirofano : int)
            +getHorasQuirofano() int
            +getcomplejidad() int
        }
    }
    
    %%Herencias
    
    Persona <|-- Propietario
    Persona <|-- Veterinario
    Persona <|--Recepcionista
    Mascota <|-- Perro
    Mascota <|-- Gato
    Mascota <|-- AveExotica
    Facturable <|-- ConsultaGeneral
    Facturable <|-- Vacunacion
    Facturable <|-- Cirugia
    
    %%relacion 
    Mascota "0..*" --o "1" Propietario : Agregacion
    
   
    
    
```
