package com.cdweb.vocabproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity implements Serializable {
    @Column(name = "id")
    private long id;
    @Column(name = "tittle")
    private String tittle ;
    @Column(name = "description")
    private String description ;
    @Column(name = "status")
    private boolean status ;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account ;


    
}
