package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.dto.MessageDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.util.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping(value = {"/register"})
public class RegisterController {
    private String redirectUrl = "/register";
    @Autowired
    private AccountService accountService;
    @Autowired
    private RegisterValidator registerValidator;


    @GetMapping(value = {"","/"})
    public String loginPage(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("error", null);

        return "register";
    }
    @PostMapping(value = {"", "/"})
    public String register(Model model, @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        try {
            // validate
            registerValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("messageDTO", new MessageDTO("error",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("accountDTO", accountDTO);
                return "register";
            } else {
                // save
                Account account = accountService.register(accountDTO);

                if (account != null) {
                    redirectUrl = "/login";
                    return "redirect:" + redirectUrl;
                }

                model.addAttribute("messageDTO", new MessageDTO("error",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("accountDTO", accountDTO);
                return "login";
            }
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }

}
