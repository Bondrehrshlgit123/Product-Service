package com.example.productservice.clientservice;

import com.example.productservice.clientservice.dtos.FakeStoreProductdto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductClientTest {

    @Autowired
    private FakeStoreProductClient fakeStoreProductClient;
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;
    @MockBean
    private RestTemplate restTemplate;
    @Test
    public void testGetProductbyId(){
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        ResponseEntity<FakeStoreProductdto> response=new ResponseEntity<>(null, HttpStatusCode.valueOf(200));

        when(restTemplate.getForEntity(anyString(),eq(FakeStoreProductdto.class),eq(Long.class)))
                .thenReturn(response);

        FakeStoreProductdto fakeStoreProductdto=fakeStoreProductClient.getProductbyId(1L);
        Assertions.assertNull(fakeStoreProductdto);

    }
}
