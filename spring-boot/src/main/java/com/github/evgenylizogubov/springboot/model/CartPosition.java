package com.github.evgenylizogubov.springboot.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "amount"})
@ToString
public class CartPosition {
    private Integer id;
    private Product product;
    private Integer amount;
}
