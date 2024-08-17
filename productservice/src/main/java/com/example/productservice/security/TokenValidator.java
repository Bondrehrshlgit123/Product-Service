package com.example.productservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    private String url="http://localhost:8890/auth/validate";
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public Optional<SessionValidateResponseDto> validateToken(String token, Long id){

        ValidateTokenRequestDto validateTokenRequestDto=new ValidateTokenRequestDto();
        validateTokenRequestDto.setUser_id(id);
        validateTokenRequestDto.setToken(token);
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<SessionValidateResponseDto> sessionValidateResponseDtoResponseEntity
                =restTemplate.postForEntity(url,validateTokenRequestDto, SessionValidateResponseDto.class);
        if(sessionValidateResponseDtoResponseEntity==null)
            return null;
        SessionValidateResponseDto sessionValidateResponseDto= sessionValidateResponseDtoResponseEntity.getBody();
        Optional<SessionValidateResponseDto> sessionValidateResponseDtoOptional=
                Optional.of(sessionValidateResponseDto);
        return sessionValidateResponseDtoOptional;
    }
}
