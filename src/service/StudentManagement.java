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

public class StudentManagement {
    private Set<Student> studentList = new TreeSet<>();

    public String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] words = name.split(" ");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                formattedName.append(word.substring(0, 1).toUpperCase())
                             .append(word.substring(1).toLowerCase())
                             .append(" ");
            }
        }

        return formattedName.toString().trim();
    }

    public void addStudents(Scanner scanner) {
        System.out.println("----------------------------------------");
        String name = "";
        String lastName = "";
        String subject = "";
        int age = 0;
        int ID = 0;
        int DNI = 0;
        double grades = 0;
        String birthDateStr = "";
        String exitWord = "";

        while (!exitWord.equals("Exit")) {
            System.out.print("Name: ");
            name = scanner.nextLine();

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Error: the name should only contain letters.");
                continue;
            }

            System.out.print("Last Name: ");
            lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z ]+")) {
                System.out.println("Error: the last name should only contain letters.");
                continue;
            }

            name = formatName(name);
            lastName = formatName(lastName);

            try {
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
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                    }
                }

                System.out.print("Age: ");
                age = scanner.nextInt();

                LocalDate currentDate = LocalDate.now();
                int calculatedAge = Period.between(birthDate, currentDate).getYears();

                if (calculatedAge == age) {
                    System.out.println("The age matches the birth date.");
                } else {
                    System.out.println("The age does NOT match the birth date.");
                    continue;
                }

                System.out.print("ID (Only 3 digits): ");
                ID = scanner.nextInt();

                if (ID > 1000) {
                    System.out.println("ONLY 3 DIGITS.");
                    continue;
                }

                System.out.println("DNI (Only 5 digits): ");
                DNI = scanner.nextInt();

                if (DNI > 10000) {
                    System.out.println("ONLY 5 DIGITS, didn't you learn from the ID?");
                }
                scanner.nextLine();

                System.out.print("Course:\n1.Programming\n2.Cooking\n3.Garbage Collector\n");
                int courseNumber = scanner.nextInt();
                if (courseNumber == 1) {
                    subject = "Programming";
                } else if (courseNumber == 2) {
                    subject = "Cooking";
                } else if (courseNumber == 3) {
                    subject = "Garbage Collector";
                } else {
                    System.out.println("This is not a valid subject.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: the input must be a number, please try again.");
                scanner.nextLine();
            }
            System.out.println("Type 'Exit' if you wish to exit or anything else to continue.");
            exitWord = scanner.nextLine();
        }

        Student student = new Student(name, lastName, age, subject, ID, birthDateStr, DNI, grades);
        studentList.add(student);
        System.out.println("Student added.");
    }

    public void removeStudents(Scanner scanner) {
        System.out.println("----------------------------------------");
        String exitWord = "";
        while (!exitWord.equals("Exit")) {
            if (studentList.isEmpty()) {
                System.out.println("There are no students to remove.");
                return;
            }

            for (Student student : studentList) {
                System.out.println(student);
            }

            try {
                System.out.print("Provide the ID of the student you want to remove: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                boolean removed = studentList.removeIf(e -> e.getID() == ID);

                if (removed) {
                    System.out.println("Student successfully removed.");
                } else {
                    System.out.println("No student found with that ID.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Unexpected input.");
            }

            System.out.println("Type 'Exit' if you wish to exit or anything else to continue.");
            exitWord = scanner.nextLine();
        }
    }

    public void updateStudent(Scanner scanner) {
        System.out.println("----------------------------------------");
        for (Student student : studentList) {
            System.out.println(student);
        }

        try {
            System.out.print("Provide the ID of the student you want to update: ");
            int ID = scanner.nextInt();
            scanner.nextLine();

            Student foundStudent = null;

            for (Student student : studentList) {
                if (student.getID() == ID) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent == null) {
                System.out.println("No student found with that ID.");
                return;
            }

            System.out.print("New name (leave blank to not change): ");
            String newName = scanner.nextLine();

            if (!newName.isEmpty()) {
                foundStudent.setFirstName(newName);
            }

            System.out.print("New last name (leave blank to not change): ");
            String newLastName = scanner.nextLine();
            if (!newLastName.isEmpty()) {
                foundStudent.setLastName(newLastName);
            }

            System.out.print("New age (0 to not change): ");
            int newAge = scanner.nextInt();
            if (newAge != 0) {
                foundStudent.setAge(newAge);
            }

            System.out.print("New subject (leave blank to not change): ");
            String newSubject = scanner.nextLine();

            if (!newSubject.isEmpty()) {
                foundStudent.setSubject(newSubject);
            }

            System.out.println("Student details updated.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Unexpected input.");
        }
    }

    public void showStudentDetails(Scanner scanner) {
        System.out.println("----------------------------------------");
        for (Student student : studentList) {
            System.out.println(student);
        }
        try {
            System.out.print("Provide the ID of the student you want to see the details of: ");
            int ID = scanner.nextInt();
            scanner.nextLine();

            Student foundStudent = null;

            for (Student student : studentList) {
                if (student.getID() == ID) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent != null) {
                System.out.println("Student Details:");
                System.out.println("Name: " + foundStudent.getFirstName());
                System.out.println("Last Name: " + foundStudent.getLastName());
                System.out.println("Age: " + foundStudent.getAge());
                System.out.println("Subject: " + foundStudent.getSubject());
                System.out.println("ID: " + foundStudent.getID());
                System.out.println("Birth Date: " + foundStudent.getBirthDate());
                System.out.println("DNI: " + foundStudent.getDNI());
                System.out.println("Grades: " + foundStudent.getGrades());
            } else {
                System.out.println("No student found with that ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Unexpected input.");
        }
    }

    public void assignGrade(Scanner scanner) {
        for (Student student : studentList) {
            System.out.println(student);
        }
        try {
            System.out.println("Provide the ID of the student you want to grade.");
            int ID = scanner.nextInt();
            scanner.nextLine();
            Student foundStudent = null;

            for (Student student : studentList) {
                if (student.getID() == ID) {
                    foundStudent = student;
                }
            }

            System.out.println("Provide the grade or enter 0 to leave it unchanged.");
            double grade = scanner.nextInt();
            scanner.nextLine();
            if (grade != 0) {
                foundStudent.setGrades(grade);
                System.out.println("Grade successfully assigned.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: the input must be a number.");
        }
    }

    public void showTeachers(TeacherManagement teacherManagement) {
        System.out.println("-------------------------------------");
        Set<Teacher> teacherList = teacherManagement.getTeacherList();
        if (teacherList.isEmpty()) {
            System.out.println("No students to show.");
        } else {
            for (Teacher teacher : teacherList) {
                System.out.println("Name: " + teacher.getFirstName());
                System.out.println("Last Name: " + teacher.getLastName());
                System.out.println("Age: " + teacher.getAge());
                System.out.println("Subject: " + teacher.getSubject());
            }
        }
    }

    public Set<Student> getStudentList() {
        return studentList;
    }
}
