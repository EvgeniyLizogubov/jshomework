package com.github.evgenylizogubov;

public class Wall implements Obstacle {
    
    private final int height;
    
    public Wall(int height) {
        this.height = height;
    }
    
    @Override
    public boolean overcomingObstacle(Actionable participant) {
        participant.jump();
        
        if (participant.getMaxJumpingHeight() >= height) {
            System.out.println("and overcomes the obstacle!");
            return true;
        }
        
        System.out.println("and does not overcome the obstacle");
        return false;
    }
}
