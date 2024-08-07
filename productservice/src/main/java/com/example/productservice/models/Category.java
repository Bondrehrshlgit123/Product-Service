package com.example.productservice.models;
import com.example.productservice.models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.naming.MalformedLinkException;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel{
    private String name;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
