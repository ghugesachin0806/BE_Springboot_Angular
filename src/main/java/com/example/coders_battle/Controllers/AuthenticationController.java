package com.example.coders_battle.Controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.coders_battle.DTOs.AuthenticationRequest;
import com.example.coders_battle.DTOs.AuthenticationResponse;
import com.example.coders_battle.Utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
     @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken (@RequestBody AuthenticationRequest AuthenticationRequest,HttpServletResponse response) throws IOException
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(AuthenticationRequest.getEmail(),AuthenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Incorrect Credentials");
        }catch(DisabledException e)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created !");
            return null; 
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(AuthenticationRequest.getEmail());


        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);

    }
    
}
