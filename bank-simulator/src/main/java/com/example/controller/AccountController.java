package com.example.controller;

import java.util.List;

import com.example.model.Account;
import com.example.repository.AccountRepository;

public class AccountController {

    private final AccountRepository accountRepository = new AccountRepository();

   
    public void createAccount(Account account) {
        accountRepository.saveAccount(account);
    }

  
    public Account getAccountById(int id) {
        return accountRepository.getAccountById(id);
    }

   
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

  
    public void updateBalance(int accountId, double newBalance) {
        accountRepository.updateBalance(accountId, newBalance);
    }

    
    public void deleteAccount(int accountId) {
        accountRepository.deleteAccount(accountId);
    }
}

