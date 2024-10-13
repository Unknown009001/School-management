package src.menu;

import java.util.Scanner;

public class MainMenu {
    public int invokeMainMenu(Scanner scanner){
        System.out.println("Hello, welcome! Choose an option: \n1.Student\n2.Teacher\n3.Exit");
        return scanner.nextInt();
    }
}
