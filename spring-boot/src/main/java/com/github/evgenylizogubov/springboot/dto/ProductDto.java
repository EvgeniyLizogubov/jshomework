package com.github.evgenylizogubov.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private int price;
}
