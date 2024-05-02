package com.github.evgenylizogubov;

import java.util.Arrays;

public class HomeWorkApp {
    public static void main(String[] args) {
        Plate plate = new Plate(100);
        plate.fillMax();
        
        Cat[] cats = new Cat[4];
        cats[0] = new Cat("Barsik");
        cats[1] = new Cat("Murka");
        cats[2] = new Cat("Vasily");
        cats[3] = new Cat("Marseille");
        
        Arrays.stream(cats).forEach(cat -> cat.eat(plate));
        Arrays.stream(cats).forEach(System.out::println);

        plate.info();
        System.out.println(plate.increaseFood(50));
        plate.info();
    }
}
