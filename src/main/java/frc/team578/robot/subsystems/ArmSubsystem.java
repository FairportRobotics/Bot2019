package frc.team578.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team578.robot.enums.ArmPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;

public class ArmSubsystem implements Initializable, UpdateDashboard {

    private DoubleSolenoid firstSolenoids;
    private DoubleSolenoid secondSolenoids;

    DigitalInput fsts = new DigitalInput(0);
    DigitalInput fsbs = new DigitalInput(0);
    DigitalInput ssts = new DigitalInput(0);
    DigitalInput ssbs = new DigitalInput(0);

    private DoubleSolenoid.Value firstRetract = DoubleSolenoid.Value.kForward;
    private DoubleSolenoid.Value firstExtend = DoubleSolenoid.Value.kReverse;

    private DoubleSolenoid.Value secondRetract = DoubleSolenoid.Value.kForward;
    private DoubleSolenoid.Value secondExtend = DoubleSolenoid.Value.kReverse;

    @Override
    public void initialize() {
//        firstSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_FRONT_CLIMB_UP, RobotMap.PCM_FRONT_CLIMB_DOWN);
//        secondSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_REAR_CLIMB_UP, RobotMap.PCM_REAR_CLIMB_DOWN);

    }

    public void retract() {
        firstSolenoids.set(firstRetract);
        secondSolenoids.set(secondRetract);
    }

    public void extendMid() {
        firstSolenoids.set(firstExtend);
        secondSolenoids.set(secondRetract);
    }

    public void extendMid2() {
        firstSolenoids.set(firstRetract);
        secondSolenoids.set(secondExtend);
    }

    public void extendFull() {
        firstSolenoids.set(firstExtend);
        secondSolenoids.set(secondExtend);
    }

    public ArmPositionEnum getArmPosition() {
        if (firstSolenoids.get() == firstExtend && secondSolenoids.get() == secondExtend) {
            return ArmPositionEnum.FULL_EXTEND;
        } else if (firstSolenoids.get() == firstExtend && secondSolenoids.get() == secondRetract) {
            return ArmPositionEnum.MID_EXTEND;
        } else if (firstSolenoids.get() == firstRetract && secondSolenoids.get() == secondExtend) {
            return ArmPositionEnum.MID2_EXTEND;
        } else {
            return ArmPositionEnum.RETRACTED;
        }
    }

    @Override
    public void updateDashboard() {

    }
}
