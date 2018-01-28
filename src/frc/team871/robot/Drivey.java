package frc.team871.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.team871.tools.EnhancedXBoxController;
import org.usfirst.frc.team871.tools.XBoxAxes;

public class Drivey implements PIDOutput {

    private Talon rB;
    private Talon lB;

    private AHRS gyro;

    private MecanumDrive mechDrive;

    public Drivey(Talon lF, Talon rF, Talon rB, Talon lB, AHRS gyro){
      this.gyro = gyro;

        mechDrive = new MecanumDrive(lF, lB, rF, rB);
    }

    /**
     *Drives with robot field oriented.
     * @param xboxCon Enhanced Xbox Controller
     */
    public void driveFieldOriented(EnhancedXBoxController xboxCon){
        driveCartesian(xboxCon.getValue(XBoxAxes.LEFTX),xboxCon.getValue(XBoxAxes.LEFTY),xboxCon.getValue(XBoxAxes.RIGHTX), this.gyro.getAngle());
    }

    public void driveRobotOriented(EnhancedXBoxController xboxCon){
        driveCartesian(xboxCon.getValue(XBoxAxes.LEFTX),xboxCon.getValue(XBoxAxes.LEFTY),xboxCon.getValue(XBoxAxes.RIGHTX), 0);
    }

    public void orientReset(){
        gyro.reset();
    }

    /**
     *
     * @param x Horizontal
     * @param y Vertical
     * @param rot Rotation
     * gyroAngle is set to 0, robot oriented.
     */
    public void driveCartesian(double x, double y, double rot){
        driveCartesian(x,y,rot,0);
    }

    /**
     *
     * @param x Horizontal
     * @param y vertical
     * @param rot amount rotation
     * @param gyroAngle angle from gyro
     */
    public void driveCartesian(double x, double y, double rot, double gyroAngle){

        double mag = Math.sqrt((x*x)+(y*y));
        double dir = Math.atan2(y,x);

        drivePolar(mag, dir, rot, gyroAngle);
    }

    /**
     *
     * @param dir double direction
     * @param mag double magnitude
     * @param rot double rotation addition
     * @param gyroAngle double offset of rotation addition based off current angle of robot with gyro.
     */
    public void drivePolar(double dir, double mag, double rot, double gyroAngle){
        dir = dir-gyroAngle;

        mag = Math.min(mag , +1 );
        mag = Math.max(mag , -1 );

        mechDrive.drivePolar(mag, dir, rot);
    }

    @Override
    public void pidWrite(double output) {

    }
}
