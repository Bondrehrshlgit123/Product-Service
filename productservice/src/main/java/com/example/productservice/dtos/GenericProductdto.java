package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductdto {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private String image;
    private Price price;
}
