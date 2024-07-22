package com.github.evgenylizogubov.springboot.service;

import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getAll() {
        return productRepository.getAll();
    }
    
    public Product getById(int id) {
        return productRepository.getById(id);
    }
    
    public void removeById(int id) {
        productRepository.removeById(id);
    }
    
    public void create(Product product) {
        productRepository.create(product);
    }
}
