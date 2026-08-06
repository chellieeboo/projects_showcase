// FIXED CODE - ALL BUGS CORRECTED!
public class StudentManager {
    
    private String studentName;
    private int studentAge;
    private double studentGrade;
    
    // FIXED: Added data types to constructor parameters
    public StudentManager(String name, int age, double grade) {
        studentName = name;
        studentAge = age;
        studentGrade = grade;
    }
    
    // FIXED: Added proper return type and fixed display
    public void displayInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student Grade: " + studentGrade);
    }
    
    // FIXED: Added proper boolean logic and validation
    public boolean isPassing() {
        // Check if grade is valid (0-100) and passing (>=75)
        if (studentGrade < 0 || studentGrade > 100) {
            System.out.println("Warning: Invalid grade detected!");
            return false;
        }
        return studentGrade >= 75;
    }
    
    // FIXED: Added validation for new grade
    public void updateGrade(double newGrade) {
        if (newGrade < 0 || newGrade > 100) {
            System.out.println("Error: Grade must be between 0 and 100");
            return;
        }
        studentGrade = newGrade;
        System.out.println("Grade updated successfully!");
    }
    
    // FIXED: Added proper method to test the class
    public static void main(String[] args) {
        StudentManager student = new StudentManager("Maria Santos", 20, 85.5);
        student.displayInfo();
        
        System.out.println("Is passing? " + student.isPassing());
        
        student.updateGrade(92.0);
        student.displayInfo();
        
        // Test invalid grade
        student.updateGrade(150.0); // Should show error
    }
}
