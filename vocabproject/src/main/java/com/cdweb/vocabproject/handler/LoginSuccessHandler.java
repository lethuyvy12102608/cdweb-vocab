package com.cdweb.vocabproject.handler;

import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectURL = "/login";
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getAccount();

            if (account != null) {
                switch (account.getRole().getName().toUpperCase()) {
                    case "ADMIN":
                        redirectURL = "/manager/home";
                        break;
                    case "USER":
                        redirectURL = "/home";
                        break;
                    default:
                        redirectURL = "/login";
                        break;
                }
            }

            response.sendRedirect(redirectURL);
        } catch (Exception ex) {
            response.sendRedirect(redirectURL);
        }
    }

}
