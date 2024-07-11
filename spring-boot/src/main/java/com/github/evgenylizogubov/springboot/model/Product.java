package com.github.evgenylizogubov.springboot.model;

public class Product {
    private static int count = 1;
    
    private int id;
    private String name;
    private int price;
    
    public Product(String name, int price) {
        this.id = count++;
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
}
