package com.example.productservice.services;

import com.example.productservice.clientservice.FakeStoreProductClient;
import com.example.productservice.clientservice.dtos.FakeStoreProductdto;
import com.example.productservice.dtos.GenericFakeStoreProductdto;
import com.example.productservice.dtos.GenericProductdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductdto")
public class FakestoreProductService implements FakeProductService{

    FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakestoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient=fakeStoreProductClient;
    }
    public GenericFakeStoreProductdto convertFakeStoreProductToGenericProduct(FakeStoreProductdto fakeStoreProductdto){
        GenericFakeStoreProductdto genericProductdto=new GenericFakeStoreProductdto();
        genericProductdto.setId(fakeStoreProductdto.getId());
        genericProductdto.setName(fakeStoreProductdto.getTitle());
        genericProductdto.setCategory(fakeStoreProductdto.getCategory());
        genericProductdto.setPrice(fakeStoreProductdto.getPrice());
        genericProductdto.setImage(fakeStoreProductdto.getImage());
        genericProductdto.setDescription(fakeStoreProductdto.getDescription());
        return genericProductdto;
    }
    @Override
    public GenericFakeStoreProductdto getProductById(Long id) {

        FakeStoreProductdto fakeStoreProductdto=fakeStoreProductClient.getProductbyId(id);

        GenericFakeStoreProductdto genericProductdto= convertFakeStoreProductToGenericProduct(fakeStoreProductdto);

        System.out.println("inside fake store");
        return genericProductdto;
    }

    @Override
    public GenericFakeStoreProductdto createProduct(GenericFakeStoreProductdto product) {

        FakeStoreProductdto fakeStoreProductdto= fakeStoreProductClient.createProduct(product);

        return convertFakeStoreProductToGenericProduct(fakeStoreProductdto);
    }

    @Override
    public List<GenericFakeStoreProductdto> getAllProducts() {

        List<FakeStoreProductdto> fakeStoreProductdtos=fakeStoreProductClient.getAllProducts();
        List<GenericFakeStoreProductdto> genericProductdtos=new ArrayList<>();
        for (FakeStoreProductdto fakeStoreProductdto:fakeStoreProductdtos){
            GenericFakeStoreProductdto genericProductdto= convertFakeStoreProductToGenericProduct(fakeStoreProductdto);
            genericProductdtos.add(genericProductdto);
        }
        return genericProductdtos;
    }

    @Override
    public GenericFakeStoreProductdto deleteProduct(Long id) {

        FakeStoreProductdto fakeStoreProductdto= fakeStoreProductClient.deleteProduct(id);
        GenericFakeStoreProductdto genericProductdto= convertFakeStoreProductToGenericProduct(fakeStoreProductdto);
        return genericProductdto;
    }

}
