package com.spring.security.basic.auth.controller;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import com.spring.security.basic.auth.service.UserService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(){
        return "user profile";
    }


    @GetMapping("/{Id}")
    public UserDTO getUserById(@PathVariable Long Id, Authentication authentication){

        String username = authentication.getName();
        User currentUser = userService.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found."));

        if (!currentUser.getId().equals(Id)){
            throw new RuntimeException("Access Denied!");
        }

        return userService.getUserById(Id);
    }
}
