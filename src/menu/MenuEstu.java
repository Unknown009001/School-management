package src.menu;

import java.util.Scanner;

public class MenuEstu {
    
    public int menuEstuInvoc(Scanner scanner){
        System.out.println("----------------------------------------");
        System.out.println("Hola bienvenido, proporciona una opcion");
        System.out.println("1. Añadir estudiantes");
        System.out.println("2. Eliminar estudiantes");
        System.out.println("3. Actualizar estudiante");
        System.out.println("4. Mirar datos del estudiante");
        System.out.println("5. Calificar estudiante");
        System.out.println("6. Salir");
        return scanner.nextInt();
    }
}
