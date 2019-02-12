package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team578.robot.RobotMap;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.swerve.SwerveConstants;
import frc.team578.robot.subsystems.swerve.SwerveUtils;

public class TankDriveSubsystem extends Subsystem implements Initializable {

    private WPI_TalonSRX rightMaster;
    private WPI_TalonSRX rightSlave;
    private WPI_TalonSRX leftMaster;
    private WPI_TalonSRX leftSlave;

    WPI_TalonSRX frontLeftSwerveTalon;
    WPI_TalonSRX frontRightSwerveTalon;
    WPI_TalonSRX backLeftSwerveTalon;
    WPI_TalonSRX backRightSwerveTalon;


    @Override
    protected void initDefaultCommand() {

    }


    @Override
    public void initialize() {

        rightMaster = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_DRIVE_TALON_ID);
        rightSlave = new WPI_TalonSRX(RobotMap.BACK_RIGHT_DRIVE_TALON_ID);
        leftMaster = new WPI_TalonSRX(RobotMap.FRONT_LEFT_DRIVE_TALON_ID);
        leftSlave = new WPI_TalonSRX(RobotMap.BACK_LEFT_DRIVE_TALON_ID);

        rightMaster.setSafetyEnabled(false);
        rightSlave.setSafetyEnabled(false);
        leftMaster.setSafetyEnabled(false);
        leftSlave.setSafetyEnabled(false);

        rightMaster.setExpiration(.25);
        leftMaster.setExpiration(.25);

        rightSlave.set(ControlMode.Follower, RobotMap.FRONT_RIGHT_DRIVE_TALON_ID);
        leftSlave.set(ControlMode.Follower, RobotMap.FRONT_LEFT_DRIVE_TALON_ID);

        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);

        rightMaster.set(ControlMode.PercentOutput, 0);
        leftMaster.set(ControlMode.PercentOutput, 0);

        // Align the steer wheels forward and lock them
        frontLeftSwerveTalon = SwerveUtils.createSteerTalon(RobotMap.FRONT_LEFT_ROTATE_TALON_ID,
                SwerveConstants.FRONT_LEFT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
                SwerveConstants.turn_kIZone);
        frontRightSwerveTalon = SwerveUtils.createSteerTalon(RobotMap.FRONT_RIGHT_ROTATE_TALON_ID,
                SwerveConstants.FRONT_RIGHT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
                SwerveConstants.turn_kIZone);
        backLeftSwerveTalon = SwerveUtils.createSteerTalon(RobotMap.BACK_LEFT_ROTATE_TALON_ID,
                SwerveConstants.BACK_LEFT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
                SwerveConstants.turn_kIZone);
        backRightSwerveTalon = SwerveUtils.createSteerTalon(RobotMap.BACK_RIGHT_ROTATE_TALON_ID,
                SwerveConstants.BACK_RIGHT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
                SwerveConstants.turn_kIZone);

        frontLeftSwerveTalon.setNeutralMode(NeutralMode.Brake);
        frontLeftSwerveTalon.set(ControlMode.Position,frontLeftSwerveTalon.getSelectedSensorPosition());

        frontRightSwerveTalon.setNeutralMode(NeutralMode.Brake);
        frontRightSwerveTalon.set(ControlMode.Position,frontRightSwerveTalon.getSelectedSensorPosition());

        backLeftSwerveTalon.setNeutralMode(NeutralMode.Brake);
        backLeftSwerveTalon.set(ControlMode.Position,backLeftSwerveTalon.getSelectedSensorPosition());

        backRightSwerveTalon.setNeutralMode(NeutralMode.Brake);
        backRightSwerveTalon.set(ControlMode.Position,backRightSwerveTalon.getSelectedSensorPosition());

    }

    public void move(double leftY, double rightY) {
        leftMaster.set(ControlMode.PercentOutput, -leftY);
        rightMaster.set(ControlMode.PercentOutput, rightY);
    }

    public void stop() {
        disableLeft();
        disableRight();
    }

    public void disableLeft() {
        leftMaster.stopMotor();
    }

    public void disableRight() {
        rightMaster.stopMotor();
    }
}
