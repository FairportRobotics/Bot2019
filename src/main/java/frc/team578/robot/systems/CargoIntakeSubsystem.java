package frc.team578.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team578.robot.RobotMap;
import frc.team578.robot.systems.interfaces.Initializable;
import frc.team578.robot.systems.interfaces.UpdateDashboard;

public class CargoIntakeSubsystem extends Subsystem implements Initializable, UpdateDashboard {

    private WPI_TalonSRX intakeTalon;
    private DoubleSolenoid intakeSolenoid;
    private double intake_power = .5;

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void initialize() {
        intakeTalon = new WPI_TalonSRX(RobotMap.CLIMB_WHEELS_TALON_ID);
        intakeTalon.setNeutralMode(NeutralMode.Brake);
        intakeTalon.setNeutralMode(NeutralMode.Brake);



        intakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_INTAKE_OPEN, RobotMap.PCM_INTAKE_CLOSE);


    }


    public void spinIntakeInward() {
        intakeTalon.set(ControlMode.PercentOutput,intake_power);
    }

    public void spinIntakeOutwards() {
        intakeTalon.set(ControlMode.PercentOutput,-intake_power);
    }

    public void spinIntakeStop() {
        intakeTalon.set(ControlMode.PercentOutput,0);
    }

    public void openIntake() {
        intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void closeIntake() {
        intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public boolean isIntakeOpen() {
        return intakeSolenoid.get().equals(DoubleSolenoid.Value.kForward);
    }

    public boolean isIntakeClosed() {
        return intakeSolenoid.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void updateDashboard() {

    }
}
