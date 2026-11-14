import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        int choice;

        do {
            System.out.println("\n===== Binary Search Tree =====");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number: ");
                    bst.insert(input.nextInt());
                    System.out.println("Number inserted successfully!");
                    break;

                case 2:
                    System.out.print("Enter number to search: ");
                    int searchValue = input.nextInt();

                    if (bst.search(searchValue)) {
                        System.out.println("Number found!");
                    } else {
                        System.out.println("Number not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter number to delete: ");
                    int deleteValue = input.nextInt();

                    if (bst.search(deleteValue)) {
                        bst.delete(deleteValue);
                        System.out.println("Number deleted successfully!");
                    } else {
                        System.out.println("Number not found!");
                    }
                    break;

                case 4:
                    System.out.print("Inorder: ");
                    bst.inorder();
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Preorder: ");
                    bst.preorder();
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Postorder: ");
                    bst.postorder();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        input.close();
    }
}