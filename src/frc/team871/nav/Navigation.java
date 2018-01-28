package frc.team871.nav;

import com.kauailabs.navx.frc.AHRS;
import frc.team871.robot.Drivey;

public class Navigation {


   AHRS navX;


    private DrivePlan drivePlan;
    private InProgressNavigation currentInProgressPlan;
    private Waypoint currLocation;

    private double errorDist;
    public Navigation(AHRS navX, DrivePlan drivePlan, Drivey drive){
        this.navX = navX;
        this.drivePlan = drivePlan;

        errorDist =  6; //error distance in inches
    }

    public void loadNewDrivePlan(DrivePlan drivePlan){
        this.currentInProgressPlan = (InProgressNavigation)drivePlan;

    }

    public void loadNewInProgressNav(InProgressNavigation newInProgNav){
        this.currentInProgressPlan = newInProgNav;

    }


    public void startNav(){
        currentInProgressPlan.resetIndex();
        resumeNav();//Has same use as resumeNav() but restarts the nav progress
    }

    public void pauseNav(){

    }

    public void resumeNav(){
        if(true){ //if no obstructions or hardsets telling robor to not proceed forward resume nav
            //navToPoint();
        }

    }

    public long getEstTime(){
        return currentInProgressPlan.getRemainingTime();

    }

    public InProgressNavigation stealMyCurrentInProgressNavigation(){
        return currentInProgressPlan;
    }

    private void navToPoint(Waypoint currLocation){
        if(!currentInProgressPlan.isFinished()) {
            Waypoint nextLocation = currentInProgressPlan.getNextPoint();
            double distToNextLocation = calcDist(currLocation, nextLocation);
            double dir = calcAngle(currLocation, nextLocation);



            //set drive to a direction and magnitude untill the robot is within range of error.
            if (distToNextLocation < errorDist) {
                currentInProgressPlan.progressNextPoint();
            }
        }else{
            //done with this nav plan.
        }
    }

    private void driveToPoint( Drivey drive, AHRS gyro,  double distToNextLocation, double dir){


    }

    private double calcDist(Waypoint point1, Waypoint point2){
        return Math.sqrt(Math.pow(point2.getX()-point1.getX(), 2) + Math.pow(point2.getZ()-point1.getZ(), 2) + Math.pow(point2.getY()-point1.getY(), 2));
        //^^ |P1P2|=√(x2−x1)2+(y2−y1)2+(z2−z1) the 3D dist formula.

    }

    private double calcAngle(Waypoint point1, Waypoint point2) {
            float angle = (float) Math.toDegrees(Math.atan2(point2.getY() - point1.getY(), point2.getX() - point1.getX()));
            if(angle < 0){
                angle += 360;
            }

            return angle;
        }
}
