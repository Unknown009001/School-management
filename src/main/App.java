package src.main;

import java.util.Scanner;

import src.menu.*;
import src.service.*;

public class App {

    public static void main(String[] args){

        StudentManagement studentManager = new StudentManagement();
        TeacherManagement teacherManager = new TeacherManagement();
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();

        int startOption;
        do {
            startOption = mainMenu.invokeMainMenu(scanner);
            switch (startOption) {
                case 1:
                    StudentMenu studentMenu = new StudentMenu();
                    int studentOption;
                    do {
                        studentOption = studentMenu.invokeStudentMenu(scanner);
                        switch (studentOption) {
                            case 1:
                                studentManager.addStudents(scanner);
                                break;
                            case 2: 
                                studentManager.removeStudents(scanner);
                                break;
                            case 3: 
                                studentManager.updateStudent(scanner);
                                break;
                            case 4: 
                                studentManager.showStudentDetails(scanner);
                                break;
                            case 5:
                                studentManager.assignGrade(scanner);
                                break;
                            default:
                                System.out.println("---------------------------------");
                                break;
                        }
                    } while (studentOption != 6);
                    break;
                case 2:
                    TeacherMenu teacherMenu = new TeacherMenu();
                    int teacherOption;
                    do {
                        teacherOption = teacherMenu.invokeTeacherMenu(scanner);
                        switch (teacherOption) {
                            case 1:
                                teacherManager.addTeachers(scanner);
                                break;
                            case 2: 
                                teacherManager.removeTeachers(scanner);
                                break;
                            case 3: 
                                teacherManager.updateTeacher(scanner);
                                break;
                            case 4: 
                                teacherManager.showTeacherDetails(scanner);
                                break;
                            default:
                                System.out.println("---------------------------------");
                                break;
                        }
                    } while (teacherOption != 5);
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("---------------------------------------");
                    break;
            }
        } while (startOption != 3);

        scanner.close();
    }
}
