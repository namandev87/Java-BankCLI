package in.bankcli;

import java.util.ArrayList;

class BankingSystem {

    ArrayList<UserAccount> userAccounts = new ArrayList<>();

    void createNewUserAccount(String userName, String userId, String password){
        userAccounts.add(new UserAccount(userId, userName, password));
    }

    boolean checkUserId(String userid){
        for (UserAccount userAccount : userAccounts) {
            if(userAccount != null && userAccount.getUserId().equals(userid)){
                return true;
            }
        }
        return false;
    }

    UserAccount checkCredentials(String userId, String password){
        for (UserAccount userAccount : userAccounts) {
            if(userAccount.getUserId().equals(userId)){
                if(userAccount.getPassword().equals(password)){
                    return userAccount;
                }
                return null;
            }
        }
        return null;
    }

    String createNewUserBankAccount(UserAccount user, int accountType, double deposit){
        return user.createNewBankAccount(accountType, deposit);
    }

    int noOfUserBankAccounts(UserAccount user){
        return user.getNoOfBankAccounts();
    }

    String userBankAccountNumber(UserAccount user, int index){
        return user.getBankAccountNumber(index);
    }

    String userBankAccountType(UserAccount user, int index){
        return user.getUserBankAccountType(index) == 1 ? "Savings" : "Current";
    }

    double userBankAccountBalance (UserAccount user, int index){
        return user.getUserBankAccountBalance(index);
    }

    void userDepositAmount(int index, double amount, UserAccount user){
        user.depositAmount(amount, index);
    }

    void userWithdrawAmount(int index, double amount, UserAccount user){
        user.withdrawAmount(amount, index);
    }

    String checkBankAccountExist(String accountNumber){
        for (int i = 0; i < userAccounts.size(); i++){
            for(int j = 0; j < userAccounts.get(i).getNoOfBankAccounts(); j++){
                if (userAccounts.get(i).getBankAccountNumber(j).equals(accountNumber)){
                    return userAccounts.get(i).getUserName();
                }
            }
        }
        return null;
    }

    void transferAmount(int index, double amount, UserAccount sender, String receiverAccount){
        sender.withdrawAmount(amount, index);
        for (int i = 0; i < userAccounts.size(); i++){
            for(int j = 0; j < userAccounts.get(i).getNoOfBankAccounts(); j++){
                if (userAccounts.get(i).getBankAccountNumber(j).equals(receiverAccount)){
                    userAccounts.get(i).depositAmount(amount, j);
                }
            }
        }
    }

    String getUserBankTransactions(int index, UserAccount user){
        return user.getBankTransactions(index);
    }

    void removeBankAccount(UserAccount user, int index){
        user.deleteBankAccount(index);
    }
}
