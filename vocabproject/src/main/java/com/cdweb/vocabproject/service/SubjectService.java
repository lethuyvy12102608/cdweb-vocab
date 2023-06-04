package com.cdweb.vocabproject.service;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public Subject findByTittle(String tittle) {
        return subjectRepository.findByTittle(tittle).orElse(null);
    }

    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }

    public List<Subject> findByAccount(Account account){return subjectRepository.findByAccountAndStatus(account, true);}
}
