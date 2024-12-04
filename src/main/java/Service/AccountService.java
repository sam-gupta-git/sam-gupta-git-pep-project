package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account){
        return accountDAO.insertAccount(account);
    }

    public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
    }

    public Account getAccountByUsername(String username){
        return accountDAO.getAccountByUsername(username);
    }
}
