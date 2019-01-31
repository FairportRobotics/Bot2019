package frc.team578.robot.systems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.team578.robot.RobotMap;
import frc.team578.robot.systems.interfaces.Initializable;
import frc.team578.robot.systems.interfaces.UpdateDashboard;

public class ElevatorSubsystem implements Initializable, UpdateDashboard {

    // TODO: These will need PID.
    private WPI_TalonSRX first;
    private WPI_TalonSRX second;

    @Override
    public void initialize() {

//        elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_TALON);
//        elevatorTalon.setNeutralMode(NeutralMode.Brake);
//
//        elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
//        elevatorTalon.setSensorPhase(true);
//        elevatorTalon.setInverted(false);
//
//        /* Set relevant frame periods to be at least as fast as periodic rate */
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

    }

    @Override
    public void updateDashboard() {

    }


}
