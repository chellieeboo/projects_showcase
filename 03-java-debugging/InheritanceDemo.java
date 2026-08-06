/**
 * INHERITANCE DEMO - Shows parent-child class relationships
 * 
 * Parent Class: Person
 * Child Classes: Student, Teacher, Staff
 */
public class InheritanceDemo {
    
    public static void main(String[] args) {
        System.out.println("👨‍👩‍👧‍👦 INHERITANCE DEMONSTRATION");
        System.out.println("=".repeat(40));
        
        // Create different types of people
        Student student = new Student("Maria Santos", 20, "BSIT", 2);
        Teacher teacher = new Teacher("Mr. Reyes", 45, "Computer Science", 50000);
        Staff staff = new Staff("Ms. Cruz", 35, "Registrar", 30000);
        
        // Display their information
        System.out.println("\n📚 STUDENT:");
        student.displayInfo();
        System.out.println("Status: " + student.getStatus(85.5));
        
        System.out.println("\n👨‍🏫 TEACHER:");
        teacher.displayInfo();
        
        System.out.println("\n📋 STAFF:");
        staff.displayInfo();
        
        // Show polymorphism
        System.out.println("\n🔄 POLYMORPHISM DEMO:");
        System.out.println("All people can introduce themselves differently:");
        
        Person[] people = {student, teacher, staff};
        for (Person p : people) {
            p.introduce();
        }
    }
}

/**
 * PARENT CLASS: Person
 * Contains common attributes and methods for all people
 */
class Person {
    // Common attributes
    protected String name;
    protected int age;
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Common methods
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
    
    // Method to be overridden by child classes (POLYMORPHISM)
    public void introduce() {
        System.out.println("Hi, I'm " + name + ". I'm a person.");
    }
}

/**
 * CHILD CLASS: Student
 * Inherits from Person and adds student-specific attributes
 */
class Student extends Person {
    // Student-specific attributes
    private String course;
    private int yearLevel;
    
    // Constructor
    public Student(String name, int age, String course, int yearLevel) {
        super(name, age); // Call parent constructor
        this.course = course;
        this.yearLevel = yearLevel;
    }
    
    // Student-specific method
    public String getStatus(double grade) {
        if (grade >= 75) {
            return "✅ PASSING";
        } else {
            return "❌ FAILING";
        }
    }
    
    // Override displayInfo (adds student details)
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel);
    }
    
    // Override introduce (POLYMORPHISM)
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ". I'm a " + yearLevel + "th year " + course + " student.");
    }
}

/**
 * CHILD CLASS: Teacher
 * Inherits from Person and adds teacher-specific attributes
 */
class Teacher extends Person {
    // Teacher-specific attributes
    private String department;
    private double salary;
    
    // Constructor
    public Teacher(String name, int age, String department, double salary) {
        super(name, age);
        this.department = department;
        this.salary = salary;
    }
    
    // Teacher-specific method
    public void teach() {
        System.out.println(name + " is teaching " + department + ".");
    }
    
    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Department: " + department);
        System.out.println("Salary: ₱" + String.format("%,.2f", salary));
    }
    
    // Override introduce (POLYMORPHISM)
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ". I'm a " + department + " teacher.");
    }
}

/**
 * CHILD CLASS: Staff
 * Inherits from Person and adds staff-specific attributes
 */
class Staff extends Person {
    // Staff-specific attributes
    private String office;
    private double salary;
    
    // Constructor
    public Staff(String name, int age, String office, double salary) {
        super(name, age);
        this.office = office;
        this.salary = salary;
    }
    
    // Staff-specific method
    public void work() {
        System.out.println(name + " is working at the " + office + " office.");
    }
    
    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Office: " + office);
        System.out.println("Salary: ₱" + String.format("%,.2f", salary));
    }
    
    // Override introduce (POLYMORPHISM)
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ". I work at the " + office + " office.");
    }
}
