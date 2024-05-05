package com.github.evgenylizogubov;

public class Treadmill implements Obstacle {
    
    private final int length;
    
    public Treadmill(int length) {
        this.length = length;
    }
    
    @Override
    public boolean overcomingObstacle(Actionable participant) {
        participant.run();
        
        if (participant.getMaxRunningDistance() >= length) {
            System.out.println("and overcomes the obstacle!");
            return true;
        }
        
        System.out.println("and does not overcome the obstacle");
        return false;
    }
}
