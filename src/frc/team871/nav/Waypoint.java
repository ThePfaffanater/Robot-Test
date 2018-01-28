package frc.team871.nav;

import frc.team871.tools.Coordinate;

public class Waypoint extends Coordinate {
    private double speed;
    private double robotAngle;

    /**
     * A location within a planned route.
     * @param gridX X value on a grid of inches
     * @param gridY X value on a grid of inches
     * @param gridZ X value on a grid of inches
     * @param speed Speed the robot should be at this position (+1.0 to -1.0)
     * @param robotAngle Orientation of Robot at this location.
     */
    public Waypoint( double gridX,  double gridY,  double gridZ, double speed, double robotAngle){
        super(gridX,gridY,gridY);
        this.speed = speed;
        this.robotAngle = robotAngle;
    }

    /**
     *  A location within a planned route.
     *  Default gridZ is set to 0.
     * @param gridX X value on a grid of inches
     * @param gridY X value on a grid of inches
     * @param speed Speed the robot should be at this position (+1.0 to -1.0)
     * @param robotAngle Orientation of Robot at this location.
     */
    public Waypoint(double gridX,  double gridY, double speed, double robotAngle){
        this(gridX,gridY, 0, speed, robotAngle);
    }

    public double getSpeed() {
        return speed;
    }

    public double getRobotAngle() {
        return robotAngle;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setRobotAngle(double robotAngle) {
        this.robotAngle = robotAngle;
    }
}
