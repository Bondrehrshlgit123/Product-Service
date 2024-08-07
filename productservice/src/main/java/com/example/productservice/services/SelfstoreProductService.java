package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service("selfstoreproductservice")
public class SelfstoreProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    @Autowired
    public SelfstoreProductService(ProductRepository productRepository,
                                   CategoryRepository categoryRepository,
                                   PriceRepository priceRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.priceRepository=priceRepository;
    }
    @Override
    public GenericProductdto getProductById(Long id) {
        System.out.println("inside self store");
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional==null) return null;
        Product product=productOptional.get();

            GenericProductdto genericProductdto=convertProductToGenericProduct(product);

        return genericProductdto;
    }
@Transactional
    @Override
    public GenericProductdto createProduct(GenericProductdto genericProductdto) {
        Product product=convertGenericProductToProduct(genericProductdto);

        if(product.getCategory().getId()!=null){
           Category category=categoryRepository.findById(product.getCategory().getId()).orElseThrow(()->new IllegalArgumentException("Invalid category"));
            product.setCategory(category);
        }else{
            categoryRepository.save(product.getCategory());
        }

        if(product.getPrice().getId()!=null){
          Price existingprice=priceRepository.findById(product.getPrice().getId()).orElseThrow(()->new IllegalArgumentException("Invalid Price"));
            product.setPrice(existingprice);
        }else{
            priceRepository.save(product.getPrice());
        }

        Product savedproduct=productRepository.save(product);
        GenericProductdto genericProductdto1=convertProductToGenericProduct(savedproduct);
        return genericProductdto1;
    }

    @Override
    public List<GenericProductdto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<GenericProductdto> genericProductdtos=new ArrayList<>();
        for(Product product:products){
            GenericProductdto genericProductdto=convertProductToGenericProduct(product);
            genericProductdtos.add(genericProductdto);
        }
        return genericProductdtos;
    }

    @Override
    public GenericProductdto deleteProduct(Long id) {
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=productOptional.get();
        Category category=product.getCategory();
        product.setCategory(null);
        Price price=product.getPrice();
        priceRepository.deleteById(price.getId());
        productRepository.deleteById(id);
        GenericProductdto genericProductdto=convertProductToGenericProduct(product);

        return genericProductdto;
    }
@Transactional
    @Override
    public GenericProductdto updateProduct(Long id, GenericProductdto genericProductdto) {
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=productOptional.get();

        product.setName(genericProductdto.getName());
        product.setDescription(genericProductdto.getDescription());


        if(genericProductdto.getCategory().getId()!=null){
           Category category=categoryRepository.findById(genericProductdto.getCategory().getId()).orElseThrow(()->new IllegalArgumentException("Invalid category"));
            product.setCategory(category);
        }else{
            categoryRepository.save(product.getCategory());
        }

        product.setImage(genericProductdto.getImage());

        if(genericProductdto.getPrice().getId()!=null){
           Price price=priceRepository.findById(genericProductdto.getPrice().getId()).orElseThrow(()->new IllegalArgumentException("Invalid Price"));
            product.setPrice(price);
        }else{
            priceRepository.save(product.getPrice());
        }

        Product savedproduct=productRepository.save(product);
        GenericProductdto genericProductdto1=convertProductToGenericProduct(savedproduct);
        return genericProductdto1;
    }
    @Override
    public List<GenericProductdto> getProductbyCategory(String name){
        List<GenericProductdto> genericProductdtos=new ArrayList<>();
        List<Product> products=productRepository.findAllByCategory_name(name);
        for(Product product:products){
            GenericProductdto genericProductdto=convertProductToGenericProduct(product);
            genericProductdtos.add(genericProductdto);
        }
        return genericProductdtos;
    }

    public GenericProductdto convertProductToGenericProduct(Product product){
        if(product==null){
            return null;
        }
        GenericProductdto genericProductdto=new GenericProductdto();
        genericProductdto.setId(product.getId());
        genericProductdto.setName(product.getName());
        genericProductdto.setCategory(product.getCategory());
        genericProductdto.setPrice(product.getPrice());
        genericProductdto.setImage(product.getImage());
        genericProductdto.setDescription(product.getDescription());
        return genericProductdto;
    }
    public Product convertGenericProductToProduct(GenericProductdto product){
        Product genericProductdto=new Product();
        genericProductdto.setId(product.getId());
        genericProductdto.setName(product.getName());
        genericProductdto.setCategory(product.getCategory());
        genericProductdto.setPrice(product.getPrice());
        genericProductdto.setImage(product.getImage());
        genericProductdto.setDescription(product.getDescription());
        return genericProductdto;
    }
}
