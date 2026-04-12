package com.mehedi.couriertrack.web;

import java.security.Principal;
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
    public String showDecks(Model model, Principal principal) {
        String username = principal.getName(); 

        // Auto-Register new Google users
        AppUser currentUser = userRepository.findByUserName(username);
        if (currentUser == null) {
            currentUser = new AppUser(username, "google-auth-no-password", "USER");
            userRepository.save(currentUser);
        }

        // ONLY show this specific user's decks
        model.addAttribute("decks", deckRepository.findByAppUserUserName(username)); 
        model.addAttribute("newDeck", new Deck()); 
        
        return "decks";
    }

    // 2. Add a new Deck (Dynamically assigned to whoever is logged in!)
    @PostMapping("/addDeck")
    public String addDeck(Deck newDeck, Principal principal) {
        AppUser currentUser = userRepository.findByUserName(principal.getName());
        newDeck.setAppUser(currentUser); 
        deckRepository.save(newDeck);
        return "redirect:/decks";
    }

    // 3. Delete a Deck
    @GetMapping("/deleteDeck/{id}")
    public String deleteDeck(@PathVariable("id") Long deckId) {
        deckRepository.deleteById(deckId);
        return "redirect:/decks"; 
    }
}