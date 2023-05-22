package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findById(long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

}
