package com.example.productservice.security;

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

    public Optional<JwtData> validateToken(String token){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<JwtData> jwtDataResponseEntity=restTemplate.getForEntity(url, JwtData.class,token);
        if(jwtDataResponseEntity==null)
            return null;
        JwtData jwtData= jwtDataResponseEntity.getBody();
        Optional<JwtData> jwtDataOptional=Optional.of(jwtData);
        return jwtDataOptional;
    }
}
