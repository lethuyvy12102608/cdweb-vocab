package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    public Subject findById(long subjectId){return subjectRepository.findById(subjectId).orElse(null);}
    public Subject findByTittle(String tittle){return subjectRepository.findByTittle(tittle).orElse(null);}

}
