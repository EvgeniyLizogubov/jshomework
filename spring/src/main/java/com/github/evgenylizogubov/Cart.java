package com.github.evgenylizogubov;

import com.github.evgenylizogubov.model.Product;
import com.github.evgenylizogubov.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private ProductRepository repository;
    
    private List<Product> products;
    
    @Autowired
    public Cart(ProductRepository repository) {
        this.products = new ArrayList<>();
        this.repository = repository;
    }
    
    public void addProduct(int id) {
        products.add(repository.getById(id));
    }
    
    public void removeProduct(int id) {
        products.remove(repository.getById(id));
    }
    
    public List<Product> getAll() {
        return products;
    }
}
