package com.cdweb.vocabproject.model.mapper;

import com.cdweb.vocabproject.model.dto.VocabularyDTO;
import com.cdweb.vocabproject.model.entity.Vocabulary;

import java.util.List;

public interface VocabularyMapper {

    VocabularyDTO toDTO(Vocabulary vocabulary);

    List<VocabularyDTO> toListDTO(List<Vocabulary> vocabularies);

    Vocabulary toEntity(VocabularyDTO vocabularyDTO);
}
