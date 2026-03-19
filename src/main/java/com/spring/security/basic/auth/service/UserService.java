package com.spring.security.basic.auth.service;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import com.spring.security.basic.auth.mapper.MapStructMapper;
import com.spring.security.basic.auth.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapperConfig;

    @Autowired
    private MapStructMapper mapStructMapper;

    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return modelMapperConfig.map(save, UserDTO.class);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDTO getUserByIdCached(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found..."));

        return mapStructMapper.toDto(user);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found..."));

        return mapStructMapper.toDto(user);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @CacheEvict(value = "users", key = "#id")
    public void deleteUserByIdCached(Long id) {
        userRepository.deleteById(id);
    }


    @CachePut(value = "users", key = "#id")
    public UserDTO updateUserByIdCached(Long id, User user) {
        User userForUpdate = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found for id..."));

        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userForUpdate.setPhone(user.getPhone());
        userForUpdate.setRoles(user.getRoles());
        userRepository.save(userForUpdate);

        return mapStructMapper.toDto(userForUpdate);
    }
}
