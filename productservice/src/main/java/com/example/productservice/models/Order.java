package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseModel{

    @ManyToMany
    private List<Product> products;
}
