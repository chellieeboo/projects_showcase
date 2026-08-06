# Java OOP Bug Fix - StudentManager

## Project Overview
This project demonstrates my ability to identify, fix, and explain bugs in Java OOP code.

## Files
- `Before_BuggyCode.java` – Original code with bugs
- `After_FixedCode.java` – Clean, working code
- `Bug_Explanation.txt` – Detailed explanation of each fix

## Bugs I Found and Fixed
1. **Constructor** – Missing data types for parameters
2. **Method return type** – Missing `void` in displayInfo()
3. **Grade validation** – Allowed invalid grades (negative or >100)
4. **Logic error** – isPassing() didn't check for invalid grades
5. **Testing** – Added test for edge cases

## What I Learned
- Java compilation errors are usually easy to fix
- Data validation is CRITICAL for reliable programs
- Testing edge cases (like invalid inputs) catches hidden bugs
- Documenting bugs helps others (and future me) understand

## OOP Concepts Used
- **Encapsulation** – Private fields with public methods
- **Constructors** – Proper initialization
- **Methods** – Behavior and logic

## How to Run
1. Compile the fixed code:
   ```bash
   javac After_FixedCode.java
