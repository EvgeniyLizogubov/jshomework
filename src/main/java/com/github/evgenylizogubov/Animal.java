package com.github.evgenylizogubov;

import java.util.Random;

public abstract class Animal {
    int maxRunLength;
    int maxSwimDistance;
    int maxJumpHeight;
    
    public Animal(int maxRunLength, int maxSwimDistance, int maxJumpHeight) {
        this.maxRunLength = maxRunLength;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
    }
    
    abstract void run(int distance);
    
    abstract void swim(int distance);
    
    abstract void jump(int height);
    
    static int getRandomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
