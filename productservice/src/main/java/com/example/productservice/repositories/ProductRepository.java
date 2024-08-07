package com.example.productservice.repositories;

import com.example.productservice.dtos.GenericProductdto;
import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Product findByNameAndPrice_currency(String name,String currency);
//    @Query(value = "select * from Product where name=:name",nativeQuery = true)
//    Product findingByName(String name);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    <S extends Product> S save(S entity);

    List<Product> findAllByCategory_name(String name);

    @Override
    void deleteById(Long id);

}
