package com.mehedi.couriertrack.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**", "/register", "/css/**").permitAll() // Allow registration!
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login") // Use our custom HTML page!
                .defaultSuccessUrl("/decks", true)
                .permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login") // Put Google on our custom page!
                .defaultSuccessUrl("/decks", true) 
            )
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
            
        return http.build();
    }

    // The encryption tool
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}