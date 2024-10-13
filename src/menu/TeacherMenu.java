package src.menu;

import java.util.Scanner;

public class TeacherMenu {
    public int invokeTeacherMenu(Scanner scanner){
        System.out.println("----------------------------------------");
        System.out.println("Hello, welcome. Please provide an option:");
        System.out.println("1. Add teachers");
        System.out.println("2. Remove teachers");
        System.out.println("3. Update teachers");
        System.out.println("4. View teacher data");
        System.out.println("5. Exit");
        return scanner.nextInt();
    }
}
