package src.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.Scanner;
import java.util.TreeSet;
import src.model.*;
public class GestionProfesores {
    
    private Set<Profesor> listaProfesores = new TreeSet<>();
    
    public String formatearNombre(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            return nombre;
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

    

    public void añadirProfesores(Scanner scanner){
        System.out.println("----------------------------------------");
        String nombre = "";
        String apellido = "";
        String asignatura = "";
        int edad = 0;
        int ID = 0;
        int DNI = 0;
        String fechaNacimientoStr = "";
        String palabraSalida = "";

        while (!palabraSalida.equals("Salir")) {
            
            System.out.println("Nombre: ");
            nombre = scanner.nextLine();

            if (!nombre.matches("[a-zA-Z ]+")) {
                System.out.println("Error: el nombre debe llevar solo letras");
                continue;
            }

            System.out.println("Apellido: ");
            apellido = scanner.nextLine();

            if (!apellido.matches("[a-zA-Z ]+")) {
                System.out.println("Error: el apellido debe llevar solo letras");
                continue;
            }

            nombre = formatearNombre(nombre);
            apellido = formatearNombre(apellido);

            try{
                LocalDate fechaNacimiento = null;
                boolean fechaValida = false;

                while (!fechaValida) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    System.out.print("Ingresa tu fecha de nacimiento (yyyy-mm-dd): ");
                    fechaNacimientoStr = scanner.nextLine();
    
                    try {
                        fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
                        fechaValida = true; 
                    } catch (DateTimeParseException e) {
                        
                        System.out.println("Formato de fecha inválido. Por favor, usa el formato yyyy-mm-dd.");
                    }
                }
                System.out.print("Edad: ");
                edad = scanner.nextInt();
                
                LocalDate fechaActual = LocalDate.now();
                int edadActual = Period.between(fechaNacimiento, fechaActual).getYears();
    
              
                if (edadActual == edad) {
                    System.out.println("La edad coincide con la fecha de nacimiento.");
                } else {
                    System.out.println("La edad NO coincide con la fecha de nacimiento.");
                    continue;
                }
                
                System.out.print("ID (Solo 3 digitos): ");
                ID = scanner.nextInt();

                if (ID > 1000) {
                    System.out.println("SOLO 3 PUTOS DIGITOS");
                    continue;
                }
                
                System.out.println("DNI (Solo 5 digitos): ");
                DNI = scanner.nextInt();

                if (DNI > 10000) {
                    System.out.println("SOLO 5 PERROS DIGITOS, no aprendiste del ID?");
                }
                scanner.nextLine(); 


                System.out.print("Curso:\n1.Programacion\n2. Cocina\n3. Basurero ");
                int NumeroCurso = scanner.nextInt();
                if (NumeroCurso == 1) {
                    asignatura = "Programacion";
                } else if (NumeroCurso == 2) {
                    asignatura = "Cocina";
                } else if (NumeroCurso == 3) {
                    asignatura = "Basurero";
                } else{
                    System.out.println("Esto no es un asignatura");
                    continue;
                }
                scanner.nextInt();
                
                break;
            }catch (InputMismatchException e) {
                System.out.println("Error: la entrada debe de ser un número, vuelva a intentarlo.");
                scanner.nextLine(); 
            }
            System.out.println("Esciba 'Salir' Si desea salir O cualquier otra cosa si desea continuar");
            palabraSalida = scanner.nextLine();
         
        }

        
        Profesor profesor = new Profesor(nombre, apellido, edad, asignatura, ID, fechaNacimientoStr, DNI);
        listaProfesores.add(profesor);
        System.out.println("Profesor añadido");
        
    }

    public void eliminarProfesores(Scanner scanner){
        System.out.println("----------------------------------------");
        String palabraSalida = "";
        while (!palabraSalida.equals("Salida")) {
            if (listaProfesores.isEmpty()) {
                System.out.println("No hay profesores para eliminar");
                break;
            }

            for (Profesor profesor : listaProfesores) {
                System.out.println(profesor);
            }

            try{
                System.out.println("Proporcione la ID del profesor que desea borrar");
                int ID = scanner.nextInt();
                scanner.nextLine();

                 boolean eliminado = listaProfesores.removeIf(e -> e.getID() == ID);
                 if (eliminado) {
                    System.out.println("Profesor eliminado con exito");
                } else{
                    System.out.println("No se encontro profesor con esa ID");
                }

            } catch ( InputMismatchException e){
                System.out.println("Error: La respuesta esperada era un numero");
            }
          
            System.out.println("Esciba 'Salir' Si desea salir O cualquier otra cosa si desea continuar");
            palabraSalida = scanner.nextLine();
        }
        
    }


