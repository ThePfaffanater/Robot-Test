package frc.team871.tools;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * @author TP-Laptop on 1/31/2018.
 * @project Robo 2018 ALPHA TEST
 */
public class ReadWritePID implements PIDOutput, PIDSource {

    private double outVal;
    private double inVal;
    private PIDSourceType type;

    public ReadWritePID(PIDSourceType type){
        outVal = 0;
        inVal = 0;
        this.type = type;
    }

    //PID sets its output here
    @Override
    public void pidWrite(double output) {
        this.outVal = outVal;
    }

    //Read PID's output because I cant just read from itself which is objectively stupid
    public double getOutput(){
        return outVal;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        this.type = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return this.type;
    }

    @Override
    public double pidGet() {
        return inVal;
    }
}
