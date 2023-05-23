package com.cdweb.vocabproject.model.mapper.impl;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.model.mapper.VocabularyMapper;
import com.cdweb.vocabproject.service.SubjectService;
import com.cdweb.vocabproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VocabularyMapperImpl implements VocabularyMapper {

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public VocabularyDTO toDTO(Vocabulary vocabulary) {
        if(vocabulary == null)
            return null;
        VocabularyDTO vocabularyDTO = new VocabularyDTO();
        vocabularyDTO.setId(vocabulary.getId());
        vocabularyDTO.setEnglish(vocabulary.getEnglish());
        vocabularyDTO.setVietnamese(vocabulary.getVietnamese());
        vocabularyDTO.setStatus(vocabulary.isStatus());
        vocabularyDTO.setSubjectId(vocabulary.getSubject().getId());

        SubjectDTO subjectDTO = new SubjectDTO();
        Subject subject = vocabulary.getSubject();
        subjectDTO.setTittle(subject.getTittle());
        subjectDTO.setDescription(subject.getDescription());
        subjectDTO.setStatus(subject.isStatus());
        vocabularyDTO.setSubjectDTO(subjectDTO);

        return vocabularyDTO;
    }

    @Override
    public List<VocabularyDTO> toListDTO(List<Vocabulary> vocabularies) {
        if(vocabularies == null)
            return null;
        List<VocabularyDTO> vocabularyDTOS = new ArrayList<>();
        for (Vocabulary vocabulary: vocabularies) {
            VocabularyDTO vocabularyDTO = toDTO(vocabulary);
            if (vocabularyDTO != null) {
                vocabularyDTOS.add(vocabularyDTO);
            }
        }
        return vocabularyDTOS;
    }

    @Override
    public Vocabulary toEntity(VocabularyDTO vocabularyDTO) {
        if(vocabularyDTO == null) {
            return null;
        }
        Vocabulary vocabulary = vocabularyService.findById(vocabularyDTO.getId());

        if (vocabulary == null) {
            vocabulary = new Vocabulary();
        }
        vocabulary.setVietnamese(vocabularyDTO.getVietnamese());
        vocabulary.setEnglish(vocabularyDTO.getEnglish());
        vocabulary.setStatus(vocabularyDTO.isStatus());

        Subject subject = subjectService.findById(vocabularyDTO.getSubjectId());
        vocabulary.setSubject(subject);

        return vocabulary;
    }

}
