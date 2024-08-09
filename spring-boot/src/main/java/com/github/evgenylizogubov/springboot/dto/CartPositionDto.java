package com.github.evgenylizogubov.springboot.dto;

import com.github.evgenylizogubov.springboot.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CartPositionDto {
    private Integer id;
    private Product product;
    private Integer amount;
}
