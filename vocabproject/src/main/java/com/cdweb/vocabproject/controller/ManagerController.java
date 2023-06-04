package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.service.SubjectService;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/home") // localhost:8080/manager/home
public class ManagerController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private VocabularyService vocabularyService ;

    @GetMapping(value = {"/", ""})
    public String homeManager(Model model) {
        List<Account> accounts = accountService.findAll();
        List<Subject> subjects = subjectService.findAll();
        List<Vocabulary> vocabularies = vocabularyService.findAll();


        model.addAttribute("accountSize", accounts.size());
        model.addAttribute("subjectSize",subjects.size());
        model.addAttribute("vocabularySize",vocabularies.size());
        return "manager-home";
    }


}
