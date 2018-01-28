package frc.team871.nav;

public class InProgressDrivePlan extends DrivePlan {

    private int currentIndex = 0;
    private boolean finished = false;

    public InProgressDrivePlan(){
        super();
        currentIndex = 0;

    }

    public void resetIndex(){
        currentIndex = 0;
    }

    public Waypoint getNextPoint(){
        return this.get(currentIndex);
    }

    public void progressNextPoint(){
         currentIndex++;

         if(currentIndex>= (this.size()-1));
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * @return  long value of the time it will take to travel through all waypoints in this DrivePlan between current waypoint and the last waypoint. (Assuming in a frictionless vacuum)
     */
    public long getRemainingTime(){
        return getPointThroughPointTime(currentIndex,(this.size()-1));
    }

    /**
     * @return double value of distance through all waypoints between current waypoint and the last waypoint that are stored in this DrivePlan.
     */
    public double getRemainingDist(){
        return getPointThroughPointDist(currentIndex,(this.size()-1));
    }


    public boolean isFinished() {
        return finished;
    }
}
