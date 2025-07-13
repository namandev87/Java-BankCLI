package in.bankcli;

import java.util.Scanner;



public class MainPanel {

    static BankingSystem bank = new BankingSystem();
    static Scanner sc = new Scanner(System.in);

    static boolean exit = false;
    static boolean login = false;

    public static void main(String[] args) {
        int choice;
        while (!exit) {

            choice = Utility.greet("Customer Banking Portal", sc, "Create New Account", "Login to Existing Account", "Exit");

            switch (choice) {
                case 1 : createNewUserAccount();
                    break;

                case 2: loginUserAccount();
                    break;

                case 3: exit();
                    break;

                default:
                    System.out.println("Invalid Input! Try again");
            }
        }
    }


    static void createNewUserAccount(){
        sc.nextLine();
        System.out.println(Utility.greet("create new account"));
        System.out.print("Full Name: ");
        String name = sc.nextLine();
        System.out.print("Set User ID (Unique): ");
        String userId = sc.nextLine();
        System.out.print("Set Password (Case Sensitive): ");
        String password = sc.nextLine();
        bank.createNewUserAccount(name, userId, password);
        System.out.println("Account successfully created.");

    }

    static void loginUserAccount(){
        sc.nextLine();
        login = true;
        int choice;
        UserAccount user = userAuth();
        if (user == null){
            return;
        }

        while(login && !exit){
            choice = Utility.greet("Account Dashboard", sc, "Select Bank Account", "Create New Bank Account", "Logout", "Exit");

            switch (choice) {
                case 1: selectBankAccount(user);
                    break;

                case 2: createUserBankAccount(user);
                    break;

                case 3: login = false;
                    break;

                case 4: exit();
                    break;

                default:
                    System.out.println("Invalid Input");
            }

        }

    }

    static void createUserBankAccount(UserAccount user){
        int accountType;
        double deposit;
        accountType = Utility.greet("Create New account", sc, "Savings Account", "Current Account");
        System.out.print("Enter Initial Deposit: $");
        deposit = sc.nextDouble();
        System.out.println("Bank Account Successfully Created");
        System.out.println("Account Number: " +bank.createNewUserBankAccount(user, accountType, deposit));

    }

    static UserAccount userAuth(){
        System.out.println(Utility.greet("Login authentication"));
        String userId, password;
        int counter = 0;


        System.out.print("User ID: ");
        userId =  sc.nextLine();
        if(!bank.checkUserId(userId)) {
            System.out.println("User Not Found!");
            return null;
        }


        do {
            System.out.print("Password: ");
            password = sc.nextLine();
            if(bank.checkCredentials(userId, password) != null){
                System.out.println("User Authenticated");
            } else {
                System.out.println("Invalid password. Tries left: " +(3 - ++counter));
            }
        } while (bank.checkCredentials(userId, password) == null && counter < 3);
        return bank.checkCredentials(userId, password);
    }

    static void selectBankAccount(UserAccount user){
        while (login && !exit) {
            System.out.println(Utility.greet("select bank account"));
            System.out.println("Select your option:\n");
            int numOfAccounts = bank.noOfUserBankAccounts(user);
            if (numOfAccounts == 0) {
                System.out.println("No Accounts to Display");
                return;
            }
            for (int i = 0; i < numOfAccounts; i++) {
                System.out.println((i+1)+ ". " +bank.userBankAccountNumber(user, i));
            }
            System.out.print("\nEnter your choice: ");
            int index = sc.nextInt() - 1;

            bankAccountMenu(user, index);
        }
    }

    static void bankAccountMenu(UserAccount user, int index){
        int choice;
        while (login && !exit){
            System.out.println(Utility.greet("bank account"));
            printBankAccountDetails(user, index);
            choice = Utility.greet(sc, "View Balance", "Deposit Money", "Withdraw Money",
                    "Transfer Money", "Transaction History", "Delete Account", "Logout", "Previous Menu", "Exit");
            switch (choice) {
                case 1: printCurrentBankBalance(user, index);
                    break;

                case 2: depositMoney(user, index);
                    break;

                case 3: withdrawMoney(user, index);
                    break;

                case 4: transferMoney(user, index);
                    break;

                case 5: getTransactionHistory(user, index);
                    break;

                case 6: deleteAccount(user, index);
                    return;

                case 7: login = false;
                    return;

                case 8: return;

                case 9: exit();

                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    static void printBankAccountDetails(UserAccount user, int index){
        System.out.printf("""
                Customer Name  : %s
                Account Number : %s
                Account Type   : %s
                """, user.getUserName(), bank.userBankAccountNumber(user, index), bank.userBankAccountType(user, index));
    }

    static void printCurrentBankBalance(UserAccount user, int index){
        System.out.println("Available Balance: $" +bank.userBankAccountBalance(user, index));
    }

    static void depositMoney(UserAccount user, int index){
        System.out.print("Enter the amount to deposit: ");
        double amount = sc.nextDouble();
        bank.userDepositAmount(index, amount, user);
        System.out.println("Amount Deposited Successfully");
    }

    static void withdrawMoney(UserAccount user, int index){
        System.out.print("Enter the amount to withdraw: ");
        double amount = sc.nextDouble();
        bank.userWithdrawAmount(index, amount, user);
        System.out.println("Amount Withdrawn Successfully");
    }

    static void transferMoney(UserAccount user, int index){
        sc.nextLine();
        System.out.print("Enter Receiver's Bank Account No.: ");
        String receiverAccount = sc.nextLine();
        String receiverName = bank.checkBankAccountExist(receiverAccount);
        if (receiverName == null){
            System.out.println("No Account Found");
        } else {
            System.out.println("Account Holder Name: " +receiverName);
        }
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        bank.transferAmount(index, amount, user, receiverAccount);
        System.out.println("Transaction Successful");
    }

    static void getTransactionHistory(UserAccount user, int index){
        System.out.println(bank.getUserBankTransactions(index, user));
    }

    static void deleteAccount(UserAccount user, int index){
        bank.removeBankAccount(user, index);
        System.out.println("Account Successfully Deleted");
    }

    static void exit(){
        System.out.println("Thank you for using our services!");
        exit = true;
    }
}
