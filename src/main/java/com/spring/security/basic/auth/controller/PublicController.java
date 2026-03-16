package com.spring.security.basic.auth.controller;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import com.spring.security.basic.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user){
        UserDTO savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(Authentication authentication){

        String username = authentication.getName();
        return ResponseEntity.ok("User logged in :" + username);
    }

}
