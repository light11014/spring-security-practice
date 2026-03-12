package dev.security.dto;

import dev.security.model.Role;
import lombok.Getter;

@Getter
public class SignupRequest {
    private String username;
    private String password;
    private Role role;
}
