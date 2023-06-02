package com.cdweb.vocabproject.repository;

import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    List<Vocabulary> findBySubject(Subject subject);
    
}
