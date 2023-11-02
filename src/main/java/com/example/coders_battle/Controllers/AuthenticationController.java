package com.example.coders_battle.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.coders_battle.DTOs.AuthenticationRequest;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    public void createAuthenticationToken (@RequestBody AuthenticationRequest AuthenticationRequest,HttpServletResponse response) throws IOException
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(AuthenticationRequest.getEmail(),AuthenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Incorrect Credentials");
        }catch(DisabledException e)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created !");
            return; 
        }
    }
    
}