    public void actualizarProfesor(Scanner scanner){
        System.out.println("-------------------------------------");
        for (Profesor profesor : listaProfesores) {
            System.out.println(profesor);
        }

        try{
            System.out.println("Proporcione la ID del profesor a actualizar");
            int ID = scanner.nextInt();
            Profesor profesorEncontrado = null;
    
            for (Profesor profesor : listaProfesores) {
                if (profesor.getID() == ID) {
                    profesorEncontrado = profesor;
                }
            }
    
            System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
            String nombreNuevo = scanner.nextLine();
            if (!nombreNuevo.isEmpty()) {
                profesorEncontrado.setNombre(nombreNuevo);
            }
    
            System.out.print("Nuevo apellido (dejar en blanco para no cambiar): ");
            String nuevoApellido = scanner.nextLine();
            if (!nuevoApellido.isEmpty()) {
                profesorEncontrado.setApellido(nuevoApellido);
            }
        
            System.out.print("Nueva edad (0 para no cambiar): ");
            int nuevaEdad = scanner.nextInt();
            if (nuevaEdad != 0) {
                profesorEncontrado.setEdad(nuevaEdad);
            }
        
            System.out.print("Nueva asignatura (dejar en blanco para no cambiar): ");
            String nuevaAsignatura = scanner.nextLine();
    
            if (!nuevaAsignatura.isEmpty()) {
                profesorEncontrado.setAsignatura(nuevaAsignatura);
            }
        
            System.out.println("Datos del estudiante actualizados.");
        }catch(InputMismatchException e){
            System.out.println("Error: No era la respuesta esperada");
        }
        
    }

    public void mostrarDetallesProfesor(Scanner scanner){
        System.out.println("-------------------------------------");
        for (Profesor profesor : listaProfesores) {
            System.out.println(profesor);
        }
        
        try{
            System.out.println("Proporcione la ID del profesor");
            int ID = scanner.nextInt();

            Profesor profesorEncontrado = null;

            for (Profesor profesor : listaProfesores) {
                if (profesor.getID() == ID ) {
                    profesorEncontrado = profesor;
                    break;
                }
            }

            if (profesorEncontrado != null) {
                System.out.println("Detalles del Estudiante:");
                System.out.println("Nombre: " + profesorEncontrado.getNombre());
                System.out.println("Apellido: " + profesorEncontrado.getApellido());
                System.out.println("Edad: " + profesorEncontrado.getEdad());
                System.out.println("Asignatura: " + profesorEncontrado.getAsignatura());
                System.out.println("ID: " + profesorEncontrado.getID());
                System.out.println("Fecha de Nacimiento: " + profesorEncontrado.getFechaNacimiento());
                System.out.println("DNI: " + profesorEncontrado.getDNI());
            } else {
                System.out.println("No se encontró un estudiante con esa ID.");
            }
        }catch(InputMismatchException e){
            System.out.println("Error: No era la respuesta esperada");
        }


    }

    public void mostrarEstudiantes(GestionEstudiantes gestionEstudiantes){
        System.out.println("-------------------------------------");
        Set<Estudiante> listaEstudiantes = gestionEstudiantes.getListaEstudiantes();
        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para mostrar.");
        } else {
            for (Estudiante estudiante : listaEstudiantes) {
                System.out.println("Nombre: " + estudiante.getNombre());
                System.out.println("Apellido: " + estudiante.getApellido());
                System.out.println("Edad: " + estudiante.getEdad());
                System.out.println("Asignatura: " + estudiante.getAsignatura());
                System.out.println("ID: " + estudiante.getID());
                System.out.println("DNI: " + estudiante.getDNI());
                System.out.println("-------------------------------------");
            }
        }
    }



    public Set<Profesor> getListaProfesores() {
        return listaProfesores;
    }



    public void setListaProfesores(Set<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    
}
