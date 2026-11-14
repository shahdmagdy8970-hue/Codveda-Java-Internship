
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        BankAccount account = new BankAccount(1000);

        int choice;

        do {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter amount: ");
                        double depositAmount =
                                Double.parseDouble(input.nextLine());
                        account.deposit(depositAmount);
                        break;

                    case 2:
                        System.out.print("Enter amount: ");
                        double withdrawAmount =
                                Double.parseDouble(input.nextLine());
                        account.withdraw(withdrawAmount);
                        break;

                    case 3:
                        account.checkBalance();
                        break;

                    case 4:
                        System.out.println("Thank you for using our bank!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Please enter a valid number!");
            }

        } while (choice != 4);

        input.close();
    }
}
