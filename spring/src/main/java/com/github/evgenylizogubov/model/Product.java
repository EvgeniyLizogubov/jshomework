package com.github.evgenylizogubov.model;

public class Product {
    private static int counter = 1;
    
    private int id;
    private String name;
    private int price;
    
    public Product(String name, int price) {
        this.id = counter++;
        this.name = name;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price;
    }
}
