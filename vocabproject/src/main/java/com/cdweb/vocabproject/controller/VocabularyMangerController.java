package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.impl.SubjectMapperIml;
import com.cdweb.vocabproject.model.mapper.impl.VocabularyMapperImpl;
import com.cdweb.vocabproject.service.SubjectService;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/manager/vocabulary")
public class VocabularyMangerController {
    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyMapperImpl vocabularyMapper;

    @Autowired
    private SubjectMapperIml subjectMapperIml;

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
//        List<Subject> subjects = subjectService.findAll();

        model.addAttribute("vocabularyDTO", vocabularyDTO);
//        model.addAttribute("subjectListDTO", subjects);
        return "manager-vocabulary-form";

    }

    @PostMapping(value = {"/form"})
    public String saveVocabulary(Model model, VocabularyDTO vocabularyDTO) {
        vocabularyDTO.setStatus(true);
        Vocabulary vocabulary = vocabularyService.save(vocabularyMapper.toEntity(vocabularyDTO));
        String redirectUrl = "/manager/vocabulary/form/" + vocabulary.getId();

        return "redirect:" + redirectUrl;
    }
}
