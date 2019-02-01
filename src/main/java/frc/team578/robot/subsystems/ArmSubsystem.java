package frc.team578.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;

public class ArmSubsystem implements Initializable, UpdateDashboard {

    private DoubleSolenoid firstSolenoids;
    private DoubleSolenoid secondSolenoids;

    @Override
    public void initialize() {
//        frontSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_FRONT_CLIMB_UP, RobotMap.PCM_FRONT_CLIMB_DOWN);
//        rearSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_REAR_CLIMB_UP, RobotMap.PCM_REAR_CLIMB_DOWN);
    }

    @Override
    public void updateDashboard() {

    }
}
