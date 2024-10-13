package src.model;

public class Teacher implements Comparable<Teacher> {

    private String firstName;
    private String lastName;
    private int age;
    private String subject;
    private int ID;
    private String birthDate;
    private int DNI;

    public Teacher(String firstName, String lastName, int age, String subject, int ID, String birthDate, int DNI) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.subject = subject;
        this.ID = ID;
        this.birthDate = birthDate;
        this.DNI = DNI;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    @Override
    public int compareTo(Teacher other) {
        return Integer.compare(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + ID + ") - Subject: " + subject;
    }
}
