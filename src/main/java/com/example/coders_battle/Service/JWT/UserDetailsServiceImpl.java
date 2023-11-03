package com.example.coders_battle.Service.JWT;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.coders_battle.Entity.User;
import com.example.coders_battle.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findFirstByEmail(email);

        if(userOptional.isEmpty())
        throw new UsernameNotFoundException("User Not Found !");

        return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),userOptional.get().getEmail(),new ArrayList<>());
        }
    
}
