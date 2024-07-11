package com.github.evgenylizogubov.springboot.controller;

import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductController2 {
    private ProductService productService;
    
    @Autowired
    public ProductController2(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }
    
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        productService.removeById(id);
    }
}
