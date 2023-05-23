package com.cdweb.vocabproject.model.mapper;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Subject;

import java.util.List;


public interface SubjectMapper {
    public SubjectDTO toDTO(Subject subject);

    public List<SubjectDTO> toListDTO(List<Subject> subjectList);

    public Subject toEntity(SubjectDTO subjectDTO);


}
