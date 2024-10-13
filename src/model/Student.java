package src.model;

public class Student implements Comparable<Student> {

    private String firstName;
    private String lastName;
    private int age;
    private String subject;
    private int ID;
    private String birthDate;
    private int DNI;
    private double grades;
    
    public Student(String firstName, String lastName, int age, String subject, int ID, String birthDate, int DNI, double grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.subject = subject;
        this.ID = ID;
        this.birthDate = birthDate;
        this.DNI = DNI;
        this.grades = grades;
    }

    public int getDNI(){
        return DNI;
    }

    public void setDNI(int DNI ){
        this.DNI = DNI;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + ID + ") - Subject: " + subject;
    }
    
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.ID, other.ID);
    }
}
