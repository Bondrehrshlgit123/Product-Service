package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.services.CategoryService;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productServiceMock;
    @MockBean
    private CategoryService categoryServiceMock;
//    @Autowired
//    public ProductControllerTest(){
//        productServiceMock= Mockito.mock(ProductService.class);
//        categoryServiceMock=Mockito.mock(CategoryService.class);
//        productController=new ProductController(productServiceMock,categoryServiceMock);
//    }
    @Test
    public void testGetProductbyIdtoReturnEmptyObjectWhenAnyLongvalueisPassed(){
       when(productServiceMock.getProductById(any(Long.class))).thenReturn(null);

        GenericProductdto genericProductdto=productController.getProductbyId(1L);
        Assertions.assertNotNull(genericProductdto);
    }

    @Test
    public void testGetProductByIdReturnsCorrectResponse(){
        GenericProductdto tobereturned=new GenericProductdto();
        tobereturned.setId(2L);
        tobereturned.setName("Iphone");
        when(productServiceMock.getProductById(any(Long.class)))
                .thenReturn(tobereturned);
        GenericProductdto response=productController.getProductbyId(3L);
        Assertions.assertEquals(2L,response.getId());
        Assertions.assertEquals("Iphone",response.getName());
    }
}
