package com.example.coders_battle.Service;

import com.example.coders_battle.DTOs.SignupDTO;
import com.example.coders_battle.DTOs.UserDTO;

public interface UserService {

    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserwithEmail(String string);
    
}
