package com.cdweb.vocabproject.model.mapper.impl;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import com.cdweb.vocabproject.model.mapper.SubjectMapper;
import com.cdweb.vocabproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapperIml implements SubjectMapper {

    @Autowired
    private SubjectService subjectService;

    @Override
    public SubjectDTO toDTO (Subject subject){
        if(subject == null) return null ;

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setTittle(subject.getTittle());
        subjectDTO.setDescription(subject.getDescription());
        subjectDTO.setStatus(subject.isStatus());

        AccountDTO accountDTO = new AccountDTO();
        Account account = new Account();
        if(account!= null) {
            accountDTO.setId(account.getId());
            accountDTO.setFullName(account.getFullName());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setUsername(account.getUsername());
            accountDTO.setStatus(account.isStatus());
        }
        subjectDTO.setAccountDTO(accountDTO);

        return subjectDTO ;

    }
    @Override
    public List<SubjectDTO> toListDTO(List<Subject> subjectList){
        if(subjectList==null) return null;
        List<SubjectDTO> result = new ArrayList<>();
        subjectList.forEach(element -> result.add(toDTO(element)));
        return result ;

    }
    @Override
    public Subject toEntity(SubjectDTO subjectDTO){
        if (subjectDTO==null) return null;

        Subject subject = subjectService.findById(subjectDTO.getId());
        if (subject == null) subject = new Subject();

        subject.setTittle(subjectDTO.getTittle());
        subject.setDescription(subjectDTO.getDescription());
        subject.setStatus(subjectDTO.isStatus());

        return subject;
    }
}
