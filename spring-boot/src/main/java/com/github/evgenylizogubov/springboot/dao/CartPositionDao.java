package com.github.evgenylizogubov.springboot.dao;

import com.github.evgenylizogubov.springboot.model.CartPosition;

import java.util.List;

public interface CartPositionDao {
    void add(CartPosition cartPosition);
    
    void remove(int id);
    
    List<CartPosition> getAll();
    
    void changeAmount(int id, int amount);
}
