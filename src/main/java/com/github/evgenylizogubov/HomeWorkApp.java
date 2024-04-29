package com.github.evgenylizogubov;

public class HomeWorkApp {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.run(200);
        cat.swim(150);
        cat.jump(2);
        
        Dog dog = new Dog();
        dog.run(450);
        dog.swim(7);
        dog.jump(1);
    }
}
