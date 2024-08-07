package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.websocket.Decoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Transactional
@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
	private PriceRepository priceRepository;
	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	public ProductServiceApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository){
		this.categoryRepository=categoryRepository;
		this.productRepository=productRepository;
		this.priceRepository=priceRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category category=new Category();
//		category.setName("electronics");
//		Category savedCategory=categoryRepository.getReferenceById(2L);
//		Price price=new Price("Rupees",2000);
//
//		Product product=new Product();
//		product.setName("Motorola2");
//		product.setDescription("Moto galaxyf41");
//		product.setImage("xyz");
//		product.setPrice(price);
//		product.setCategory(savedCategory);
//		productRepository.save(product);
		//priceRepository.deleteById(UUID.fromString());
//		Category category= categoryRepository.getReferenceById(2L);
//		List<Product> products=category.getProducts();
//		for (Product product:products){
//			System.out.println(product);
//		}
//		Product product=productRepository.findByNameAndPrice_currency("Galaxy2","Rupees");
//		System.out.println(product);
//
//		Product product1=productRepository.findingByName("Motorola2");
//		System.out.println(product1);

	}
}
