package com.mehedi.couriertrack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;      // e.g., "Finnish Grammar"
    private String language;  // e.g., "FI" or "IT"

    // THE RELATIONSHIP: Many Decks belong to One AppUser
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    // 1. Empty Constructor (Hibernate needs this!)
    public Deck() {}

    // 2. Constructor with parameters
    public Deck(String name, String language, AppUser appUser) {
        this.name = name;
        this.language = language;
        this.appUser = appUser;
    }

    // 3. Getters and Setters
    public Long getId() { return id; }
    // We usually don't need a setId because the database auto-generates it

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public AppUser getAppUser() { return appUser; }
    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}