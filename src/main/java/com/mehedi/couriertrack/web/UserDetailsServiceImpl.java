package com.mehedi.couriertrack.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mehedi.couriertrack.domain.AppUser;
import com.mehedi.couriertrack.domain.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    public UserDetailsServiceImpl(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUserName(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        
        // Give Spring the username, encrypted password, and role
        return new org.springframework.security.core.userdetails.User(
            username, 
            curruser.getPassword(),
            AuthorityUtils.createAuthorityList(curruser.getRole())
        );
    }
}