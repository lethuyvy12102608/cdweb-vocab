package com.cdweb.vocabproject.config;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class UserLoginConfig {

    @ModelAttribute("user")
    public AccountDTO getUser(Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = null;
            AccountDTO accountDTO = null;

            if (customUserDetails != null) {
                account = customUserDetails.getAccount();
            }

            if (account != null) {
                accountDTO = new AccountDTO();
                accountDTO.setUsername(account.getUsername());
                accountDTO.setFullName(account.getFullName());
                accountDTO.setEmail(account.getEmail());
            }
            return accountDTO;
        }
        return null;
    }

}
