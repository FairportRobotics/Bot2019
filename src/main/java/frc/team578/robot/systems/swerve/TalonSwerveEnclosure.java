package frc.team578.robot.systems.swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonSwerveEnclosure extends BaseEnclosure {

    private WPI_TalonSRX driveTalon;
    private WPI_TalonSRX steerTalon;

    private boolean reverseSteerEncoder = false;
    private boolean reverseSteer = false;
    private boolean reverseDriveEncoder = false;
    private boolean reverseDrive = false;

    public TalonSwerveEnclosure(String name, WPI_TalonSRX driveMotor, WPI_TalonSRX steerMotor, double gearRatio) {

        super(name, gearRatio);

        this.driveTalon = driveMotor;
        this.steerTalon = steerMotor;
    }

    @Override
    public int getSteerEncoderPosition() {
        int reverse = reverseSteerEncoder ? -1 : 1;
        return reverse * steerTalon.getSelectedSensorPosition(0);
    }

    public int getDriveEncoderPosition() {
        int reverse = reverseDriveEncoder ? -1 : 1;
        return reverse * driveTalon.getSelectedSensorPosition(0);
    }

    @Override
    protected void setSteerAngleEncPosition(int encPosition) {
        steerTalon.setSelectedSensorPosition(encPosition, 0, 10);
    }

    @Override
    protected void setDriveSpeed(double speed) {
            driveTalon.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void setSteerAngle(double angle) {
        steerTalon.set(ControlMode.Position, (reverseSteer ? -1 : 1) * angle * gearRatio);
    }

    @Override
    public void stop() {
        this.steerTalon.stopMotor();
        this.driveTalon.stopMotor();
    }

    @Override
    public void zeroSteerEncoder() {
        this.steerTalon.setSelectedSensorPosition(0);
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
}
