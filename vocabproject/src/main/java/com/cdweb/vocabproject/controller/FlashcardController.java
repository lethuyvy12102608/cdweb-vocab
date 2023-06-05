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

    @GetMapping("/{subjectId}")
    public String flashcardHome(Model model, @PathVariable long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        Vocabulary vocabulary = new Vocabulary();
        List<Vocabulary> vocabularies = vocabularyService.findBySubject(subject);
        if (vocabularies.size() != 0) {
            model.addAttribute("vocabulariesDTO", vocabularyMapper.toListDTO(vocabularies));
            model.addAttribute("firstVocab", vocabularies.get(0));
            vocabularies.remove(0);
            model.addAttribute("vocabularyDTO", vocabulary);
            model.addAttribute("title", subject.getTittle());
            model.addAttribute("vocabularies", vocabularyMapper.toListDTO(vocabularies));
            return "flashcard";
        }
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("vocabularyDTO", vocabulary);
        model.addAttribute("vocabulariesDTO", null);
        return "flashcard";
    }

    @RequestMapping("/{subjectId}/edit")
    public String editFlashcard(Model model, @PathVariable long subjectId) {
        return "edit-flashcard";
    }

    @PostMapping("/{subjectId}")
    public String addFlashcard(@PathVariable long subjectId, VocabularyDTO vocabularyDTO){
        vocabularyDTO.setStatus(true);
        vocabularyDTO.setSubjectId(subjectId);
        Vocabulary vocabulary = vocabularyMapper.toEntity(vocabularyDTO);
        vocabularyService.save(vocabulary);
        String redirectUrl = "/flashcard/" + subjectId;
        return "redirect:" + redirectUrl;
    }

}