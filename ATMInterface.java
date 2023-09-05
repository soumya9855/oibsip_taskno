import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulated account details
        String userId = "user123";
        String pin = "1234";
        double balance = 1000.0;
        ArrayList<String> transactionHistory = new ArrayList<>();

        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (enteredUserId.equals(userId) && enteredPin.equals(pin)) {
            System.out.println("Welcome, " + userId + "!");
            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        displayTransactionHistory(transactionHistory);
                        break;
                    case 2:
                        withdrawFunds(balance, transactionHistory, scanner);
                        break;
                    case 3:
                        depositFunds(balance, transactionHistory, scanner);
                        break;
                    case 4:
                        transferFunds(balance, transactionHistory, scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
    }

    public static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    public static void displayTransactionHistory(ArrayList<String> history) {
        System.out.println("\nTransaction History:");
        for (String transaction : history) {
            System.out.println(transaction);
        }
    }

    public static void withdrawFunds(double balance, ArrayList<String> history, Scanner scanner) {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (amount <= balance) {
            balance -= amount;
            history.add("Withdrawn $" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public static void depositFunds(double balance, ArrayList<String> history, Scanner scanner) {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        balance += amount;
        history.add("Deposited $" + amount);
        System.out.println("Deposit successful.");
    }

    public static void transferFunds(double balance, ArrayList<String> history, Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter transfer amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (amount <= balance) {
            balance -= amount;
            history.add("Transferred $" + amount + " to " + recipientId);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}
