# Heisenbergs Oftalmología

Nuestro sistema de consultas médicas es óptimo para manejar diferentes citas de pacientes, para brindarles el mejor servicio con nuestros mejores doctores.
Aquí en la Oftalmología de los Heisenbergs tenemos tecnología de primera línea con un sistema rápido y fácil para registrarse, sacar citas y ver nuestros médicos disponibles.

La applicacion fue desarrollada en Java para administrar operaciones de una clínica oftalmológica. Permite registrar pacientes y empleados, crear citas, administrar la fila de espera, atender pacientes de emergencia y llevar historial médico.

El sistema cuenta con tres tipos de usuarios:

- Secretaria
- Enfermero/a
- Doctor

### Estructuras de datos utilizadas

| Requisito | Estructura |
|---|---|
| Empleados y pacientes por ID | `HashMap` |
| Fila de espera (primero en llegar, primero en ser atendido) | `ArrayDeque` |
| Pacientes de emergencia (mayor prioridad primero) | `PriorityQueue` |
| Citas por ID | `HashMap` |

## Instalación

**Requisitos:** Java JDK 17 o superior. No se necesitan dependencias externas.

1. Clona el repositorio:
   ```bash
   git clone https://github.com/NormarieUPR/Data-Structures-PA1-FAN.git
   ```
2. Compila el programa desde la carpeta raíz:
   ```bash
   javac Main.java
   ```
3. Ejecuta:
   ```bash
   java Main
   ```

## Uso

1. Ejecuta el programa.
2. Selecciona tu rol escribiendo el número correspondiente:

   ```
   Who are you
   ---------------------------------------------
   (1) Secretary
   (2) Nurse
   (3) Doctor
   (4) Exit
   Choose an option:
   ```
3. Elige una opción del menú de tu rol y sigue las instrucciones en la consola.

### Funciones por rol

| Rol | Funciones |
|---|---|
| **Secretaria** | Registrar pacientes y empleados, mostrar y eliminar doctores, buscar empleados por ID, concertar y cancelar citas, hacer check-in, registrar emergencias, ver el historial y las citas de hoy |
| **Enfermero/a** | Ver pacientes en espera, ver médicos disponibles, buscar médicos por especialidad |
| **Doctor** | Marcar su disponibilidad, atender al siguiente paciente, registrar la visita, ver el historial |

## Configuración

No hay variables de ambiente ni archivos de configuración. Los datos se mantienen en memoria mientras el programa está en ejecución, así que se pierden al cerrarlo.

### Reglas del sistema

- Los empleados utilizan identificadores entre 1 y 1000.
- Los pacientes utilizan identificadores desde el 1001.
- Las citas deben programarse a una hora exacta o a la media hora.
- Las citas solo pueden cancelarse si están pendientes.



## Autores
- **Normarie Martinez Flores - normarie.martinez@upr.edu** 
- **Alec Rodriguez Velazquez - alec.rodriguez1@upr.edu**
- **Favian Marrero Diaz - favian.marrero@upr.edu**

Universidad de Puerto Rico Recinto de Mayaguez (UPRM)

### Tasks por persona

Normarie: Registros y personal (4 tasks + clases base)
- [x] Registrar a un nuevo paciente
- [x] Registrar a un nuevo empleado
- [x] Buscar un médico por especialidad
- [x] Mostrar médicos disponibles

Favian: Citas e historial (5 tasks)
- [x] Concertar una cita
- [x] Cancelar una cita
- [x] Registrar la visita médica
- [x] Mostrar la historia clínica del paciente
- [x] Mostrar las citas de hoy

Alec: Fila de espera y atención (5 tasks)
- [x] Registrar a un paciente (la llegada, cuando la secretaria lo registra)
- [x] Añadir paciente a la fila de espera
- [x] Atender a pacientes de urgencia (bono)
- [x] Asignar al paciente a un médico
- [x] Mostrar a todos los pacientes en espera
