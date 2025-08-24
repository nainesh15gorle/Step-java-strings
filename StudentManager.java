import java.util.Scanner;

public class StudentManager {
    private String[] students;
    private int count;  // to keep track of how many students are added

    public StudentManager(int size) {
        students = new String[size];
        count = 0;1
    }

    public void addStudent(String name) {
        if (count < students.length) {
            students[count] = name;
            count++;
            System.out.println(name + " has been added.");
        } else {
            System.out.println("Student list is full!");
        }
    }

    // Display all student names
    public void displayStudents() {
        if (count == 0) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("Student List:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    public boolean searchStudent(String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager(5); // max 5 students

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add student");
            System.out.println("2. Display students");
            System.out.println("3. Search student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    manager.addStudent(name);
                    break;
                case 2:
                    manager.displayStudents();
                    break;
                case 3:
                    System.out.print("Enter student name to search: ");
                    String searchName = scanner.nextLine();
                    boolean found = manager.searchStudent(searchName);
                    if (found) {
                        System.out.println(searchName + " is in the list.");
                    } else {
                        System.out.println(searchName + " is not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
} // <--