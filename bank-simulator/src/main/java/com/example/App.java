// mvn exec:java -Dexec.mainClass="com.example.App"
// mvn clean install

package com.example;

import java.util.List;
import java.util.logging.Logger;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.setup.DatabaseInitializer;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        logger.info(" Starting Bank Simulator...");

       
        DatabaseInitializer.initialize();

        AccountRepository repo = new AccountRepository();

       
        Account newAccount = new Account();
        newAccount.setCustomerId(1); 
        newAccount.setAccountType("Savings");
        newAccount.setBankName("SBI");
        newAccount.setBranch("Vizag Main");
        newAccount.setBalance(5000.00);
        newAccount.setStatus("Active");
        newAccount.setAccountNumber("SBIN0000001");
        newAccount.setIfscCode("SBIN0001234");
        newAccount.setNameOnAccount("RuchiUser");
        newAccount.setPhoneLinkedWithBank("9876543210");
        newAccount.setSavingAmount(1000.00);

        repo.saveAccount(newAccount);

       List<Account> accounts = repo.getAllAccounts();

if (accounts.isEmpty()) {
    logger.warning(" No accounts found. Please insert test data in 'account_details' table.");
} else {
    for (Account acc : accounts) {
        logger.info(" Account: ID=" + acc.getAccountId() +
                    ", Name=" + acc.getNameOnAccount() +
                    ", Balance=₹" + acc.getBalance());
    }
}


        
        repo.updateBalance(2, 7000.00);

      

        logger.info("Bank Simulator finished.");
    }
}
