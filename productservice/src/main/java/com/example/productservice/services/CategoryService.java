package com.example.productservice.services;

import com.example.productservice.dtos.GenericCategorydto;
import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public List<GenericCategorydto> getAllCategories(){
        List<GenericCategorydto> genericCategorydtos= new ArrayList<>();
        List<Category> categories=categoryRepository.findAll();
        for (Category category:categories){
            GenericCategorydto genericCategorydto=convertCategoryToGenericCategory(category);
            genericCategorydtos.add(genericCategorydto);
        }
        return genericCategorydtos;
    }

    public GenericCategorydto convertCategoryToGenericCategory(Category category){
        GenericCategorydto genericCategorydto=new GenericCategorydto();
        genericCategorydto.setName(category.getName());
        return genericCategorydto;
    }
}
