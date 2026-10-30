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
    %% Herencias de personas
    Persona <|-- Propietario
    Persona <|-- Veterinario
    Persona <|--Recepcionista
    
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
    %%Herencia de animales
    Mascota <|-- Perro
    Mascota <|-- Gato
    Mascota <|-- AveExotica
    
    %%relacion entre propietario y mascota
    Mascota "0..*" --o "1" Propietario : Agregacion
    
```
