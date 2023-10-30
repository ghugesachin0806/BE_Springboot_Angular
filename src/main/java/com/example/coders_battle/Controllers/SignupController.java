package com.example.coders_battle.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.coders_battle.DTOs.SignupDTO;
import com.example.coders_battle.DTOs.UserDTO;
import com.example.coders_battle.Service.UserService;

@RestController
public class SignupController {

   @Autowired
   private UserService userService;

   @PostMapping({ "/sign-up" })
   public ResponseEntity<?> createUser(@RequestBody(required = true) SignupDTO SignupDTO) {

      if (userService.hasUserwithEmail(SignupDTO.getEmail()))
         return new ResponseEntity<>("User already Registered with email : " + SignupDTO.getEmail(), HttpStatus.NOT_ACCEPTABLE);

      UserDTO userDTO = userService.createUser(SignupDTO);

      if (userDTO == null)
         return new ResponseEntity<>("User not created, Come again !", HttpStatus.BAD_REQUEST);

      return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
   }
}
