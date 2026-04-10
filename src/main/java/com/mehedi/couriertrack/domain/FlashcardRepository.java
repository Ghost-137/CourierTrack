package com.mehedi.couriertrack.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
    List<Flashcard> findByDeckId(Long deckId);

   // Keep this one the same (because the variable is actually named isFavorite)
    List<Flashcard> findByDeckAppUserUserNameAndIsFavoriteTrue(String userName);
    
    // REMOVE the "Is" from this one to match the needsPractice variable!
    List<Flashcard> findByDeckAppUserUserNameAndNeedsPracticeTrue(String userName);
}