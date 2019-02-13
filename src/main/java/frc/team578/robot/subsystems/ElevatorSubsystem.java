package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.team578.robot.enums.ElevatorPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;
import frc.team578.robot.utils.PIDFinished;

public class ElevatorSubsystem implements Initializable, UpdateDashboard {

    public final int ARM_LEVEL_ONE_POS = 0;
    public final int STRUCTURE_LEVEL_ONE_POS = 0;

    // TODO: These will need PID.
    private WPI_TalonSRX armTalon;
    private WPI_TalonSRX structureTalon;

    private PIDFinished<Double> pfArm;
    private PIDFinished<Double> pfStructure;

    @Override
    public void initialize() {
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
        pfArm = new PIDFinished<Double>(50,3,armTalon::getErrorDerivative,x -> x == 0);
        pfStructure = new PIDFinished<Double>(50,3,armTalon::getErrorDerivative,(x) -> x == 0);
    }

//    public void setPos(int level) {
//        switch (level) {
//            case 0:
//                armTalon.set(ControlMode.Position, 0);
//                structureTalon.set(ControlMode.Position, 0);
//                break;
//            case 1:
//                armTalon.set(ControlMode.Position, ARM_TALON_HEIGHT);
//                structureTalon.set(ControlMode.Position, 0);
//                break;
//            case 2:
//                armTalon.set(ControlMode.Position, ARM_TALON_HEIGHT);
//                structureTalon.set(ControlMode.Position, STRUCTURE_TALON_HEIGHT);
//                break;
//        }
//    }

    public void moveArmMotor(double value) {
        armTalon.set(ControlMode.PercentOutput,value);
    }

    public void moveStructureMotor(double value) {
        structureTalon.set(ControlMode.PercentOutput,value);
    }


    public void moveToLevel(ElevatorPositionEnum pos) {
        switch(pos) {
            case LEVEL_ONE:
                armTalon.set(ControlMode.Position,ARM_LEVEL_ONE_POS);
                structureTalon.set(ControlMode.Position,STRUCTURE_LEVEL_ONE_POS);
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
    }
}
