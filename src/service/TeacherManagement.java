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

public class TeacherManagement {
    
    private Set<Teacher> teacherList = new TreeSet<>();
    
    public String formatName(String name){
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void addTeachers(Scanner scanner){
        System.out.println("----------------------------------------");
        String name = "";
        String lastName = "";
        String subject = "";
        int age = 0;
        int ID = 0;
        int DNI = 0;
        String birthDateStr = "";
        String exitWord = "";

        while (!exitWord.equals("Exit")) {
            
            System.out.println("Name: ");
            name = scanner.nextLine();

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Error: the name must only contain letters.");
                continue;
            }

            System.out.println("Last Name: ");
            lastName = scanner.nextLine();

            if (!lastName.matches("[a-zA-Z ]+")) {
                System.out.println("Error: the last name must only contain letters.");
                continue;
            }

            name = formatName(name);
            lastName = formatName(lastName);

            try{
                LocalDate birthDate = null;
                boolean validDate = false;

                while (!validDate) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    System.out.print("Enter your birth date (yyyy-mm-dd): ");
                    birthDateStr = scanner.nextLine();
    
                    try {
                        birthDate = LocalDate.parse(birthDateStr, formatter);
                        validDate = true; 
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use the yyyy-mm-dd format.");
                    }
                }
                System.out.print("Age: ");
                age = scanner.nextInt();
                
                LocalDate currentDate = LocalDate.now();
                int currentAge = Period.between(birthDate, currentDate).getYears();
    
                if (currentAge == age) {
                    System.out.println("The age matches the birth date.");
                } else {
                    System.out.println("The age DOES NOT match the birth date.");
                    continue;
                }
                
                System.out.print("ID (Only 3 digits): ");
                ID = scanner.nextInt();

                if (ID > 1000) {
                    System.out.println("ONLY 3 DAMN DIGITS");
                    continue;
                }
                
                System.out.println("DNI (Only 5 digits): ");
                DNI = scanner.nextInt();

                if (DNI > 10000) {
                    System.out.println("ONLY 5 DAMN DIGITS, didn't you learn from the ID?");
                }
                scanner.nextLine(); 

                System.out.print("Subject:\n1.Programming\n2. Cooking\n3. Trashman ");
                int courseNumber = scanner.nextInt();
                if (courseNumber == 1) {
                    subject = "Programming";
                } else if (courseNumber == 2) {
                    subject = "Cooking";
                } else if (courseNumber == 3) {
                    subject = "Trashman";
                } else{
                    System.out.println("This is not a subject.");
                    continue;
                }
                scanner.nextInt();
                
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: the input must be a number, please try again.");
                scanner.nextLine(); 
            }
            System.out.println("Write 'Exit' if you want to exit or anything else if you want to continue.");
            exitWord = scanner.nextLine();
        }

        Teacher teacher = new Teacher(name, lastName, age, subject, ID, birthDateStr, DNI);
        teacherList.add(teacher);
        System.out.println("Teacher added.");
    }

    public void removeTeachers(Scanner scanner){
        System.out.println("----------------------------------------");
        String exitWord = "";
        while (!exitWord.equals("Exit")) {
            if (teacherList.isEmpty()) {
                System.out.println("There are no teachers to remove.");
                break;
            }

            for (Teacher teacher : teacherList) {
                System.out.println(teacher);
            }

            try{
                System.out.println("Provide the ID of the teacher you want to delete.");
                int ID = scanner.nextInt();
                scanner.nextLine();

                boolean removed = teacherList.removeIf(e -> e.getID() == ID);
                if (removed) {
                    System.out.println("Teacher successfully removed.");
                } else {
                    System.out.println("No teacher found with that ID.");
                }

            } catch (InputMismatchException e){
                System.out.println("Error: The expected answer was a number.");
            }
          
            System.out.println("Write 'Exit' if you want to exit or anything else if you want to continue.");
            exitWord = scanner.nextLine();
        }
    }

    public void updateTeacher(Scanner scanner){
        System.out.println("-------------------------------------");
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }

        try {
            System.out.println("Provide the ID of the teacher to update.");
            int ID = scanner.nextInt();
            Teacher foundTeacher = null;
    
            for (Teacher teacher : teacherList) {
                if (teacher.getID() == ID) {
                    foundTeacher = teacher;
                }
            }
    
            System.out.print("New name (leave blank to not change): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                foundTeacher.setFirstName(newName);
            }
    
            System.out.print("New last name (leave blank to not change): ");
            String newLastName = scanner.nextLine();
            if (!newLastName.isEmpty()) {
                foundTeacher.setLastName(newLastName);
            }
        
            System.out.print("New age (0 to not change): ");
            int newAge = scanner.nextInt();
            if (newAge != 0) {
                foundTeacher.setAge(newAge);
            }
        
            System.out.print("New subject (leave blank to not change): ");
            String newSubject = scanner.nextLine();
    
            if (!newSubject.isEmpty()) {
                foundTeacher.setSubject(newSubject);
            }
        
            System.out.println("Teacher information updated.");
        } catch (InputMismatchException e) {
            System.out.println("Error: The expected response was a number.");
        }
    }

    public void showTeacherDetails(Scanner scanner){
        System.out.println("-------------------------------------");
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
        
        try {
            System.out.println("Provide the ID of the teacher.");
            int ID = scanner.nextInt();

            Teacher foundTeacher = null;

            for (Teacher teacher : teacherList) {
                if (teacher.getID() == ID ) {
                    foundTeacher = teacher;
                    break;
                }
            }

            if (foundTeacher != null) {
                System.out.println("Teacher Details:");
                System.out.println("Name: " + foundTeacher.getFirstName());
                System.out.println("Last Name: " + foundTeacher.getLastName());
                System.out.println("Age: " + foundTeacher.getAge());
                System.out.println("Subject: " + foundTeacher.getSubject());
                System.out.println("ID: " + foundTeacher.getID());
                System.out.println("Birth Date: " + foundTeacher.getBirthDate());
                System.out.println("DNI: " + foundTeacher.getDNI());
            } else {
                System.out.println("No teacher found with that ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: The expected response was a number.");
        }
    }

    public void showStudents(StudentManagement studentManagement){
        System.out.println("-------------------------------------");
        Set<Student> studentList = studentManagement.getStudentList();
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : studentList) {
                System.out.println("Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("Age: " + student.getAge());
                System.out.println("Subject: " + student.getSubject());
                System.out.println("ID: " + student.getID());
                System.out.println("DNI: " + student.getDNI());
                System.out.println("-------------------------------------");
            }
        }
    }

    public Set<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(Set<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
