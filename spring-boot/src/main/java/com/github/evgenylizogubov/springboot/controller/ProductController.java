package com.github.evgenylizogubov.springboot.controller;

import com.github.evgenylizogubov.springboot.dto.ProductDto;
import com.github.evgenylizogubov.springboot.error.ResourceNotFoundException;
import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.service.ProductService;
import com.github.evgenylizogubov.springboot.util.mapper.ProductMapper;
import com.github.evgenylizogubov.springboot.util.validation.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductValidator productValidator;
    
    @GetMapping
    public List<ProductDto> getAll(@RequestParam(defaultValue = "1") int pageNumber,
                                   @RequestParam(required = false) Integer minPrice,
                                   @RequestParam(required = false) Integer maxPrice) {
        log.info("getAll with page={}, minPrice={}, maxPrice={}", pageNumber, minPrice, maxPrice);
        return productService.getAll(pageNumber, minPrice, maxPrice).stream()
                .map(productMapper::toDto).toList();
    }
    
    @GetMapping("/count")
    public int count(@RequestParam(required = false) Integer minPrice,
                     @RequestParam(required = false) Integer maxPrice) {
        log.info("count with minPrice={}, maxPrice={}", minPrice, maxPrice);
        return productService.getCount(minPrice, maxPrice);
    }
    
    @GetMapping("/{id}")
    public ProductDto get(@PathVariable int id) {
        log.info("get with id={}", id);
        return productMapper.toDto(productService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id=" + id + " not found")));
    }
    
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("delete by id={}", id);
        productService.removeById(id);
    }
    
    @PostMapping
    public void save(@RequestBody ProductDto productDto) {
        log.info("save {}", productDto);
        productValidator.validate(productDto);
        Product product = productMapper.fromDto(productDto);
        product.setId(null);
        productService.create(productMapper.fromDto(productDto));
    }
}
