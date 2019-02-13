package frc.team578.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team578.robot.RobotMap;
import frc.team578.robot.enums.ArmPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;

public class ArmSubsystem implements Initializable, UpdateDashboard {

    private DoubleSolenoid firstSolenoids;
    private DoubleSolenoid secondSolenoids;

    // fsts == first solenoid top sensor
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
        firstSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_ARM_FIRST_EXTEND, RobotMap.PCM_ARM_FIRST_RETRACT);
        secondSolenoids = new DoubleSolenoid(RobotMap.PCM, RobotMap.PCM_ARM_SECOND_EXTEND, RobotMap.PCM_ARM_SECOND_RETRACT);
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

    public boolean isArmMoving() {
        // TODO : Or make this positional like Climber?
        return false;
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
