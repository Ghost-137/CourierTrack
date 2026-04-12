package com.mehedi.couriertrack.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.mehedi.couriertrack.domain.AppUser;
import com.mehedi.couriertrack.domain.AppUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // 2. Show the Registration Page
    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    // 3. Save the New User
    @PostMapping("/register")
    public String processRegistration(AppUser user) {
        // Encrypt the password before saving!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        
        userRepository.save(user);
        
        // Send them back to the login page with a success message
        return "redirect:/login?registered";
    }
    
}
