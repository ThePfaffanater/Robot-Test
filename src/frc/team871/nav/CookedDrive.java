package frc.team871.nav;

import frc.team871.tools.PIDControl;

public class CookedDrive {

    private PIDControl xPID;
    private PIDControl yPID;

    private double Kp;
    private double Ki;
    private double Kd;

    public CookedDrive(double Kp, double Ki, double Kd){
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;


        xPID = new PIDControl(Kp,Ki,Kd, 0);
        yPID = new PIDControl(Kp,Ki,Kd, 0);
    }




    public double getKp() {
        return Kp;
    }

    public void setKp(double kp) {
        Kp = kp;
    }

    public double getKi() {
        return Ki;
    }

    public void setKi(double ki) {
        Ki = ki;
    }

    public double getKd() {
        return Kd;
    }

    public void setKd(double kd) {
        Kd = kd;
    }
}
