package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.VocabularyMapper;
import com.cdweb.vocabproject.service.SubjectService;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/vocabulary")
public class VocabularyManagerController {
    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = {"/", ""})
    public String list(Model model) {
        List<Vocabulary> vocabularyList = vocabularyService.findAll();

        model.addAttribute("vocabularyList", vocabularyMapper.toListDTO(vocabularyList));
        return "manager-vocabulary-list";
    }

    @GetMapping(value = {"/form/", "/form"})
    public String newVocabulary(Model model) {
        VocabularyDTO vocabularyDTO = new VocabularyDTO();
        List<Subject> subjects = subjectService.findAll();

        model.addAttribute("vocabularyDTO", vocabularyDTO);
        model.addAttribute("subjectListDTO", subjects);
        return "manager-vocabulary-form"     ;

    }

    @PostMapping(value = {"/form"})
    public String saveVocabulary(Model model, VocabularyDTO vocabularyDTO) {
        vocabularyDTO.setStatus(true);
        Vocabulary vocabulary = vocabularyService.save(vocabularyMapper.toEntity(vocabularyDTO));
        String redirectUrl = "/manager/vocabulary/";



        return "redirect:" + redirectUrl;
    }


    @GetMapping("/form/{id}")
    public String editSubject(Model model,@PathVariable long id) {
        String redirectUrl = "/manager/vocabulary";
        try {
            Vocabulary vocabulary = vocabularyService.findById(id);

            if (vocabulary == null) {
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("vocabulary", vocabularyMapper.toDTO(vocabulary));
            model.addAttribute("SubjectListDTO", subjectService.findAll());

            return "manager-subject-form";
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping("/delete/{id}") // /manager/accounts/form/6
    public String deleteSubject(Model model,@PathVariable long id) {
        String redirectUrl = "/manager/vocabulary";
        try {
            Vocabulary vocabulary = vocabularyService.findById(id);

            if (vocabulary == null) {
                return "redirect:" + redirectUrl;
            }
            vocabulary.setStatus(false);
            vocabularyService.save(vocabulary);

            return "redirect:" + redirectUrl;
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }
}
