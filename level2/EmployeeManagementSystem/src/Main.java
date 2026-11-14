
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // Add Employee
    public static void addEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(input.nextLine());

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("ID already exists!");
                return;
            }
        }

        System.out.print("Enter Employee Name: ");
        String name = input.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = Double.parseDouble(input.nextLine());

        Employee emp = new Employee(id, name, salary);
        employees.add(emp);

        System.out.println("Employee added successfully!");
    }

    // View Employees
    public static void viewEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for (Employee emp : employees) {
            emp.displayInfo();
        }
    }

    // Update Employee
    public static void updateEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(input.nextLine());

        for (Employee emp : employees) {

            if (emp.getId() == id) {

                System.out.print("Enter new name: ");
                String name = input.nextLine();

                System.out.print("Enter new salary: ");
                double salary = Double.parseDouble(input.nextLine());

                emp.setName(name);
                emp.setSalary(salary);

                System.out.println("Employee updated successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    // Delete Employee
    public static void deleteEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(input.nextLine());

        for (Employee emp : employees) {

            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Employee deleted successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        viewEmployees();
                        break;

                    case 3:
                        updateEmployee();
                        break;

                    case 4:
                        deleteEmployee();
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Please enter a valid number!");
            }

        } while (choice != 5);

        input.close();
    }
}
