package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.impl.VocabularyMapperImpl;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class TestController {

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyMapperImpl vocabularyMapper;

    @GetMapping(value = {""})
    public String loginPage(Model model) {

        Vocabulary vocabulary = vocabularyService.findById(1);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDTO(vocabulary);

        return "test1";
    }

}
