package frc.team871.nav;

import java.util.ArrayList;

/**
 * Contains Waypoints in an ArrayList. Can get general stats on whole of list like total distance and estimated time to completion.
 * Is indexed at 0
 */
public class DrivePlan extends ArrayList<Waypoint>{

    public DrivePlan(){

     }


     public Waypoint getLastPoint(){
        return this.get(this.size()-1);//get the last waypoint in this drivePlan
     }

    /**
     * @param speed you want to set to all waypoints in this DrivePlan
     */
    public void setAllSpeeds(double speed){
        for(int i = (0); i < (this.size()-1); i++){ //iterates between all waypoints
            this.get(i).setSpeed(speed);
        }
    }

    /**
     * @param index1 your current index(waypoint in this DrivePlan)
     * @return  long value of the time it will take to travel through all waypoints in this DrivePlan between index1 and the last waypoint. (Assuming in a frictionless vacuum)
     * Is indexed at 0.
     */
    public double getRemainingTime(int index1){
        return getPointThroughPointTime(index1,(this.size()-1));
    }

    /**
     * @param index1 first index(waypoint in this DrivePlan)
     * @param index2 last index(waypoint in this DrivePlan)
     * @return long value of the time it will take to travel through all waypoints in this DrivePlan between index1 and index2. (Assuming in a frictionless vacuum)
     * Is indexed at 0.
     */
    public long getPointThroughPointTime(int index1, int index2) {
        long totalTime;
        double unfixedTime =  0;

        for(int i = (index1+1); i < index2; i++){ //Gets the distance and divides by velocity to get time.
            Waypoint point1 = this.get(i-1);
            Waypoint point2 = this.get(i);
            double dist= Math.sqrt(Math.pow(point2.getX()-point1.getX(), 2) + Math.pow(point2.getZ()-point1.getZ(), 2) + Math.pow(point2.getY()-point1.getY(), 2));
            //^^ |P1P2|=√(x2−x1)2+(y2−y1)2+(z2−z1) the 3D dist formula.

            if(point2.getSpeed() !=0) {
                unfixedTime += dist / point2.getSpeed(); //gets the desired speed of the coordinate you are going to, not what one you came from hence it being point2 and not point1.
                //^^ V=d/t -> t=d/v velocity formula
            }
        }

        totalTime = (long)unfixedTime; //TODO: Should I use wrapper classes? (slightly more efficient cast like this but it truncates to 0)
        return totalTime;
    }

    /**
     * @param index1 your current index(waypoint in this DrivePlan)
     * @return double value of distance through all waypoints between index1 and the last waypoint that are stored in this DrivePlan.
     * Is indexed at 0.
     */
    public double getRemainingDist(int index1){
        return getPointThroughPointDist(index1,(this.size()-1));
    }

    /**
     * @param index1 first index(waypoint in this DrivePlan)
     * @param index2 last index(waypoint in this DrivePlan)
     * @return double value of distance through all waypoints between index1 and index2 that are stored in this DrivePlan.
     * Is indexed at 0.
     */
    public double getPointThroughPointDist(int index1, int index2){
        if(this.size() < 2) return 0; //When there is only one coordinate in the list, send back 0

        double fullDist = 0;

        for(int i = (index1+1); i < index2; i++){
            Waypoint point1 = this.get(i-1);
            Waypoint point2 = this.get(i);
            fullDist+= Math.sqrt(Math.pow(point2.getX()-point1.getX(), 2) + Math.pow(point2.getZ()-point1.getZ(), 2) + Math.pow(point2.getY()-point1.getY(), 2));
            //^^ |P1P2|=√(x2−x1)2+(y2−y1)2+(z2−z1) the 3D dist formula.
        }


        return fullDist;
    }

}
