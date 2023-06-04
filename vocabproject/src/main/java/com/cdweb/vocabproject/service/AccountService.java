package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Role;
import com.cdweb.vocabproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }
    public Account save(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        Account account = findById(accountDTO.getId());
        if (account == null) {
            account = new Account();
        }

        if (accountDTO.getId() == 0) {
            String password = "123456";
            String encodedPassword = passwordEncoder.encode(password);
            account.setPassword(encodedPassword);
        }

        // role
        Role role = roleService.findById(Long.parseLong(accountDTO.getRole()));
        account.setRole(role);

        // account
        account.setId(accountDTO.getId());
        account.setFullName(accountDTO.getFullName().trim());
        account.setEmail(accountDTO.getEmail().trim());
        account.setUsername(accountDTO.getUsername().trim());
        account.setStatus(accountDTO.isStatus());

        return accountRepository.save(account);
    }
    public Account register(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        Account account = new Account();
        // role
        Role role = roleService.findByName("USER");
        account.setRole(role);

        // account
        account.setId(accountDTO.getId());
        account.setFullName(accountDTO.getFullName().trim());
        account.setUsername(accountDTO.getUsername().trim());
        String encodedPassword = passwordEncoder.encode(accountDTO.getPassword());
        account.setPassword(encodedPassword);
        account.setEmail(accountDTO.getUsername().trim());
        account.setStatus(true);

        return accountRepository.save(account);
    }



}
