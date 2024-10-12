package src.main;

import java.util.Scanner;

import src.menu.*;
import src.service.*;


public class App {

    public static void main(String[] args){

        GestionEstudiantes gestorEs = new GestionEstudiantes();
        GestionProfesores gestorPr = new GestionProfesores();
        Scanner scanner = new Scanner(System.in);
        MenuInicio menuInicio = new MenuInicio();

        int opcionInicio;
        do {
            opcionInicio = menuInicio.menuInicioInvoc(scanner);
            switch (opcionInicio) {
                case 1:
                    MenuEstu menuEstu = new MenuEstu();
                    int opcionEs;
                    do {
                        opcionEs = menuEstu.menuEstuInvoc(scanner);
                        switch (opcionEs) {
                            case 1:
                                gestorEs.añadirEstudiantes(scanner);
                                break;
                            case 2: 
                                gestorEs.eliminarEstudiantes(scanner);
                                break;
                            case 3: 
                                gestorEs.actualizarEstudiante(scanner);
                                break;
                            case 4: 
                                gestorEs.mostrarDetallesEstudiante(scanner);
                                break;
                            case 5:
                                gestorEs.ponerCalificacion(scanner);
                                break;
                            default:
                                System.out.println("---------------------------------");
                                break;
                        }
                    } while (opcionEs != 6);
                    break;
                case 2:
                    MenuProf menuProf = new MenuProf();
                    int opcionProf;
                    do {
                        opcionProf = menuProf.menuProfInvoc(scanner);
                        switch (opcionProf) {
                            case 1:
                                gestorPr.añadirProfesores(scanner);
                                break;
                            case 2: 
                                gestorPr.eliminarProfesores(scanner);
                                break;
                            case 3: 
                                gestorPr.actualizarProfesor(scanner);
                                break;
                            case 4: 
                                gestorPr.mostrarDetallesProfesor(scanner);
                                break;
                            default:
                                System.out.println("---------------------------------");
                                break;
                        }
                    } while (opcionProf != 5);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("---------------------------------------");
                    break;
            }
        } while (opcionInicio != 3);

        scanner.close();
    }
}
