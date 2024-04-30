package com.github.evgenylizogubov;

import java.util.Random;

public class Animal {
    int maxRunLength;
    int maxSwimDistance;
    int maxJumpHeight;
    
    public Animal(int maxRunLength, int maxSwimDistance, int maxJumpHeight) {
        this.maxRunLength = maxRunLength;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
    }
    
    void run(int distance) {
        System.out.println("runs: " + (distance <= maxRunLength ? "true" : "false"));
    }
    
    void swim(int distance) {
        System.out.println("swims: " + (distance <= maxSwimDistance ? "true" : "false"));
    }
    
    void jump(int height) {
        System.out.println("jumps: " + (height <= maxJumpHeight ? "true" : "false"));
    }
    
    static int getRandomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
