package com.selaz.ms.infra.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;

import java.util.ArrayList;


@Component
public class CustomUserDetailsService implements UserDetailsService{

    private UserJpaRepository userRepository;

    public CustomUserDetailsService(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJpaModel user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getNivel().getMessate(), new ArrayList<>());
    }
    
}