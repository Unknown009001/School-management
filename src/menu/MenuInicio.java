package src.menu;

import java.util.Scanner;

public class MenuInicio {
    public int menuInicioInvoc(Scanner scanner){
        System.out.println("Hola bienvenido escoge una opcion: \n1.Estudiante\n2.Profesor\n3.Salir");
        return scanner.nextInt();
    }

}
