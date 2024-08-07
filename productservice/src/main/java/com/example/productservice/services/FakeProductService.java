package com.example.productservice.services;

import com.example.productservice.dtos.GenericFakeStoreProductdto;
import com.example.productservice.dtos.GenericProductdto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FakeProductService {
    GenericFakeStoreProductdto getProductById(Long id);

    GenericFakeStoreProductdto createProduct(GenericFakeStoreProductdto genericProductdto);
    List<GenericFakeStoreProductdto> getAllProducts();
    GenericFakeStoreProductdto deleteProduct(Long id);
}
