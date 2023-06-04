package com.cdweb.vocabproject.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDTO {
    private long id;
    private String tittle;
    private String description;
    private boolean status;

    private AccountDTO accountDTO;
    private long accountId;

    private List<VocabularyDTO> vocabularyDTOList;
}
