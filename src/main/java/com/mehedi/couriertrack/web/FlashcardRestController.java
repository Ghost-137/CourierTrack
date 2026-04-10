package com.mehedi.couriertrack.web;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehedi.couriertrack.domain.Flashcard;
import com.mehedi.couriertrack.domain.FlashcardRepository;

@RestController // Tells Spring to output raw JSON data!
public class FlashcardRestController {

    private final FlashcardRepository flashcardRepository;

    public FlashcardRestController(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    // 1. Get ALL flashcards in raw JSON format
    @GetMapping("/api/flashcards")
    public Iterable<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    // 2. Get a RANDOM flashcard (The cool feature!)
    @GetMapping("/api/random-card")
    public Flashcard getRandomCard() {
        List<Flashcard> allCards = (List<Flashcard>) flashcardRepository.findAll();
        
        // If the database is empty, return nothing to avoid a crash
        if (allCards.isEmpty()) {
            return null; 
        }
        
        // Pick a random number and return that specific card
        Random random = new Random();
        int randomIndex = random.nextInt(allCards.size());
        
        return allCards.get(randomIndex);
    }
}