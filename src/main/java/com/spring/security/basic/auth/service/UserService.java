package com.spring.security.basic.auth.service;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import com.spring.security.basic.auth.mapper.ModelMapperConfig;
import com.spring.security.basic.auth.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapperConfig;

    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return modelMapperConfig.map(save, UserDTO.class);
    }

}
