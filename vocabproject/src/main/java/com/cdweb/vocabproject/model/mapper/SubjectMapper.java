package com.cdweb.vocabproject.model.mapper;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.model.entity.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapper {

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
    public List<SubjectDTO> toListDTO(List<Subject> subjectList){
        if(subjectList==null) return null;
        List<SubjectDTO> result = new ArrayList<>();
        subjectList.forEach(element -> result.add(toDTO(element)));
        return result ;

    }

    public Subject toEntity(Subject subject , SubjectDTO subjectDTO){
        if (subject==null) return null;


        subject.setTittle(subjectDTO.getTittle());
        subject.setDescription(subjectDTO.getDescription());
        subject.setStatus(subjectDTO.isStatus());

        return subject;
    }
}
