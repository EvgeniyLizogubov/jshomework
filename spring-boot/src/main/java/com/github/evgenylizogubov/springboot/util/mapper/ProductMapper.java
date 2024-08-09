package com.github.evgenylizogubov.springboot.util.mapper;

import com.github.evgenylizogubov.springboot.dto.ProductDto;
import com.github.evgenylizogubov.springboot.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
    
    public Product fromDto(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
    }
}
