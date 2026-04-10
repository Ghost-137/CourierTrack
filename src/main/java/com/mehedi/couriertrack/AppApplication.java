package com.mehedi.couriertrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mehedi.couriertrack.domain.AppUser;
import com.mehedi.couriertrack.domain.AppUserRepository;
import com.mehedi.couriertrack.domain.Deck;
import com.mehedi.couriertrack.domain.DeckRepository;
import com.mehedi.couriertrack.domain.Flashcard;
import com.mehedi.couriertrack.domain.FlashcardRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(AppUserRepository userRepository, DeckRepository deckRepository, FlashcardRepository flashcardRepository) {
        return (args) -> {
            
            // Check the cloud database first!
            if (userRepository.findByUserName("mehedi") == null) {
                
                // 1. Create a Test User
                AppUser mehedi = new AppUser("mehedi", "password123", "USER");
                userRepository.save(mehedi);

                // 2. Create a Test Deck
                Deck finnishDeck = new Deck("Finnish Basics", "FI", mehedi);
                deckRepository.save(finnishDeck);

                // 3. Add some Flashcards
                Flashcard card1 = new Flashcard("Hello", "Moi", finnishDeck);
                Flashcard card2 = new Flashcard("Thank you", "Kiitos", finnishDeck);
                
                flashcardRepository.save(card1);
                flashcardRepository.save(card2);

                System.out.println("LINGUAVAULT TEST DATA LOADED TO CLOUD!");
            } else {
                System.out.println("CLOUD DATA ALREADY EXISTS. SKIPPING SETUP!");
            }
            
        };
    }

}
