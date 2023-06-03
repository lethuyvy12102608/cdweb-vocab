package com.cdweb.vocabproject.model.mapper;

import com.cdweb.vocabproject.model.dto.SubjectDTO;
import com.cdweb.vocabproject.model.entity.Subject;

import java.util.List;


public interface SubjectMapper {
    SubjectDTO toDTO(Subject subject);

    List<SubjectDTO> toListDTO(List<Subject> subjectList);

    Subject toEntity(SubjectDTO subjectDTO);
}
