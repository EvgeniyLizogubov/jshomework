package com.github.evgenylizogubov;

public class Cat extends Animal {
    public Cat() {
        super(getRandomInRange(100, 300), 0, getRandomInRange(1, 3));
    }
    
    @Override
    void run(int distance) {
        System.out.println("cat runs: " + (distance <= maxRunLength ? "true" : "false"));
    }
    
    @Override
    void swim(int distance) {
        System.out.println("cat swims: false");
    }
    
    @Override
    void jump(int height) {
        System.out.println("cat jumps: " + (height <= maxJumpHeight ? "true" : "false"));
    }
}
