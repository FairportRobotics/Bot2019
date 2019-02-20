package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team578.robot.RobotMap;
import frc.team578.robot.commands.MoveElevatorAnalogCommand;
import frc.team578.robot.enums.ElevatorPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;
import frc.team578.robot.utils.PIDFinished;

public class ElevatorSubsystem extends Subsystem implements Initializable, UpdateDashboard {

    public final int ARM_LOWEST_POS = 0;            //lowest arm position
    public final int STRUCTURE_LOWEST_POS = 0;      //lowest structure position

    public final int ARM_MAX_HEIGHT_POS = 0;        //used for level 3 or higher
    public final int STRUCTURE_MAX_HEIGHT_POS = 0;  //used only in highest cargo height
                                                    //not full height, 2 inches less due to being too high

    //Hatch heights
    public final int LEVEL_ONE_HATCH_POS = 0;       //used for ALL low hatches
    public final int LEVEL_TWO_HATCH_POS = 0;       //used for level 2 rocket
    public final int LEVEL_THREE_HATCH_POS = 0;     //structure

    //Cargo heights
    public final int LEVEL_ONE_CARGO_POS = 0;
    public final int LEVEL_TWO_CARGO_POS = 0;
    public final int CARGO_BAY_CARGO_POS = 0;

    //Player Station heights
    public final int PLAYER_STATION_CARGO_POS = 0;


    public static final int kTimeoutMs = 10;


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

        armTalon.config_kF(0, 0.93, kTimeoutMs);
        armTalon.config_kP(0, 0.455, kTimeoutMs);
        armTalon.config_kI(0, 0, kTimeoutMs);
        armTalon.config_kD(0, 0, kTimeoutMs);

        /* set acceleration and vcruise velocity - see documentation */
        armTalon.configMotionCruiseVelocity(7812, kTimeoutMs); // no idea what this means
        armTalon.configMotionAcceleration(3000, kTimeoutMs); // no idea what this means

        armTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_ARM_TALON);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
        armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
        armTalon.setNeutralMode(NeutralMode.Brake);
        /* set the peak and nominal outputs */
        armTalon.configNominalOutputForward(0, 0);
        armTalon.configNominalOutputReverse(0, 0);
        armTalon.configPeakOutputForward(1, 0);
        armTalon.configPeakOutputReverse(-1, 0);
            armTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                    LimitSwitchNormal.NormallyClosed, 0);
            armTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                    LimitSwitchNormal.NormallyOpen, 0);



        structureTalon.config_kF(0, 0.93, kTimeoutMs);
        structureTalon.config_kP(0, 0.455, kTimeoutMs);
        structureTalon.config_kI(0, 0, kTimeoutMs);
        structureTalon.config_kD(0, 0, kTimeoutMs);

        /* set acceleration and vcruise velocity - see documentation */
        structureTalon.configMotionCruiseVelocity(7812, kTimeoutMs);
        structureTalon.configMotionAcceleration(3000, kTimeoutMs);

        structureTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_STRUCTURE_TALON);
        structureTalon.setNeutralMode(NeutralMode.Brake);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        structureTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
        structureTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
        structureTalon.setNeutralMode(NeutralMode.Brake);
        /* set the peak and nominal outputs */
        structureTalon.configNominalOutputForward(0, 0);
        structureTalon.configNominalOutputReverse(0, 0);
        structureTalon.configPeakOutputForward(1, 0);
        structureTalon.configPeakOutputReverse(-1, 0);

        structureTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen, 0);
        structureTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyClosed, 0);



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
            case CARG0_BAY_CARGO:
                armTalon.set(ControlMode.Position, CARGO_BAY_CARGO_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS);
                break;

            case LEVEL_ONE_CARGO:
                armTalon.set(ControlMode.Position, LEVEL_ONE_CARGO_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS);
                break;

            case LEVEL_TWO_CARGO:
                armTalon.set(ControlMode.Position, LEVEL_TWO_CARGO_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS); //Not sure if an amount is needed, check arm max height
                break;

            case LEVEL_THREE_CARGO:
                armTalon.set(ControlMode.Position, ARM_MAX_HEIGHT_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_MAX_HEIGHT_POS);
                break;

            case LEVEL_ONE_HATCH:
                armTalon.set(ControlMode.Position, LEVEL_ONE_HATCH_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS);
                break;

            case LEVEL_TWO_HATCH:
                armTalon.set(ControlMode.Position, LEVEL_TWO_HATCH_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS);
                break;

            case LEVEL_THREE_HATCH:
                armTalon.set(ControlMode.Position, ARM_MAX_HEIGHT_POS);
                structureTalon.set(ControlMode.Position, LEVEL_THREE_HATCH_POS);
                break;

            case PLAYER_STATION_CARGO:
                armTalon.set(ControlMode.Position, PLAYER_STATION_CARGO_POS);
                structureTalon.set(ControlMode.Position, STRUCTURE_LOWEST_POS);
                break;

            default:
                // no-op
                break;
        }
    }

//    public ElevatorPositionEnum getPosition() {
//        if (armTalon.getSelectedSensorPosition() == ARM_LOWEST_POS && structureTalon.getSelectedSensorPosition() == STRUCTURE_LOWEST_POS) {
//            return ElevatorPositionEnum.LEVEL_ONE;
//        } else {
//            return ElevatorPositionEnum.UNKNOWN;
//        }
//    }


    public boolean isFinished() {
        return pfArm.getFinished() && pfStructure.getFinished();
    }

    @Override
    public void updateDashboard() {
    }


}
