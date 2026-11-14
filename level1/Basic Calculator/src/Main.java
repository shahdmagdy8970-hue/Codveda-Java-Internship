
import java.util.Scanner;

public class Main {

    // Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Division
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== BASIC CALCULATOR =====");

        System.out.print("Enter first number: ");
        double num1 = input.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = input.nextDouble();

        System.out.println("\nChoose an operation:");
        System.out.println("+ Addition");
        System.out.println("- Subtraction");
        System.out.println("* Multiplication");
        System.out.println("/ Division");

        System.out.print("Enter operation: ");
        char operator = input.next().charAt(0);

        double result = 0;
        boolean valid = true;

        switch (operator) {
            case '+':
                result = add(num1, num2);
                break;

            case '-':
                result = subtract(num1, num2);
                break;

            case '*':
                result = multiply(num1, num2);
                break;

            case '/':
                try {
                    result = divide(num1, num2);
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                    valid = false;
                }
                break;

            default:
                System.out.println("Invalid operation!");
                valid = false;
        }

        if (valid) {
            System.out.println("Result = " + result);
        }

        input.close();
    }
}
