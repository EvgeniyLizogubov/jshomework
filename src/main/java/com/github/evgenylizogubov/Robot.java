package com.github.evgenylizogubov;

public class Robot implements Actionable{
    
    private final String name;
    private final int maxRunningDistance;
    private final int maxJumpingHeight;
    
    public Robot(String name, int maxRunningDistance, int maxJumpingHeight) {
        this.maxRunningDistance = maxRunningDistance;
        this.maxJumpingHeight = maxJumpingHeight;
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println(name + " is running...");
    }
    
    @Override
    public void jump() {
        System.out.println(name + " is jumping...");
    }
    
    @Override
    public int getMaxRunningDistance() {
        return maxRunningDistance;
    }
    
    @Override
    public int getMaxJumpingHeight() {
        return maxJumpingHeight;
    }
    
    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                ", maxRunningDistance=" + maxRunningDistance +
                ", maxJumpingHeight=" + maxJumpingHeight +
                '}';
    }
}
