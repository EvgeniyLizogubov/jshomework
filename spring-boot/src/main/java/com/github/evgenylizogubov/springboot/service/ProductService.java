package com.github.evgenylizogubov.springboot.service;

import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.repository.ProductRepository;
import com.github.evgenylizogubov.springboot.util.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    
    public List<Product> getAll(int pageNumber, Integer minPrice, Integer maxPrice) {
        Specification<Product> specification = new ProductSpecification(minPrice, maxPrice);
        PageRequest request = PageRequest.of(pageNumber - 1, 2);
        return productRepository.findAll(specification, request).toList();
    }
    
    public Optional<Product> getById(int id) {
        return productRepository.findById(id);
    }
    
    public void removeById(int id) {
        productRepository.deleteById(id);
    }
    
    public void create(Product product) {
        productRepository.save(product);
    }
    
    public int getCount(Integer minPrice, Integer maxPrice) {
        Specification<Product> specification = new ProductSpecification(minPrice, maxPrice);
        return (int) productRepository.count(specification);
    }
}
