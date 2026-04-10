package com.mehedi.couriertrack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String frontText; // e.g., "To speak"
    private String backText;  // e.g., "Puhua"


    private boolean isFavorite = false; // New field to mark as favorite
    private boolean needsPractice = false; // New field to track if it needs practice
    // THE RELATIONSHIP: Many Flashcards belong to One Deck
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    // 1. Empty Constructor
    public Flashcard() {}

    // 2. Constructor with parameters
    public Flashcard(String frontText, String backText, Deck deck) {
        this.frontText = frontText;
        this.backText = backText;
        this.deck = deck;
    }

    // 3. Getters and Setters
    public Long getId() { return id; }

    public String getFrontText() { return frontText; }
    public void setFrontText(String frontText) { this.frontText = frontText; }

    public String getBackText() { return backText; }
    public void setBackText(String backText) { this.backText = backText; }

    public Deck getDeck() { return deck; }
    public void setDeck(Deck deck) { this.deck = deck; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean isFavorite) { this.isFavorite = isFavorite; }

    public boolean isNeedsPractice() { return needsPractice; }
    public void setNeedsPractice(boolean needsPractice) { this.needsPractice = needsPractice; }
}