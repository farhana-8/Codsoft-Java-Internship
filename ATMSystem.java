import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount! Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance! Available balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount! Please enter a positive value.");
        }
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + userAccount.getBalance());
    }

    private void depositMoney() {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        userAccount.deposit(amount);
    }

    private void withdrawMoney() {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        userAccount.withdraw(amount);
    }
}

// Main class to run the ATM program
public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.00); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.displayMenu();
    }
}
