package com.github.evgenylizogubov.repository;

import com.github.evgenylizogubov.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;
    
    @PostConstruct
    private void init() {
        products = new ArrayList<>(List.of(
                new Product("Молоко", 800),
                new Product("Хлеб", 350),
                new Product("Каша", 900),
                new Product("Колбаса", 11000),
                new Product("Пельмешки", 2600)
        ));
    }
    
    public List<Product> getAll() {
        return products;
    }
    
    public Product getById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }
}
