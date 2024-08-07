package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductdto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    GenericProductdto getProductById(Long id);

    GenericProductdto createProduct(GenericProductdto genericProductdto);
    List<GenericProductdto> getAllProducts();
    GenericProductdto deleteProduct(Long id);
    GenericProductdto updateProduct(Long id,GenericProductdto genericProductdto);

    public List<GenericProductdto> getProductbyCategory(String name);
}
