package com.github.evgenylizogubov;

public class Dog extends Animal {
    
    public Dog() {
        super(getRandomInRange(300, 600), getRandomInRange(5, 10), getRandomInRange(0, 2));
    }
}
