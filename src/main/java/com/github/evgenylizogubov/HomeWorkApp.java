package com.github.evgenylizogubov;

public class HomeWorkApp {
    
    public static void main(String[] args) {
        Actionable cat = new Cat("Pushistik", 200, 3);
        Actionable human = new Human("Fry", 300, 1);
        Actionable robot = new Robot("Bender", 500, 2);
        
        Actionable[] participants = new Actionable[3];
        participants[0] = cat;
        participants[1] = human;
        participants[2] = robot;
        
        Obstacle wall1 = new Wall(2);
        Obstacle wall2 = new Wall(3);
        Obstacle treadmill1 = new Treadmill(300);
        Obstacle treadmill2 = new Treadmill(500);
        
        Obstacle[] obstacles = new Obstacle[4];
        obstacles[0] = wall1;
        obstacles[1] = wall2;
        obstacles[2] = treadmill1;
        obstacles[3] = treadmill2;
        
        for (Actionable participant : participants) {
            for (Obstacle obstacle : obstacles) {
                if (!obstacle.overcomingObstacle(participant)) {
                    break;
                }
            }
        }
    }
}
