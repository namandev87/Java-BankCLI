package in.bankcli;

import java.util.ArrayList;

class UserAccount {
    private String userId;
    private String userName;
    private String password;
    private ArrayList<UserBankAccount> bankAccounts = new ArrayList<>();

    UserAccount(String userId, String userName, String password){
        this.password = password;
        this.userId = userId;
        this.userName = userName;
    }

    String getUserId(){
        return userId;
    }

    String getPassword(){
        return password;
    }

    String createNewBankAccount(int accountType, double deposit){
        bankAccounts.add(new UserBankAccount(accountType, deposit, userName));
        return bankAccounts.getLast().getAccountNumber();
    }

    int getNoOfBankAccounts(){
        return bankAccounts.size();
    }

    String getBankAccountNumber(int index){
        return bankAccounts.get(index).getAccountNumber();
    }

    String getUserName(){
        return userName;
    }

    int getUserBankAccountType(int index){
        return bankAccounts.get(index).getAccountType();
    }

    double getUserBankAccountBalance(int index){
        return bankAccounts.get(index).getBalance();
    }

    void depositAmount(double amount, int index){
        bankAccounts.get(index).depositAmount(amount);
    }

    void withdrawAmount(double amount, int index){
        bankAccounts.get(index).withdrawAmount(amount);
    }

    String getBankTransactions(int index){
        return bankAccounts.get(index).getTransactions();
    }

    void deleteBankAccount(int index){
        bankAccounts.remove(index);
    }
}
