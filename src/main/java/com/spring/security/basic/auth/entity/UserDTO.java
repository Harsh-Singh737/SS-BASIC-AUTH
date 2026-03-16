package com.spring.security.basic.auth.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.security.basic.auth.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","username","email","roles"})
public class UserDTO {

    private Long Id;
    private String username;
    private String email;
    private List<Role> roles;
}
