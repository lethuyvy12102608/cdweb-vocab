package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(optional.get());
    }
}
