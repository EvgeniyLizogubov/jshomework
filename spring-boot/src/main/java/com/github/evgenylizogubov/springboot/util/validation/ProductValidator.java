package com.github.evgenylizogubov.springboot.util.validation;

import com.github.evgenylizogubov.springboot.dto.ProductDto;
import com.github.evgenylizogubov.springboot.error.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        
        if (productDto.getPrice() < 1) {
            errors.add("Цена продукта не может быть меньше 1!");
        }
        
        if (productDto.getName() == null || productDto.getName().isBlank()) {
            errors.add("Название продукта не может быть пустым!");
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
