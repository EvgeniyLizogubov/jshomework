package com.github.evgenylizogubov.springboot.dao;

import com.github.evgenylizogubov.springboot.model.CartPosition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartPositionDaoImpl implements CartPositionDao {
    private final List<CartPosition> cartPositions = new ArrayList<>();
    private int index = 1;
    
    @Override
    public void add(CartPosition cartPosition) {
        if (cartPositions.contains(cartPosition)) {
            CartPosition saved = cartPositions.get(cartPositions.indexOf(cartPosition));
            saved.setAmount(saved.getAmount() + 1);
        } else {
            cartPosition.setId(index++);
            cartPositions.add(cartPosition);
        }
    }
    
    @Override
    public void remove(int id) {
        CartPosition cartPosition = cartPositions.stream()
                .filter(cp -> cp.getId() == id).findFirst().get();
        cartPositions.remove(cartPosition);
    }
    
    @Override
    public List<CartPosition> getAll() {
        return cartPositions;
    }
    
    @Override
    public void changeAmount(int id, int amount) {
        CartPosition cartPosition = cartPositions.stream()
                .filter(cp -> cp.getId() == id).findFirst().get();
        cartPosition.setAmount(amount);
    }
}
