// BUGGY CODE - CAN YOU FIND THE ERRORS?
public class StudentManager {
    // This class should manage student records
    // But it has SEVERAL bugs!
    
    private String studentName;
    private int studentAge;
    private double studentGrade;
    
    // Constructor - but there's a bug here!
    public StudentManager(name, age, grade) {
        studentName = name;
        studentAge = age;
        studentGrade = grade;
    }
    
    // Method to display student info - but it's broken!
    public void displayInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student Grade: " + studentGrade);
    }
    
    // Method to check if student passed - bug here!
    public boolean isPassing() {
        if (studentGrade >= 75) {
            return true;
        } else {
            return false;
        }
    }
    
    // Method to update grade - missing validation!
    public void updateGrade(double newGrade) {
        studentGrade = newGrade;
    }
    
    // Main method to test - but it won't compile!
    public static void main(String[] args) {
        StudentManager student = new StudentManager("Maria Santos", 20, 85.5);
        student.displayInfo();
        
        System.out.println("Is passing? " + student.isPassing());
        
        student.updateGrade(92.0);
        student.displayInfo();
    }
}
