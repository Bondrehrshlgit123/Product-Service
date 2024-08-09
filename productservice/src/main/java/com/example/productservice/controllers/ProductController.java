package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericCategorydto;
import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.security.JwtData;
import com.example.productservice.security.TokenValidator;
import com.example.productservice.services.CategoryService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/products")
public class ProductController {
ProductService productService;
CategoryService categoryService;
TokenValidator tokenValidator;

@Autowired
    public ProductController(@org.springframework.beans.factory.annotation.Qualifier("selfstoreproductservice") ProductService productService,
                             CategoryService categoryService, TokenValidator tokenValidator){
        this.productService=productService;
        this.categoryService=categoryService;
        this.tokenValidator=tokenValidator;
    }
    @GetMapping("/{id}")
    public GenericProductdto getProductbyId(@PathVariable("id") Long id,
                                            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth_token){
    Optional<JwtData> jwtDataOptional=tokenValidator.validateToken(auth_token);


    GenericProductdto genericProductdto=productService.getProductById(id);

        if(genericProductdto==null){
            return new GenericProductdto();
        }
    return genericProductdto;
}
    @GetMapping("/category/{name}")
    public List<GenericProductdto> getProductbyCategory(@PathVariable("name") String name){

        return productService.getProductbyCategory(name);
    }
    @PutMapping("/{id}")
    public GenericProductdto updateProduct(@PathVariable("id") Long id,
                                           @RequestBody GenericProductdto genericProductdto){
    return productService.updateProduct(id, genericProductdto);
    }
    @PostMapping
    public GenericProductdto createProduct(@RequestBody GenericProductdto genericProductdto){
     return productService.createProduct(genericProductdto);
    }

    @GetMapping
    public List<GenericProductdto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<GenericCategorydto> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductdto> deleteProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatusCode.valueOf(200));
    //return productService.deleteProduct(id);
    }

}
