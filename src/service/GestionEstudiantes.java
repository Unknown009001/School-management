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

 
public class GestionEstudiantes {
    private Set<Estudiante> listaEstudiantes = new TreeSet<>();


    
    public String formatearNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre;
        }
    
        String[] palabras = nombre.split(" ");
        StringBuilder nombreFormateado = new StringBuilder();
    
        for (String palabra : palabras) {
            if (palabra.length() > 0) {
                nombreFormateado.append(palabra.substring(0, 1).toUpperCase())
                                 .append(palabra.substring(1).toLowerCase())
                                 .append(" "); 
            }
        }
    
    
        return nombreFormateado.toString().trim();
    }
    


    public void añadirEstudiantes(Scanner scanner) {
        System.out.println("----------------------------------------");
        String nombre = "";
        String apellido = "";
        String asignatura = "";
        int edad = 0;
        int ID = 0;
        int DNI = 0;
        double calificaciones = 0;
        String fechaNacimientoStr = "";
        String palabraSalida = "";

        while (!palabraSalida.equals("Salida")) {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine();
            
            if (!nombre.matches("[a-zA-Z ]+")) {
                System.out.println("Error: el nombre solo debe contener letras.");
                continue;
            }

            System.out.print("Apellido: ");
            apellido = scanner.nextLine();
            if (!apellido.matches("[a-zA-Z ]+")) {
                System.out.println("Error: el apellido solo debe contener letras.");
                continue;
            }

            nombre = formatearNombre(nombre);
            apellido = formatearNombre(apellido);

            try {
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


                System.out.print("Curso:\n1.Programacion\n2. Cocina\n3. Basurero\n");
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
              
                
                
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: la entrada debe de ser un número, vuelva a intentarlo.");
                scanner.nextLine(); 
            }
            System.out.println("Esciba 'Salir' Si desea salir O cualquier otra cosa si desea continuar");
            palabraSalida = scanner.nextLine();

        }

       
        Estudiante estudiante = new Estudiante(nombre, apellido, edad, asignatura, ID, fechaNacimientoStr, DNI, calificaciones);
        listaEstudiantes.add(estudiante);
        System.out.println("Estudiante añadido.");
    }

    public void eliminarEstudiantes(Scanner scanner) {
        System.out.println("----------------------------------------");
        String palabraSalida = "";
        while (!palabraSalida.equals("Salida")) {
            if (listaEstudiantes.isEmpty()) {
                System.out.println("No hay estudiantes para eliminar.");
                return;
            }
    
            for (Estudiante estudiante : listaEstudiantes) {
                System.out.println(estudiante);
            }
            
            try{
                System.out.print("Proporcione la ID del estudiante que desea eliminar: ");
                int ID = scanner.nextInt();
                scanner.nextLine(); 
                boolean eliminado = listaEstudiantes.removeIf(e -> e.getID() == ID);
                
                if (eliminado) {
                    System.out.println("Estudiante eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un estudiante con esa ID.");
                }

                
                
            }catch(InputMismatchException e){
                System.out.println("Error: No era la respuesta esperada");
                
            }

            System.out.println("Esciba 'Salir' Si desea salir O cualquier otra cosa si desea continuar");
                palabraSalida = scanner.nextLine();
           
           
        }
        
    }



    public void actualizarEstudiante(Scanner scanner) {
        System.out.println("----------------------------------------");
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println(estudiante);
        }

       try{
        System.out.print("Proporcione la ID del estudiante que desea actualizar: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
    
        Estudiante estudianteEncontrado = null;
        
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getID() == ID) {
                estudianteEncontrado = estudiante;
                break;
            }
        }
    
        if (estudianteEncontrado == null) {
            System.out.println("No se encontró un estudiante con esa ID.");
            return;
        }
    
     
        System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
        String nuevoNombre = scanner.nextLine();

        if (!nuevoNombre.isEmpty()) {
            estudianteEncontrado.setNombre(nuevoNombre);
        }
    
        System.out.print("Nuevo apellido (dejar en blanco para no cambiar): ");
        String nuevoApellido = scanner.nextLine();
        if (!nuevoApellido.isEmpty()) {
            estudianteEncontrado.setApellido(nuevoApellido);
        }
    
        System.out.print("Nueva edad (0 para no cambiar): ");
        int nuevaEdad = scanner.nextInt();
        if (nuevaEdad != 0) {
            estudianteEncontrado.setEdad(nuevaEdad);
        }
    
        System.out.print("Nueva asignatura (dejar en blanco para no cambiar): ");
        String nuevaAsignatura = scanner.nextLine();

        if (!nuevaAsignatura.isEmpty()) {
            estudianteEncontrado.setAsignatura(nuevaAsignatura);
        }


    
        System.out.println("Datos del estudiante actualizados.");
       }catch(InputMismatchException e){
            System.out.println("Error: No era la respuesta esperada");
       }
    }
    
    public void mostrarDetallesEstudiante(Scanner scanner) {
        System.out.println("----------------------------------------");
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println(estudiante);
        }
        try{
            System.out.print("Proporcione la ID del estudiante del que desea ver los detalles: ");
            int ID = scanner.nextInt();
            scanner.nextLine(); 
        
            Estudiante estudianteEncontrado = null;
        
            for (Estudiante estudiante : listaEstudiantes) {
                if (estudiante.getID() == ID) {
                    estudianteEncontrado = estudiante;
                    break;
                }
            }
        
            if (estudianteEncontrado != null) {
                System.out.println("Detalles del Estudiante:");
                System.out.println("Nombre: " + estudianteEncontrado.getNombre());
                System.out.println("Apellido: " + estudianteEncontrado.getApellido());
                System.out.println("Edad: " + estudianteEncontrado.getEdad());
                System.out.println("Asignatura: " + estudianteEncontrado.getAsignatura() );
                System.out.println("ID: " + estudianteEncontrado.getID());
                System.out.println("Fecha de Nacimiento: " + estudianteEncontrado.getFechaNacimiento());
                System.out.println("DNI: " + estudianteEncontrado.getDNI());
                System.out.println("Calificaciones: " + estudianteEncontrado.getCalificaciones());
            } else {
                System.out.println("No se encontró un estudiante con esa ID.");
            }
        }catch(InputMismatchException e){
            System.out.println("Error: No era la respuesta esperada");
        }
       
    }

    public void ponerCalificacion(Scanner scanner){
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println(estudiante);
        }
        try{
        System.out.println("Proporicona la ID del estudiante al que quieras calificar");
        int ID = scanner.nextInt();
        scanner.nextLine();
        Estudiante estudianteBuscado = null;

        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getID() == ID) {
                estudianteBuscado = estudiante;
            }
        
        
        }

        System.out.println("Proporcione la calificacion o ponga 0 para dejarla normal");
        double calificacion = scanner.nextInt();
        scanner.nextLine();
        if (calificacion != 0) {
            estudianteBuscado.setCalificaciones(calificacion);
            System.out.println("Calificacion puesta con exito");
        }

        } catch(InputMismatchException e){
            System.out.println("Error: la entrada debe de ser un numero");
        }

    }


    public void mostrarProfesores(GestionProfesores gestionProfesores){
        System.out.println("-------------------------------------");
        Set<Profesor> listaProfesores = gestionProfesores.getListaProfesores();
        if (listaProfesores.isEmpty()) {
            System.out.println("No hay estudiantes para mostrar.");
        } else {
            for (Profesor profesor : listaProfesores) {
                System.out.println("Nombre: " + profesor.getNombre());
                System.out.println("Apellido: " + profesor.getApellido());
                System.out.println("Edad: " + profesor.getEdad());
                System.out.println("Asignatura: " + profesor.getAsignatura());
                System.out.println("ID: " + profesor.getID());
                System.out.println("DNI: " + profesor.getDNI());
                System.out.println("-------------------------------------");
            }
        }
    }
    
    public Set<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }



    public void setListaEstudiantes(Set<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    
    
}
