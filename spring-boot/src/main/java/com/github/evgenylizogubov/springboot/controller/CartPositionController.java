package com.github.evgenylizogubov.springboot.controller;

import com.github.evgenylizogubov.springboot.dto.CartPositionDto;
import com.github.evgenylizogubov.springboot.model.CartPosition;
import com.github.evgenylizogubov.springboot.service.CartPositionService;
import com.github.evgenylizogubov.springboot.util.mapper.CartPositionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/cart-positions")
@RequiredArgsConstructor
@Slf4j
public class CartPositionController {
    private final CartPositionService cartPositionService;
    private final CartPositionMapper cartPositionMapper;
    
    @PostMapping
    public void add(@RequestBody CartPositionDto cartPositionDto) {
        log.info("add {}", cartPositionDto);
        CartPosition cartPosition = cartPositionMapper.toEntity(cartPositionDto);
        cartPositionService.add(cartPosition);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("delete with id={}", id);
        cartPositionService.delete(id);
    }
    
    @GetMapping
    public List<CartPositionDto> getAll() {
        log.info("getAll");
        return cartPositionService.getAll().stream()
                .map(cartPositionMapper::toDto).toList();
    }
    
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestParam int amount) {
        log.info("update with id={}, set amount={}", id, amount);
        cartPositionService.update(id, amount);
    }
}
