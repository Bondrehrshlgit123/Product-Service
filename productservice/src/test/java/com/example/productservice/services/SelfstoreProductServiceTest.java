package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfstoreProductServiceTest {
@Autowired
    private SelfstoreProductService selfstoreProductService;
@MockBean
    private ProductRepository productRepository;
@MockBean
    private CategoryRepository categoryRepository;
@MockBean
    private PriceRepository priceRepository;
@Test
    public void testGetProductByIdwhenIdIsNullReturnsNull(){
    when(productRepository.findById(null)).thenReturn(null);

    GenericProductdto genericProductdto=selfstoreProductService.getProductById(null);
    Assertions.assertNull(genericProductdto);
}
@Test
    public void testGetProductByIdwhenReturnsNonNullObject(){
    Product genericProductdto=new Product();
    genericProductdto.setId(2L);
    genericProductdto.setName("Iphone");
    Optional<Product> optional1=Optional.of(genericProductdto);
    when(productRepository.findById(anyLong())).thenReturn(optional1);

    GenericProductdto genericProductdto1=selfstoreProductService.getProductById(3L);
    Assertions.assertNotNull(genericProductdto1);
    Assertions.assertEquals(2L,genericProductdto1.getId());
}
}
