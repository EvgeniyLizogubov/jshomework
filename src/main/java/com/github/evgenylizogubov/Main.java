package com.github.evgenylizogubov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        swapElements(array, 1, 2);
        System.out.println("Array = " + Arrays.toString(array));
        
        List<Integer> list = toArrayList(array);
        System.out.println("List = " + list);
        
        Box<Apple> box1 = new Box<>();
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        
        Box<Orange> box2 = new Box<>();
        box2.add(new Orange());
        box2.add(new Orange());
        box2.add(new Orange());
        box2.add(new Orange());
        
        System.out.println("The weight of the box1 with apples: " + box1.getWeight());
        System.out.println("The weight of the box2 with oranges: " + box2.getWeight() + "\n");
        
        System.out.println("Box1 equals Box2? - " + box1.compare(box2) + "\n");
        
        Box<Apple> box3 = new Box<>();
        box3.add(new Apple());
        box3.add(new Apple());
        
        System.out.println("The weight of the box3 with apples " + box3.getWeight());
        
        box1.transferAllFruitsToAnotherBox(box3);
        
        
        System.out.println("The weight of the box1 with apples " + box1.getWeight());
        System.out.println("The weight of the box3 with apples " + box3.getWeight());
    }
    
    private static void swapElements(Object[] array, int index1, int index2) {
        Object buff = array[index1];
        array[index1] = array[index2];
        array[index2] = buff;
    }
    
    private static <T> List<T> toArrayList(T... a) {
        return List.of(a);
    }
}

abstract class Fruit {
    public abstract float getWeight();
}

class Apple extends Fruit {
    @Override
    public float getWeight() {
        return 1f;
    }
}

class Orange extends Fruit {
    @Override
    public float getWeight() {
        return 1.5f;
    }
}

class Box<T extends Fruit> {
    List<T> fruits = new ArrayList<>();
    
    public void add(T fruit) {
        fruits.add(fruit);
    }
    
    public float getWeight() {
        return fruits.isEmpty() ? 0 : fruits.size() * fruits.getFirst().getWeight();
    }
    
    public boolean compare(Box anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001f;
    }
    
    public void transferAllFruitsToAnotherBox(Box<T> anotherBox) {
        fruits.forEach(anotherBox::add);
        fruits.clear();
    }
}

