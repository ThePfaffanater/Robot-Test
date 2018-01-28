package frc.team871.nav.inertial;

import com.kauailabs.navx.frc.AHRS;
import frc.team871.nav.Waypoint;
import frc.team871.tools.Coordinate;
import org.opencv.video.KalmanFilter;

public class InertialPositioning extends Thread{

    private AHRS navX;
    private Waypoint currentPos;
    private Waypoint initialPos;
    private KalmanFilter filter;


    Coordinate lastVeloComponets;
    Coordinate displacementComponets;

    public InertialPositioning(AHRS navX, Waypoint initialPos){
        this.navX = navX;
        this.initialPos = initialPos;
        currentPos = this.initialPos;

        filter = new KalmanFilter();

        resetDisplace();
    }

    @Override
   public void run(){
        update();
   }

   private void update(){
        //get integral of acceleration to get update
            //navX.getDisplacementX(); //dont use becuase It appears to not utilize a filter.

       float filteredXAccel = (navX.getRawAccelX());// in terms of g
       float filteredYAccel = (navX.getRawAccelY());// in terms of g

       updateDisplace_g(filteredXAccel,filteredYAccel, 60);

   }

   private void updateDisplace_m_s2(Float xAccel_m_s2, Float yAccel_m_s2, Integer updateRate_hz){
        Float sampleTime = 1.0F / (float)updateRate_hz;

        Float currentX = (this.lastVeloComponets.getX().floatValue() + xAccel_m_s2 * sampleTime);
        Float currentY = (this.lastVeloComponets.getY().floatValue() + yAccel_m_s2 * sampleTime);
        Coordinate curVeloComponets = new Coordinate(currentX, currentY);

        Float dispX = this.displacementComponets.getX().floatValue() + (this.lastVeloComponets.getX().floatValue() + 0.5F * xAccel_m_s2 * (sampleTime*sampleTime));
        Float dispY = (this.lastVeloComponets.getY().floatValue() + 0.5F * yAccel_m_s2 * (sampleTime*sampleTime));
        this.displacementComponets.setX(dispX);
        this.displacementComponets.setY(dispY);

        this.lastVeloComponets = curVeloComponets;
   }

   private void updateDisplace_g(float xAccel_g, float yAccel_g, int updateRate_hz){
        double FGrav =  9.80665;

        //Converts to m/s^2
        float xAccel_m_s2 = (float) (xAccel_g / FGrav);
        float yAccel_m_s2 = (float) (yAccel_g / FGrav);
        updateDisplace_m_s2(xAccel_m_s2, yAccel_m_s2, updateRate_hz);
    }

   private void resetDisplace(){
        lastVeloComponets     = new Coordinate(0.0F,0.0F);
        displacementComponets = new Coordinate(0.0F,0.0F);
   }

   private Coordinate<Double> getDisplacement(){
        Double dispX = displacementComponets.getX().doubleValue();
        Double dispY = displacementComponets.getY().doubleValue();

        return new Coordinate<Double>(dispX,dispY);
   }

   private Coordinate<Double> getVelocity(){
        Double veloX = lastVeloComponets.getX().doubleValue();
        Double veloY = lastVeloComponets.getY().doubleValue();

        return new Coordinate<Double>(veloX, veloY);
    }

   public Waypoint getCurrentPos(){

        return currentPos;
   }

}
