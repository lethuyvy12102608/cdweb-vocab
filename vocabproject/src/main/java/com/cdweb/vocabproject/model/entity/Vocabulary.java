package com.cdweb.vocabproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vocabulary")
public class Vocabulary extends BaseEntity {
    @Column(name = "vietnamese")
    String vietnamese;
    @Column(name = "english")
    String english;
    @Column(name = "status")
    boolean status;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
