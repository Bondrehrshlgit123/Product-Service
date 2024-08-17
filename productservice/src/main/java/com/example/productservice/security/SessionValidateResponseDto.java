package com.example.productservice.security;

import com.example.productservice.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionValidateResponseDto {
    private String email;
    private String role;
    private SessionStatus sessionStatus;
}
