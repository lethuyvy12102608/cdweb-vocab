package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.SubjectMapper;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.service.CustomUserDetails;
import com.cdweb.vocabproject.service.SubjectService;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"", "/"})
    public String subjectPage(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        List<Subject> subjectList = subjectService.findByAccount(account);

        SubjectDTO subjectDTO = new SubjectDTO();

        model.addAttribute("subjectDTO", subjectDTO);
        model.addAttribute("subjectList", subjectMapper.toListDTO(subjectList));
        return "home";
    }


    @PostMapping(value = {"", "/"})
    public String saveSubject(Model model, Authentication authentication, SubjectDTO subjectDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();

        Subject subject = subjectService.save(subjectMapper.toEntity(subjectDTO));
        subject.setStatus(true);
        subject.setAccount(account);

        String redirect = "/home";
        return "redirect:" + redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable long id) {
        String redirectUrl = "/home";
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            return "redirect:" + redirectUrl;
        }
        subject.setStatus(false);
        subjectService.save(subject);
        return "redirect:" + redirectUrl;
    }
}
