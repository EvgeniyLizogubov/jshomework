package com.github.evgenylizogubov;

public class Cat extends Animal {
    public Cat() {
        super(getRandomInRange(100, 300), 0, getRandomInRange(1, 3));
    }
}
