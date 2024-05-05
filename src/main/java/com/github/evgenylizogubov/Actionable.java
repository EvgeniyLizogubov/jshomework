package com.github.evgenylizogubov;

public interface Actionable {
    
    void run();
    
    void jump();
    
    int getMaxRunningDistance();
    
    int getMaxJumpingHeight();
}
