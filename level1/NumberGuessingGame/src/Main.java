
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Generate a random number between 1 and 100
        int secretNumber = random.nextInt(100) + 1;

        int attempts = 0;
        int maxAttempts = 7;
        boolean guessedCorrectly = false;

        System.out.println("===== NUMBER GUESSING GAME =====");
        System.out.println("I chose a number between 1 and 100.");
        System.out.println("You have 7 attempts to guess it!");

        while (attempts < maxAttempts) {

            System.out.print("\nEnter your guess: ");

            // Handle invalid input
            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
                continue;
            }

            int guess = input.nextInt();
            attempts++;

            if (guess < 1 || guess > 100) {
                System.out.println("Please enter a number between 1 and 100.");
            }
            else if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed correctly!");
                guessedCorrectly = true;
                break;
            }
            else if (guess > secretNumber) {
                System.out.println("Too High! Try a smaller number.");
            }
            else {
                System.out.println("Too Low! Try a bigger number.");
            }

            System.out.println("Attempts remaining: "
                    + (maxAttempts - attempts));
        }

        if (!guessedCorrectly) {
            System.out.println("\nGame Over!");
            System.out.println("The correct number was: " + secretNumber);
        }

        input.close();
    }
}
