package com.cdweb.vocabproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VocabularyDTO {
    private long id;
    private String vietnamese;
    private String english;
    private boolean status;

    private SubjectDTO subjectDTO;
    private long subjectId;
}
