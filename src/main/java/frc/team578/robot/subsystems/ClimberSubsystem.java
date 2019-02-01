package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.RobotMap;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;

public class ClimberSubsystem extends Subsystem implements Initializable, UpdateDashboard {

    private DoubleSolenoid frontSolenoids;
    private DoubleSolenoid rearSolenoids;
    private WPI_TalonSRX climbWheelsTalon;
    private double wheel_power = .5;


    @Override
    protected void initDefaultCommand() {
    }

    @Override
    public void initialize() {
        frontSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_FRONT_CLIMB_UP, RobotMap.PCM_FRONT_CLIMB_DOWN);
        rearSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_REAR_CLIMB_UP, RobotMap.PCM_REAR_CLIMB_DOWN);
        climbWheelsTalon = new WPI_TalonSRX(RobotMap.CLIMB_WHEELS_TALON_ID);
    }

    public void moveFrontClimberUp() {
        frontSolenoids.set(DoubleSolenoid.Value.kForward);
    }

    public void moveFrontClimberDown() {
        frontSolenoids.set(DoubleSolenoid.Value.kReverse);
    }

    public void moveRearClimberUp() {
        rearSolenoids.set(DoubleSolenoid.Value.kForward);
    }

    public void moveRearClimberDown() {
        rearSolenoids.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isFrontClimberUp() {
        return frontSolenoids.get().equals(DoubleSolenoid.Value.kForward);
    }

    public boolean isFrontClimberDown() {
        return frontSolenoids.get().equals(DoubleSolenoid.Value.kReverse);
    }

    public boolean isRearClimberUp() {
        return rearSolenoids.get().equals(DoubleSolenoid.Value.kForward);
    }

    public boolean isRearClimberDown() {
        return rearSolenoids.get().equals(DoubleSolenoid.Value.kReverse);
    }

    public void climbWheelsForward() {
        climbWheelsTalon.set(ControlMode.PercentOutput, wheel_power);
    }

    public void climbWheelsBackwards(int val) {
        climbWheelsTalon.set(ControlMode.PercentOutput, -wheel_power);
    }

    public void climbWheelsStop() {
        climbWheelsTalon.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void updateDashboard() {
        SmartDashboard.putData("climb.fs",frontSolenoids);
        SmartDashboard.putData("climb.rs",rearSolenoids);
        SmartDashboard.putData("climb.cwtal",climbWheelsTalon);
    }
}
