package com.github.evgenylizogubov.springboot.service;

import com.github.evgenylizogubov.springboot.dao.CartPositionDaoImpl;
import com.github.evgenylizogubov.springboot.model.CartPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartPositionService {
    private final CartPositionDaoImpl cartPositionDao;
    
    public void add(CartPosition cartPosition) {
        cartPositionDao.add(cartPosition);
    }
    
    public void delete(int id) {
        cartPositionDao.remove(id);
    }
    
    public List<CartPosition> getAll() {
        return cartPositionDao.getAll();
    }
    
    public void update(int id, int amount) {
        if (amount > 0) {
            cartPositionDao.changeAmount(id, amount);
        }
    }
}
