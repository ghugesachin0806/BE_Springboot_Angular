package com.example.coders_battle.DTOs;
import lombok.Data;

@Data
public class AuthenticationRequest {
    
    private String email;
    private String password;
}
