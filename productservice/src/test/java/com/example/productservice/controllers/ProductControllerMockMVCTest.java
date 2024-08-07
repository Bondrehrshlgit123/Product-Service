package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMockMVCTest {
@Autowired
    private ProductController productController;
@Autowired
    private ObjectMapper objectMapper;
@MockBean
    private ProductService productService;
@Autowired
    private MockMvc mockMvc;
@Test
    public void testGetProductbyIdAPI() throws Exception {
    GenericProductdto genericProductdto=new GenericProductdto();
    genericProductdto.setId(1L);
    genericProductdto.setName("Iphone");
    when(productService.getProductById(1L))
            .thenReturn(genericProductdto);
    ResultActions resultActions=mockMvc.perform(
            get("/products/1")).andExpect(status().is(200));

    String responseString=resultActions.andReturn().getResponse().getContentAsString();
    GenericProductdto responsedto=objectMapper.readValue(responseString, GenericProductdto.class);
    Assertions.assertNotNull(responsedto);
    Assertions.assertEquals(1L,responsedto.getId());
    Assertions.assertEquals("Iphone",responsedto.getName());
}
}
