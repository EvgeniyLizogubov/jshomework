package com.github.evgenylizogubov;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean hungry;
    
    public Cat(String name) {
        this.name = name;
        this.appetite = getRandomNumber(40, 60);
        this.hungry = true;
    }
    
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public void eat(Plate plate) {
        if (plate.decreaseFood(appetite)) {
            hungry = false;
        }
    }
    
    public int getAppetite() {
        return appetite;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isHungry() {
        return hungry;
    }
    
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", hungry=" + hungry +
                '}';
    }
}
