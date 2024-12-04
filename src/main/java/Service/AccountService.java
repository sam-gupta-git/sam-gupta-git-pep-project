package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    /**
     * Default constructor
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Parameterized constructor
     * @param accountDAO Other accountDAO to initialize this.accountDAO
     */ 
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * Method to register an account, call accountDAO to insert new account object into table and return new account object
     * @param account Account object sent to DAO containing account information
     */
    public Account registerAccount(Account account){
        return accountDAO.insertAccount(account);
    }

    /**
     * Method to verify a login, call accountDAO to get account with matching username and password and return account if successful
     * @param username The username of the login attempt
     * @param password The password of the login attempt
     */
    public Account verifyLogin(String username, String password){
        return accountDAO.getAccountByLogin(username, password);
    }

    /**
     * Method to return an account specified by its ID, returns account object if successful
     * @param account_id The ID of the account to be retrieved
     */
    public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
    }

    /**
     * Method to return an account specified by its username, returns account object if successful
     * @param username The username string of the account to be retrieved
     */
    public Account getAccountByUsername(String username){
        return accountDAO.getAccountByUsername(username);
    }
}
