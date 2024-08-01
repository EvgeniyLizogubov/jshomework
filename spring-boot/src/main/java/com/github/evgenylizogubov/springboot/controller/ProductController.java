package com.github.evgenylizogubov.springboot.controller;

import com.github.evgenylizogubov.springboot.error.ResourceNotFoundException;
import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public List<Product> getAll(@RequestParam(defaultValue = "1") int pageNumber,
                                @RequestParam(required = false) Integer minPrice,
                                @RequestParam(required = false) Integer maxPrice) {
        return productService.getAll(pageNumber, minPrice, maxPrice);
    }
    
    @GetMapping("/count")
    public int count(@RequestParam(required = false) Integer minPrice,
                     @RequestParam(required = false) Integer maxPrice) {
        return productService.getCount(minPrice, maxPrice);
    }
    
    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return productService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id=" + id + " not found"));
    }
    
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        productService.removeById(id);
    }
    
    @PostMapping
    public void save(@RequestBody Product product) {
        productService.create(product);
    }
}
