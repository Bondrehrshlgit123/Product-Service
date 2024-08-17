package com.example.productservice.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long user_id;
    private String token;
}
