package com.example.productservice.clientservice;

import com.example.productservice.clientservice.dtos.FakeStoreProductdto;
import com.example.productservice.dtos.GenericFakeStoreProductdto;
import com.example.productservice.dtos.GenericProductdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class FakeStoreProductClient {

    private String producturl="https://fakestoreapi.com/products/";
    private String productidurl="https://fakestoreapi.com/products/{id}";
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public FakeStoreProductdto getProductbyId(Long id) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductdto> responseEntity=
                restTemplate.getForEntity(productidurl, FakeStoreProductdto.class,id);
        if(responseEntity==null)
            return null;
        FakeStoreProductdto fakeStoreProductdto=responseEntity.getBody();
        if(fakeStoreProductdto==null)
            return null;
        return fakeStoreProductdto;
    }

    public FakeStoreProductdto createProduct(GenericFakeStoreProductdto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductdto> responseEntity=
                restTemplate.postForEntity(producturl,product, FakeStoreProductdto.class);
        FakeStoreProductdto fakeStoreProductdto=responseEntity.getBody();

        return fakeStoreProductdto;
    }


    public List<FakeStoreProductdto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductdto>> responseEntity=restTemplate.exchange(producturl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FakeStoreProductdto>>() {});
        List<FakeStoreProductdto> fakeStoreProductdtos=responseEntity.getBody();
        return fakeStoreProductdtos;
    }


    public FakeStoreProductdto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductdto> responseEntity=restTemplate.exchange(productidurl, HttpMethod.DELETE,null,
                FakeStoreProductdto.class,id);
        FakeStoreProductdto fakeStoreProductdto=responseEntity.getBody();
        return fakeStoreProductdto;
    }

}