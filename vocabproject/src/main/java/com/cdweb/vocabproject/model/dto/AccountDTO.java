package com.cdweb.vocabproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private long id;
    private String role;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private boolean status;

    private RoleDTO roleDTO;

}