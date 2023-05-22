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

    
}
