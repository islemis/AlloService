package com.example.alloservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    @NotBlank private String username;
    @Email @NotBlank private String email;
    @Size(min = 8) private String password;
    private String role;
    private String fullname;
    private String phone;
    private String address;
}