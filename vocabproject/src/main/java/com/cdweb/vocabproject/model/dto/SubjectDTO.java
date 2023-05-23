package com.cdweb.vocabproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {
    private long id ;
    private String tittle ;
    private String description ;
    private boolean status ;
    private AccountDTO accountDTO ;
}
