package frc.team871.nav;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.team871.nav.inertial.InertialPositioning;
import frc.team871.robot.Drivey;
import frc.team871.tools.ReadWritePID;

public class Navigation  extends Thread {


   AHRS navX;


    private DrivePlan drivePlan;
    private InProgressDrivePlan currentInProgressPlan;
    private Waypoint currLocation;
    private InertialPositioning INS;
    private PIDSourceType pidSrc;
    private PIDController distPID;

    private double errorDist;
    private double distToNextLocation;

    private ReadWritePID magnitudePIDInterface;
    private PIDController magnitudePID;
    private double magnitudeKp;
    private double magnitudeKi;
    private double magnitudeKd;

    private ReadWritePID anglePIDInterface;
    private PIDController anglePID;
    private double angleKp;
    private double angleKi;
    private double angleKd;


    public Navigation(AHRS navX, DrivePlan drivePlan, Drivey drive){
        this.navX = navX;
        this.drivePlan = drivePlan;

        distToNextLocation = 0;
        errorDist =  6; //TODO: Find appropriate value for error distance
        pidSrc = PIDSourceType.kDisplacement;

        magnitudePIDInterface = new ReadWritePID(pidSrc);
        magnitudeKp = 0;
        magnitudeKi = 0;
        magnitudeKd = 0;
        magnitudePID = new PIDController(magnitudeKp, magnitudeKi, magnitudeKd, magnitudePIDInterface, magnitudePIDInterface);

        anglePIDInterface = new ReadWritePID(PIDSourceType.valueOf("angle"));
        angleKp = 0;
        angleKi = 0;
        angleKd = 0;
        anglePID = new PIDController(angleKp, angleKi, angleKd, magnitudePIDInterface, magnitudePIDInterface);

        INS = new InertialPositioning(navX,new Waypoint(0,0,.5,0));
    }

    public void loadNewDrivePlan(DrivePlan drivePlan){
        this.currentInProgressPlan = (InProgressDrivePlan)drivePlan;

    }

    public void loadNewInProgressNav(InProgressDrivePlan newInProgNav){
        this.currentInProgressPlan = newInProgNav;

    }

    public void startNav(){
        currentInProgressPlan.resetIndex();
        resumeNav();//Has same use as resumeNav() but restarts the nav progress
    }

    public void pauseNav(){

    }

    public void resumeNav(){
        INS.start();

        if(true && INS.isAlive()){ //if no obstructions or hardsets telling robor to not proceed forward resume nav
            this.currLocation = INS.getCurrentPos();
        }

    }

    public long getEstTime(){
        return currentInProgressPlan.getRemainingTime();

    }

    public InProgressDrivePlan stealMyCurrentInProgressNavigation(){
        return currentInProgressPlan;
    }

    private void navToPoint(Waypoint currLocation){
        if(!currentInProgressPlan.isFinished()) {
            Waypoint nextLocation = currentInProgressPlan.getNextPoint();
            this.distToNextLocation = calcDist(currLocation, nextLocation);
            double dir = calcAngle(currLocation, nextLocation);



            //set drive to a direction and magnitude untill the robot is within range of error.
            if (this.distToNextLocation < errorDist) {
                currentInProgressPlan.progressNextPoint();
            }
        }else{
            //done with this nav plan.
        }
    }

    private void driveToPoint(double distToNextLocation, double dir){

    }

    private double calcDist(Waypoint point1, Waypoint point2){
        return Math.sqrt(Math.pow(point2.getX().doubleValue()-point1.getX().doubleValue(), 2) + Math.pow(point2.getZ().doubleValue()-point1.getZ().doubleValue(), 2) + Math.pow(point2.getY().doubleValue()-point1.getY().doubleValue(), 2));
        //^^ |P1P2|=√(x2−x1)2+(y2−y1)2+(z2−z1) the 3D dist formula. //dont try to look at it just assume it works

    }

    private double calcAngle(Waypoint point1, Waypoint point2) {
            double angle = (float) Math.toDegrees(Math.atan2(point2.getY().doubleValue() - point1.getY().doubleValue(), point2.getX().doubleValue() - point1.getX().doubleValue()));

            return angle;
        }

}
