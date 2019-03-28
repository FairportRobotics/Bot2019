package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.RobotMap;
import frc.team578.robot.commands.MoveElevatorAnalogCommand;
import frc.team578.robot.enums.ElevatorPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;
import frc.team578.robot.utils.PIDFinished;

public class ElevatorSubsystem extends Subsystem implements Initializable, UpdateDashboard {

    public final int ARM_LEVEL_ONE_POS = 0;
    public final int STRUCTURE_LEVEL_ONE_POS = 0;

    private final double STRUCTURE_SPEED_SCALE = .8;
    private final double ARM_SPEED_SCALE = .8;


    private WPI_TalonSRX armTalon;
    private WPI_TalonSRX structureTalon;

    private PIDFinished<Double> pfArm;
    private PIDFinished<Double> pfStructure;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveElevatorAnalogCommand());
    }

    @Override
    public void initialize() {

        armTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_ARM_TALON);
        armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
        armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
        armTalon.setNeutralMode(NeutralMode.Brake);
        armTalon.configNominalOutputForward(0, 0);
        armTalon.configNominalOutputReverse(0, 0);
        armTalon.configPeakOutputForward(ARM_SPEED_SCALE, 0);
        armTalon.configPeakOutputReverse(-ARM_SPEED_SCALE, 0);
            armTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                    LimitSwitchNormal.NormallyClosed, 0);
            armTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                    LimitSwitchNormal.NormallyClosed, 0);

        structureTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_STRUCTURE_TALON);
        structureTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
        structureTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
        structureTalon.setNeutralMode(NeutralMode.Brake);
        structureTalon.configNominalOutputForward(0, 0);
        structureTalon.configNominalOutputReverse(0, 0);
        structureTalon.configPeakOutputForward(STRUCTURE_SPEED_SCALE, 0);
        structureTalon.configPeakOutputReverse(-STRUCTURE_SPEED_SCALE, 0);
        structureTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyClosed, 0);
        structureTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyClosed, 0);


        /* Set relevant frame periods to be at least as fast as periodic rate */
//        elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
//        elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
//
//        /* set the peak and nominal outputs */
//        elevatorTalon.configNominalOutputForward(0, kTimeoutMs);
//        elevatorTalon.configNominalOutputReverse(0, kTimeoutMs);
//        elevatorTalon.configPeakOutputForward(1, kTimeoutMs);
//        elevatorTalon.configPeakOutputReverse(-1, kTimeoutMs);
//
//        /* set closed loop gains in slot0 - see documentation */
//        elevatorTalon.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
//        elevatorTalon.config_kF(0, 0.93, kTimeoutMs); // .455
//        elevatorTalon.config_kP(0, 0.455, kTimeoutMs);
//        elevatorTalon.config_kI(0, 0, kTimeoutMs);
//        elevatorTalon.config_kD(0, 0, kTimeoutMs);
//
//        /* set acceleration and vcruise velocity - see documentation */
//        elevatorTalon.configMotionCruiseVelocity(7812, kTimeoutMs); // 1689 //5000
//        elevatorTalon.configMotionAcceleration(3000, kTimeoutMs); //4000
//
//        /*
//         * TODO : zero the sensor This should be done only when elevator is at
//         * the bottom!!!
//         */
//        elevatorTalon.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
//        elevatorTalon.set(ControlMode.MotionMagic, 0);
//
//        elevatorTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
//                LimitSwitchNormal.NormallyOpen, 0);
//        elevatorTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
//                LimitSwitchNormal.NormallyOpen, 0);


        // TODO: WE NEED TO ADD PID!!!!!!!!!

//        int talonID = 7;
//        boolean revMotor = false;
//        double pCoeff = 10;
//        double iCoeff = 0;
//        double dCoeff = 0;
//        double fCoeff = 0;
//        int iZone = 0;
//
//        armTalon = TalonUtil.createPIDTalon(talonID, revMotor, pCoeff, iCoeff, dCoeff, fCoeff, iZone);
//        structureTalon = TalonUtil.createPIDTalon(talonID, revMotor, pCoeff, iCoeff, dCoeff, fCoeff, iZone);
//


//        pfArm = new PIDFinished<Double>(50, 3, armTalon::getErrorDerivative, x -> x == 0);
//        pfStructure = new PIDFinished<Double>(50, 3, structureTalon::getErrorDerivative, (x) -> x == 0);
    }

    public void moveArmMotor(double value) {
        armTalon.set(ControlMode.PercentOutput, value);
    }

    public void moveStructureMotor(double value) {
        structureTalon.set(ControlMode.PercentOutput, value);
    }


    public void moveToLevel(ElevatorPositionEnum pos) {
        switch (pos) {
            case LEVEL_ONE:
                armTalon.set(ControlMode.Position, ARM_LEVEL_ONE_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LEVEL_ONE_POS);
                break;
            default:
                // no-op
                break;
        }
    }

    public ElevatorPositionEnum getPosition() {
        if (armTalon.getSelectedSensorPosition() == ARM_LEVEL_ONE_POS && structureTalon.getSelectedSensorPosition() == STRUCTURE_LEVEL_ONE_POS) {
            return ElevatorPositionEnum.LEVEL_ONE;
        } else {
            return ElevatorPositionEnum.UNKNOWN;
        }
    }


    public boolean isFinished() {
        return pfArm.getFinished() && pfStructure.getFinished();
    }

    @Override
    public void updateDashboard() {
        SmartDashboard.putNumber("structure talon", structureTalon.getSelectedSensorPosition());
        SmartDashboard.putNumber("arm talon", armTalon.getSelectedSensorPosition());
        SmartDashboard.putNumber("armTalon.araw", armTalon.getSensorCollection().getAnalogInRaw());
    }


}
