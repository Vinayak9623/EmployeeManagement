package com.vsd.Dto.request;

import com.vsd.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
}
