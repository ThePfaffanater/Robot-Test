package frc.team871.nav;

import com.kauailabs.navx.frc.AHRS;

public class InertialPositioning extends Thread{

    private AHRS navX;
    private Waypoint currentPos;
    private Waypoint initialPos;
    private double integral;

    public InertialPositioning(AHRS navX, Waypoint initialPos){
        this.navX = navX;
        this.initialPos = initialPos;

        currentPos = this.initialPos;
        integral = 0;
    }

    @Override
   public void run(){

   }

   private void update(){
        //get integral of acceleration to get update



   }

   private void getDisplacement(Waypoint point1, Waypoint point2){
        //subtract the values between two points
   }

   public Waypoint getCurrentPos(){
        return currentPos;
   }

}
