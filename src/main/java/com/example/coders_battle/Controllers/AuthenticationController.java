package com.example.coders_battle.Controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.example.coders_battle.Entity.User;
import com.example.coders_battle.Repository.UserRepository;
import com.example.coders_battle.Utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authentication")
    public void createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException, JSONException
    {
        System.out.println(authenticationRequest.getEmail()+" "+authenticationRequest.getPassword());

        try {
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("Incorrect Credentials");
        }catch(DisabledException e)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created !");
            return ; 
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if(optionalUser.isPresent())
        {
            response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId()).toString());
        }

        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.setHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, X-Requested-with, Content-Type, Accept, X-Custom-header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX+ jwt);

    }

}
