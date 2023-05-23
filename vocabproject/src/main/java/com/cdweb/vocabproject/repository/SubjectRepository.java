package com.cdweb.vocabproject.repository;

import com.cdweb.vocabproject.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Optional<Subject> findByTittle(String tittle);

}
