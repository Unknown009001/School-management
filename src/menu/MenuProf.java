package src.menu;

import java.util.Scanner;

public class MenuProf {
    public int menuProfInvoc(Scanner scanner){
        System.out.println("----------------------------------------");
        System.out.println("Hola bienvenido, proporciona una opcion");
        System.out.println("1. Añadir profesores");
        System.out.println("2. Eliminar profesores");
        System.out.println("3. Actualizar profesores");
        System.out.println("4. Mirar datos del profesores");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}
