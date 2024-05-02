package com.github.evgenylizogubov;

public class Plate {
    private final int capacity;
    private int food;
    
    public Plate(int capacity) {
        this.capacity = capacity;
    }
    
    public void fillMax() {
        food = capacity;
    }
    
    public void info() {
        System.out.printf("Plate: %d / %d\n", food, capacity);
    }
    
    public boolean decreaseFood(int amount) {
        if (amount > food) {
            return false;
        }
        
        food -= amount;
        return true;
    }
    
    public boolean increaseFood(int amount) {
        if (amount > capacity - food) {
            return false;
        }
        
        food += amount;
        return true;
    }
}
