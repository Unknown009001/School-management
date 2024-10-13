package src.menu;

import java.util.Scanner;

public class StudentMenu {

    public int invokeStudentMenu(Scanner scanner){
        System.out.println("----------------------------------------");
        System.out.println("Hello, welcome. Please provide an option:");
        System.out.println("1. Add student");
        System.out.println("2. Remove student");
        System.out.println("3. Update student");
        System.out.println("4. View student data");
        System.out.println("5. Grade student");
        System.out.println("6. Exit");
        return scanner.nextInt();
    }
}
