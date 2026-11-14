
package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Add User");
            System.out.println("6. View Users");
            System.out.println("7. Update User");
            System.out.println("8. Delete User");
            System.out.println("9. Borrow Book");
            System.out.println("10. Return Book");
            System.out.println("11. View Transactions");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                int choice = Integer.parseInt(input.nextLine());

                try {
                    switch (choice) {

                        case 1:
                            System.out.print("Enter book title: ");
                            String title = input.nextLine();

                            System.out.print("Enter author: ");
                            String author = input.nextLine();

                            BookDAO.addBook(title, author);
                            break;

                        case 2:
                            BookDAO.viewBooks();
                            break;

                        case 3:
                            System.out.print("Enter book ID: ");
                            int bookId = Integer.parseInt(input.nextLine());

                            System.out.print("Enter new title: ");
                            String newTitle = input.nextLine();

                            System.out.print("Enter new author: ");
                            String newAuthor = input.nextLine();

                            BookDAO.updateBook(bookId, newTitle, newAuthor);
                            break;

                        case 4:
                            System.out.print("Enter book ID: ");
                            int deleteBookId =
                                    Integer.parseInt(input.nextLine());

                            BookDAO.deleteBook(deleteBookId);
                            break;

                        case 5:
                            System.out.print("Enter user name: ");
                            String name = input.nextLine();

                            System.out.print("Enter user email: ");
                            String email = input.nextLine();

                            UserDAO.addUser(name, email);
                            break;

                        case 6:
                            UserDAO.viewUsers();
                            break;

                        case 7:
                            System.out.print("Enter user ID: ");
                            int userId = Integer.parseInt(input.nextLine());

                            System.out.print("Enter new name: ");
                            String newName = input.nextLine();

                            System.out.print("Enter new email: ");
                            String newEmail = input.nextLine();

                            UserDAO.updateUser(
                                    userId, newName, newEmail
                            );
                            break;

                        case 8:
                            System.out.print("Enter user ID: ");
                            int deleteUserId =
                                    Integer.parseInt(input.nextLine());

                            UserDAO.deleteUser(deleteUserId);
                            break;

                        case 9:
                            System.out.print("Enter book ID: ");
                            int borrowBookId =
                                    Integer.parseInt(input.nextLine());

                            System.out.print("Enter user ID: ");
                            int borrowUserId =
                                    Integer.parseInt(input.nextLine());

                            TransactionDAO.borrowBook(
                                    borrowBookId, borrowUserId
                            );
                            break;

                        case 10:
                            System.out.print("Enter book ID: ");
                            int returnBookId =
                                    Integer.parseInt(input.nextLine());

                            System.out.print("Enter user ID: ");
                            int returnUserId =
                                    Integer.parseInt(input.nextLine());

                            TransactionDAO.returnBook(
                                    returnBookId, returnUserId
                            );
                            break;

                        case 11:
                            TransactionDAO.viewTransactions();
                            break;

                        case 0:
                            System.out.println("Goodbye!");
                            input.close();
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }

                } catch (SQLException e) {
                    System.out.println("Database Error: " + e.getMessage());
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
