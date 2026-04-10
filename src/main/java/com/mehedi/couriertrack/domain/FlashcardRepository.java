package com.mehedi.couriertrack.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
    List<Flashcard> findByDeckId(Long deckId);
}