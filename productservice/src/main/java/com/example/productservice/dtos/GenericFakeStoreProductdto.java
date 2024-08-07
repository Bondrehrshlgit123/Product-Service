package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericFakeStoreProductdto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String image;
    private Double price;
}
