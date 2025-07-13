package in.bankcli;

import java.util.ArrayList;

class UserBankAccount {
    private int accountType; //1 -> Savings/ 2 -> Current
    private double balance = 0;
    private String accountHolderName;
    private String accountNumber;
    private static int nextAccountNumber;
    private String transactions = "";
    private int transactionsCount = 0;

    static {
        nextAccountNumber = 1000000;
    }

    {
        nextAccountNumber++;
    }

    UserBankAccount(int accountType, double balance, String accountHolderName){
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        depositAmount(balance);
        accountNumber = (accountType == 1 ? "S-" : "C-") +nextAccountNumber;
    }

    String getAccountNumber(){
        return accountNumber;
    }

    int getAccountType(){
        return accountType;
    }

    double getBalance(){
        return balance;
    }

    void depositAmount(double amount){
        balance += amount;
        transactions += ++transactionsCount+ ". Credit: " +amount+ "\n";
    }

    void withdrawAmount(double amount){
        balance -= amount;
        transactions += ++transactionsCount+ ". Debit: " +amount+ "\n";
    }

    String getTransactions(){
        return transactions;
    }
}
