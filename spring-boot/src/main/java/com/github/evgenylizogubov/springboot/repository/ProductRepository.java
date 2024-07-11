package com.github.evgenylizogubov.springboot.repository;

import com.github.evgenylizogubov.springboot.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;
    
    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product("Молоко", 450),
                new Product("Хлеб", 350),
                new Product("Колбаса", 2600),
                new Product("Сыр", 1700),
                new Product("Пельмешки", 2350)
        ));
    }
    
    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }
    
    public Product getById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Product with id=%d not found", id)));
    }
    
    public void removeById(int id) {
        products.removeIf(product -> product.getId() == id);
    }
    
    public void create(Product product) {
        products.add(product);
    }
}
