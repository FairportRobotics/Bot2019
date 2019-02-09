package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;
import frc.team578.robot.utils.PIDFinished;
import frc.team578.robot.utils.TalonUtil;

public class ElevatorSubsystem implements Initializable, UpdateDashboard {

    // TODO: These will need PID.
    private WPI_TalonSRX armTalon;
    private WPI_TalonSRX structureTalon;

    private PIDFinished<Double> pfArm;
    private PIDFinished<Double> pfStructure;

    private static final int ARM_TALON_HEIGHT = 500;
    private static final int STRUCTURE_TALON_HEIGHT = 500;

    @Override
    public void initialize() {
        // TODO: WE NEED TO ADD PID!!!!!!!!!

        int talonID = 7;
        boolean revMotor = false;
        double pCoeff = 10;
        double iCoeff = 0;
        double dCoeff = 0;
        double fCoeff = 0;
        int iZone = 0;

        armTalon = TalonUtil.createPIDTalon(talonID, revMotor, pCoeff, iCoeff, dCoeff, fCoeff, iZone);
        structureTalon = TalonUtil.createPIDTalon(talonID, revMotor, pCoeff, iCoeff, dCoeff, fCoeff, iZone);

        pfArm = new PIDFinished<Double>(50,3,armTalon::getErrorDerivative,(x) -> x == 0);
        pfStructure = new PIDFinished<Double>(50,3,armTalon::getErrorDerivative,(x) -> x == 0);
    }

    public void setPos(int level) {
        switch (level) {
            case 0:
                armTalon.set(ControlMode.Position, 0);
                structureTalon.set(ControlMode.Position, 0);
                break;
            case 1:
                armTalon.set(ControlMode.Position, ARM_TALON_HEIGHT);
                structureTalon.set(ControlMode.Position, 0);
                break;
            case 2:
                armTalon.set(ControlMode.Position, ARM_TALON_HEIGHT);
                structureTalon.set(ControlMode.Position, STRUCTURE_TALON_HEIGHT);
                break;
        }
    }

    @Override
    public void updateDashboard() {
    }
}
