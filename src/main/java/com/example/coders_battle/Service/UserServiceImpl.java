package com.example.coders_battle.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.coders_battle.DTOs.SignupDTO;
import com.example.coders_battle.DTOs.UserDTO;
import com.example.coders_battle.Entity.User;
import com.example.coders_battle.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {

        User user = new User();

        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));

        User createduser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(createduser.getId());
        userDTO.setEmail(createduser.getEmail());
        userDTO.setName(createduser.getName());

        return userDTO;

    }

}
