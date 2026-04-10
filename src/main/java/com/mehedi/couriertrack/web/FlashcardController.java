package com.mehedi.couriertrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mehedi.couriertrack.domain.Deck;
import com.mehedi.couriertrack.domain.DeckRepository;
import com.mehedi.couriertrack.domain.Flashcard;
import com.mehedi.couriertrack.domain.FlashcardRepository;

@Controller
public class FlashcardController {

    private final FlashcardRepository flashcardRepository;
    private final DeckRepository deckRepository;

    public FlashcardController(FlashcardRepository flashcardRepository, DeckRepository deckRepository) {
        this.flashcardRepository = flashcardRepository;
        this.deckRepository = deckRepository;
    }

    // 1. Show the specific Deck and its cards
    // The {id} in the URL is dynamic (e.g., /deck/1, /deck/2)
    @GetMapping("/deck/{id}")
    public String showDeckManager(@PathVariable("id") Long deckId, Model model) {
        // Find the specific deck we clicked on
        Deck currentDeck = deckRepository.findById(deckId).orElseThrow();
        
        // Send the deck, its specific cards, and an empty card to the HTML
        model.addAttribute("deck", currentDeck);
        model.addAttribute("flashcards", flashcardRepository.findByDeckId(deckId));
        model.addAttribute("newCard", new Flashcard());
        
        return "deck-manager"; 
    }

    // 2. Catch the data when you click "Add Card"
    @PostMapping("/deck/{id}/addCard")
    public String addFlashcard(@PathVariable("id") Long deckId, Flashcard newCard) {
        // Find the deck we are currently looking at
        Deck currentDeck = deckRepository.findById(deckId).orElseThrow();
        
        // Put the new card inside this deck!
        newCard.setDeck(currentDeck);
        flashcardRepository.save(newCard);
        
        // Refresh the page
        return "redirect:/deck/" + deckId;
    }

    // 3. The actual Study Mode!
    @GetMapping("/study/{id}")
    public String studyDeck(@PathVariable("id") Long deckId, Model model) {
        Deck currentDeck = deckRepository.findById(deckId).orElseThrow();
        
        model.addAttribute("deck", currentDeck);
        // We send the cards to the page so our JavaScript can flip through them
        model.addAttribute("flashcards", flashcardRepository.findByDeckId(deckId));
        
        return "study";
    }
    // 4. Delete a Flashcard
    @GetMapping("/deleteCard/{deckId}/{cardId}")
    public String deleteFlashcard(@PathVariable("deckId") Long deckId, @PathVariable("cardId") Long cardId) {
        flashcardRepository.deleteById(cardId);
        return "redirect:/deck/" + deckId; // Send them back to that specific deck manager
    }
}