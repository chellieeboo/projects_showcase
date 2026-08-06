import java.util.ArrayList;
import java.util.Scanner;

/**
 * Student Management System - A simple OOP program
 * Demonstrates: Encapsulation, ArrayList, User Input
 */
public class StudentManagementSystem {
    
    // List to store all students
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🎓 STUDENT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(30));
        
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudentGrade();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("👋 Exiting... Thank you!");
                    System.out.println("Developed by: [Your Name]");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
    
    // Display menu
    private static void showMenu() {
        System.out.println("\n📋 MENU");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student Grade");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    
    // Add a new student
    private static void addStudent() {
        System.out.println("\n➕ ADD STUDENT");
        
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Course (BSIT/BSCS/BSIS): ");
        String course = scanner.nextLine();
        
        System.out.print("Enter Year Level (1-4): ");
        int year = scanner.nextInt();
        
        System.out.print("Enter Grade (0-100): ");
        double grade = scanner.nextDouble();
        scanner.nextLine();
        
        // Validate grade
        if (grade < 0 || grade > 100) {
            System.out.println("❌ Invalid grade! Must be between 0 and 100.");
            return;
        }
        
        Student student = new Student(id, name, course, year, grade);
        students.add(student);
        System.out.println("✅ Student added successfully!");
    }
    
    // View all students
    private static void viewAllStudents() {
        System.out.println("\n📚 ALL STUDENTS");
        System.out.println("-".repeat(50));
        
        if (students.isEmpty()) {
            System.out.println("❌ No students found.");
            return;
        }
        
        System.out.printf("%-5s %-20s %-10s %-5s %-8s %-10s\n", 
                         "ID", "Name", "Course", "Year", "Grade", "Status");
        System.out.println("-".repeat(50));
        
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-10s %-5d %-8.2f %-10s\n",
                             s.getId(), s.getName(), s.getCourse(),
                             s.getYear(), s.getGrade(), s.getStatus());
        }
    }
    
    // Search for a student by ID
    private static void searchStudent() {
        System.out.println("\n🔍 SEARCH STUDENT");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("\n✅ Student Found!");
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Course: " + s.getCourse());
                System.out.println("Year: " + s.getYear());
                System.out.println("Grade: " + s.getGrade());
                System.out.println("Status: " + s.getStatus());
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }
    
    // Update student grade
    private static void updateStudentGrade() {
        System.out.println("\n📝 UPDATE GRADE");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter New Grade (0-100): ");
                double newGrade = scanner.nextDouble();
                scanner.nextLine();
                
                if (newGrade < 0 || newGrade > 100) {
                    System.out.println("❌ Invalid grade! Must be between 0 and 100.");
                    return;
                }
                
                s.setGrade(newGrade);
                System.out.println("✅ Grade updated successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }
    
    // Delete a student
    private static void deleteStudent() {
        System.out.println("\n🗑️ DELETE STUDENT");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("✅ Student deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }
}

/**
 * Student Class - Represents a student
 * Demonstrates ENCAPSULATION (private fields, public methods)
 */
class Student {
    // Private fields (Encapsulation)
    private int id;
    private String name;
    private String course;
    private int year;
    private double grade;
    
    // Constructor
    public Student(int id, String name, String course, int year, double grade) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.year = year;
        this.grade = grade;
    }
    
    // Getters and Setters (Accessors and Mutators)
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCourse() {
        return course;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    // Additional method: Check if student is passing
    public String getStatus() {
        if (grade >= 75) {
            return "PASSING ✅";
        } else {
            return "FAILING ❌";
        }
    }
    
    // Override toString() for better printing
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', grade=" + grade + "}";
    }
}
