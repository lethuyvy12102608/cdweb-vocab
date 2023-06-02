package com.cdweb.vocabproject.controller;

import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.SubjectMapper;
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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/flashcard")
public class FlashcardController {

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectMapper subjectMapper;

    @GetMapping("/{subjectId}")
    public String flashcardHome(Model model, @PathVariable long subjectId){
        Subject subject = subjectService.findById(subjectId);
        List<Vocabulary> vocabularies = vocabularyService.findBySubject(subject);
        model.addAttribute("firstVocab", vocabularies.get(0));
        vocabularies.remove(0);
        model.addAttribute("vocabularies", vocabularies);
        return "flashcard";
    }
}