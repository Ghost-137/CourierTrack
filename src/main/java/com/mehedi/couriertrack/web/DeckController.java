package com.mehedi.couriertrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mehedi.couriertrack.domain.AppUser;
import com.mehedi.couriertrack.domain.AppUserRepository;
import com.mehedi.couriertrack.domain.Deck;
import com.mehedi.couriertrack.domain.DeckRepository;

@Controller
public class DeckController {

    private final DeckRepository deckRepository;
    private final AppUserRepository userRepository;

    public DeckController(DeckRepository deckRepository, AppUserRepository userRepository) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
    }

    // 1. Show the Dashboard Page
    @GetMapping("/decks")
    public String showDecks(Model model) {
        // Send ALL decks to the HTML page
        model.addAttribute("decks", deckRepository.findAll());
        // Send an EMPTY deck to the HTML page for the "Create New" form
        model.addAttribute("newDeck", new Deck()); 
        
        return "decks"; 
    }

    // 2. Catch the data when you click "Save Deck"
    @PostMapping("/saveDeck")
    public String saveDeck(Deck newDeck) {
        // Find your user account from the database
        AppUser mehedi = userRepository.findByUserName("mehedi");
        
        // Link this new deck to you!
        newDeck.setAppUser(mehedi);
        
        // Save it to the database
        deckRepository.save(newDeck);
        
        // Refresh the page
        return "redirect:/decks"; 
    }

    // 3. Delete a Deck
    @GetMapping("/deleteDeck/{id}")
    public String deleteDeck(@PathVariable("id") Long deckId) {
        deckRepository.deleteById(deckId);
        return "redirect:/decks"; // Send them back to the dashboard
    }
}