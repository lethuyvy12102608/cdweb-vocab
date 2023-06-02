package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import com.cdweb.vocabproject.repository.SubjectRepository;
import com.cdweb.vocabproject.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyService {
    @Autowired
    private VocabularyRepository vocabularyRepository;

    public List<Vocabulary> findAll(){
        return vocabularyRepository.findAll();
    }
    public Vocabulary findById(long id) {
        return vocabularyRepository.findById(id).orElse(null);
    }

    public Vocabulary save(Vocabulary vocabulary){
        return vocabularyRepository.save(vocabulary);
    }
    public List<Vocabulary> findBySubject(Subject subject) {
        return vocabularyRepository.findBySubject(subject);
    }
}
