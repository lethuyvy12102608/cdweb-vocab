package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.mapper.SubjectMapper;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/subjects")
public class SubjectManagerController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"/", ""})
    public String list(Model model) {
        List<Subject> subjectList = subjectService.findAll();

        model.addAttribute("subjectList", subjectMapper.toListDTO(subjectList));
        return "manager-subject-list";
    }

    @GetMapping(value = {"/form/", "/form"})
    public String newSubject(Model model) {
        SubjectDTO subjectDTO = new SubjectDTO();
        List<Account> accounts = accountService.findAll();

        model.addAttribute("subjectDTO", subjectDTO);
        model.addAttribute("accountListDTO", accounts);
        return "manager-subject-form";

    }

    @PostMapping(value ={ "/form"})
    public String saveSubject(Model model,SubjectDTO subjectDTO){
        subjectDTO.setStatus(true);
        Subject subject = subjectService.save(subjectMapper.toEntity(subjectDTO));
        String redirectUrl = "/manager/subjects/form/" + subject.getId();
        return "redirect:" + redirectUrl;

    }
    @GetMapping("/form/{id}") // /manager/accounts/form/6
    public String editSubject(Model model,@PathVariable long id) {
        String redirectUrl = "/manager/subjects";
        try {
            Subject subject = subjectService.findById(id);
            if (subject == null) {
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("subjectDTO", subjectMapper.toDTO(subject));
            model.addAttribute("accountListDTO", accountService.findAll());

            return "manager-subject-form";
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping("/delete/{id}") // /manager/accounts/form/6
    public String deleteSubject(Model model,@PathVariable long id) {
        String redirectUrl = "/manager/subjects";
        try {
            Subject subject = subjectService.findById(id);
            if (subject == null) {
                return "redirect:" + redirectUrl;
            }
            subject.setStatus(false);
            subjectService.save(subject);

            return "redirect:" + redirectUrl;
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }

}
