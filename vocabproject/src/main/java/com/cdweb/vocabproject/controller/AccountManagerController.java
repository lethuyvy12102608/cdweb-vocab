package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Role;
import com.cdweb.vocabproject.model.mapper.impl.AccountMapperImpl;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.service.RoleService;
import com.cdweb.vocabproject.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/accounts")
public class AccountManagerController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapperImpl accountMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountValidator accountValidator;

    @GetMapping(value = {"/", ""})
    public String list(Model model) {
        List<Account> accountList = accountService.findAll();

        model.addAttribute("accountList", accountMapper.toListDTO(accountList));
        return "manager-account-list";
    }

    @GetMapping(value = {"/form/", "/form"})
    public String newAccount(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        List<Role> roles = roleService.findAll();

        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("roleListDTO", roles);
        return "manager-account-form";
    }

    @GetMapping("/form/{id}") // /manager/accounts/form/6
    public String editAccount(Model model,@PathVariable long id) {
        String redirectUrl = "/manager/accounts";
        try {
            Account account = accountService.findById(id);
            if (account == null) {
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("accountDTO", accountMapper.toDTO(account));
            model.addAttribute("roleListDTO", roleService.findAll());

            return "manager-account-form";
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = {"/form"})
    public String saveAccount(Model model, AccountDTO accountDTO, BindingResult bindingResult) {

        accountValidator.validate(accountDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "manager-account-form";
        }

        Account account = accountService.save(accountDTO);
        String redirectUrl = "/manager/accounts/form/" + account.getId();

        return "redirect:" + redirectUrl;
    }


}
