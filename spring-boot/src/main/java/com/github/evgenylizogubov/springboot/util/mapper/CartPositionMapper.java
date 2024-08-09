package com.github.evgenylizogubov.springboot.util.mapper;

import com.github.evgenylizogubov.springboot.dto.CartPositionDto;
import com.github.evgenylizogubov.springboot.model.CartPosition;
import org.springframework.stereotype.Component;

@Component
public class CartPositionMapper {
    public CartPosition toEntity(CartPositionDto cartPositionDto) {
        return new CartPosition(cartPositionDto.getId(), cartPositionDto.getProduct(), cartPositionDto.getAmount());
    }
    
    public CartPositionDto toDto(CartPosition cartPosition) {
        return new CartPositionDto(cartPosition.getId(), cartPosition.getProduct(), cartPosition.getAmount());
    }
}
