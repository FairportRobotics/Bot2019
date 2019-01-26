package frc.team578.robot.systems.swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.systems.interfaces.DashUpdate;

public class TalonSwerveEnclosure implements DashUpdate {

    private String name;

    private WPI_TalonSRX driveTalon;
    private WPI_TalonSRX steerTalon;

    private boolean reverseEncoder = false;
    private boolean reverseSteer = false;
    private int encoderOffset;

    public TalonSwerveEnclosure(String name, WPI_TalonSRX driveMotor, WPI_TalonSRX steerMotor, int trueNorth) {

        this.name = name;
        this.driveTalon = driveMotor;
        this.steerTalon = steerMotor;
        encoderOffset = (steerTalon.getSelectedSensorPosition() - trueNorth) + steerTalon.getSensorCollection().getAnalogInRaw();
    }

    public int getSteerEncPosition() {
        int reverse = reverseEncoder ? -1 : 1;
        return reverse * steerTalon.getSelectedSensorPosition(0);
    }

    /**
     * Sets the value of the angle encoder (used for aligning wheel in case of drift)
     * @param encPosition the current encoder value
     * TODO: This should be converted to -1 - +1 range...
     */
    public void setSteerEncPosition(int encPosition) {
        steerTalon.setSelectedSensorPosition(encPosition, 0, 10);
    }

    /**
     * Set the value of the drive motor
     * @param speed the speed value to set: -1 - full backwards, 0 - stop, +1 - full forward
     */
    public void setDriveSpeed(double speed) {
            driveTalon.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Set the angle for the steer motor
     * @param angle the angle value: -0.5 - counterclockwise 180 degrees, 0 - forward 180 degrees, +0.5 - 180 degrees clockwise
     */
    public void moveToSteerAngle(double angle) {
        steerTalon.set(ControlMode.Position, (reverseSteer ? -1 : 1) * angle * SwerveConstants.MAX_ENC_VAL);
    }

    public void moveSteerToEncoderPosition(int encPos) {
        steerTalon.set(ControlMode.Position, encPos);
    }

    public void moveSteerToAnalogPosition() {
        steerTalon.set(ControlMode.Position,encoderOffset);
    }

    public void stop() {
        this.steerTalon.stopMotor();
        this.driveTalon.stopMotor();
    }

    public void zeroSteerEncoder() {
        this.setSteerEncPosition(0);
    }

    public WPI_TalonSRX getDriveTalon() {
        return this.driveTalon;
    }

    public WPI_TalonSRX getSteerTalon() {
        return this.steerTalon;
    }

    public double getSteerCLT(int id) {
        return this.steerTalon.getClosedLoopTarget(id);
    }

    public double getSteerTurnCLTError(int id) {
        return this.steerTalon.getClosedLoopError(id);
    }

    public double getDriveCLT(int id) {
        return this.driveTalon.getClosedLoopTarget(id);
    }

    public double getDriveTurnCLTError(int id) {
        return this.driveTalon.getClosedLoopError(id);
    }

    /**
     *
     * @param speed: the speed to move the wheel, -1.0 being full backwards, 0 being stop +1.0 being full forward
     * @param angle: the angle to turn the wheel, 0 being forward, -1.0 being full turn counterclockwise, +1.0 being full turn clockwise
     */
    public void move(double speed, double angle)
    {
        int encPosition = getSteerEncPosition();
        angle = convertAngle(angle, encPosition);

        if(shouldReverse(angle, encPosition))
        {
            if(angle < 0)
                angle += 0.5;
            else
                angle -= 0.5;

            speed *= -1.0;
        }

        setDriveSpeed(speed);

        if(speed != 0.0) {
            moveToSteerAngle(angle);
        }
    }
    public String getName() {
        return name;
    }




    private boolean shouldReverse(double wa, double encoderValue){

        double ea = SwerveUtils.convertEncoderValue(encoderValue);

        //Convert the next wheel angle, which is from -.5 to .5, to 0 to 1
        if (wa < 0) wa += 1;

        //Find the difference between the two (not sure if the conversion from (-0.5 to 0.5) to (0 to 1) above is needed)
        //Difference between the two points. May be anything between -1 to 1, but we are looking for a number between -.5 to .5
        double longDifference = Math.abs(wa - ea);

        //finds shortest distance (0 to 0.5), always positive though (which is what we want)
        double difference = Math.min(longDifference, 1.0-longDifference);

        //If the difference is greater than 1/4, then return true (aka it is easier for it to turn around and go backwards than go forward)
        if (difference > 0.25) return true;
        else return false;
    }

    private double convertAngle(double angle, double encoderValue) {
        //angles are between -.5 and .5
        //This is to allow the motors to rotate in continuous circles (pseudo code on the Team 4048 forum)
        double encPos = encoderValue / SwerveConstants.MAX_ENC_VAL;

        double temp = angle;
        temp += (int)encPos;

        encPos = encPos % 1;

        if ((angle - encPos) > 0.5) temp -= 1;

        if ((angle - encPos) < -0.5) temp += 1;

        return temp;
    }

    @Override
    public void dashboardUpdate() {
        SmartDashboard.putData(steerTalon);
        SmartDashboard.putData(driveTalon);



        SmartDashboard.putNumber(name + ".drivet.araw",driveTalon.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber(name + ".steert.araw",steerTalon.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber(name + ".drivet.senspos",driveTalon.getSelectedSensorPosition());
        SmartDashboard.putNumber(name + ".steert.senspos",steerTalon.getSelectedSensorPosition());
        SmartDashboard.putNumber(name + ".drivet.CLE",driveTalon.getClosedLoopError());
        SmartDashboard.putNumber(name + ".steert.CLE",steerTalon.getClosedLoopError());
        SmartDashboard.putNumber(name + ".drivet.CLT",driveTalon.getClosedLoopTarget());
        SmartDashboard.putNumber(name + ".steert.CLT",steerTalon.getClosedLoopTarget());




        SmartDashboard.putNumber(name + ".steer.encpos",this.getSteerEncPosition());
    }
}
