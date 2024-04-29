package com.github.evgenylizogubov;

public class Dog extends Animal {
    
    public Dog() {
        super(getRandomInRange(300, 600), getRandomInRange(5, 10), getRandomInRange(0, 2));
    }
    
    @Override
    void run(int distance) {
        System.out.println("dog runs: " + (distance <= maxRunLength ? "true" : "false"));
    }
    
    @Override
    void swim(int distance) {
        System.out.println("dog swims: " + (distance <= maxSwimDistance ? "true" : "false"));
    }
    
    @Override
    void jump(int height) {
        System.out.println("dog jumps: " + (height <= maxJumpHeight ? "true" : "false"));
    }
}
