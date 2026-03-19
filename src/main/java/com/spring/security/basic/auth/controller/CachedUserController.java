package com.spring.security.basic.auth.controller;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import com.spring.security.basic.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cachedUser")
public class CachedUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Health check is mandatory.";
    }


    @GetMapping("/{id}")
    public UserDTO getUserByIdCached(@PathVariable Long id){
        return userService.getUserByIdCached(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUserByIdCached(@PathVariable Long id){
        userService.deleteUserByIdCached(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUserByIdCached(@PathVariable Long id, @RequestBody User user){
        return userService.updateUserByIdCached(id, user);
    }
}
