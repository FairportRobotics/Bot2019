package frc.team578.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team578.robot.RobotMap;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;

public class CargoIntakeSubsystem extends Subsystem implements Initializable, UpdateDashboard {

    private WPI_TalonSRX intakeTalon;
    private DoubleSolenoid intakeSolenoid;
    private double intake_power = .75;

//     its = Intake Top Sensor
//    DigitalInput its = new DigitalInput(1);
//    DigitalInput ibs = new DigitalInput(1);

    private DoubleSolenoid.Value intakeRetract = DoubleSolenoid.Value.kForward;
    private DoubleSolenoid.Value intakeExtend = DoubleSolenoid.Value.kReverse;

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void initialize() {
        intakeTalon = new WPI_TalonSRX(RobotMap.INTAKE_TALON);
        intakeTalon.setNeutralMode(NeutralMode.Brake);

//        intakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_INTAKE_OPEN, RobotMap.PCM_INTAKE_CLOSE);
    }


    public void spinIntakeInward() {
        intakeTalon.set(ControlMode.PercentOutput, intake_power);
    }

    public void spinIntakeOutwards() {
        intakeTalon.set(ControlMode.PercentOutput, -intake_power);
    }

    public void spinIntakeStop() {
        intakeTalon.set(ControlMode.PercentOutput, 0);
    }

    public void extendIntake() {
        intakeSolenoid.set(intakeExtend);
    }

    public void retractIntake() {
        intakeSolenoid.set(intakeRetract);
    }

    public boolean isIntakeExtended() {
        return intakeSolenoid.get() == intakeExtend;
    }

    public boolean isIntakeRetracted() {
        return intakeSolenoid.get() == intakeRetract;
    }

    @Override
    public void updateDashboard() {

    }
}
