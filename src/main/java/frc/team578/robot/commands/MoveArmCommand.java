package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import frc.team578.robot.enums.ArmPositionEnums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoveArmCommand extends Command {

    private static final Logger log = LogManager.getLogger(MoveArmCommand.class);

    ArmPositionEnums positionTarget;

    public MoveArmCommand(ArmPositionEnums positionTarget) {
        this.positionTarget = positionTarget;
        log.info("MoveArmCommand Constructor");
    }

    @Override
    protected void initialize() {
        log.info("Initializing MoveArmCommand");
    }

    @Override
    protected void execute() {
        if (positionTarget == ArmPositionEnums.RETRACTED) {
            Robot.armSubsystem.retract();
        } else if (positionTarget == ArmPositionEnums.MID_EXTEND) {
            Robot.armSubsystem.extendMid();
        } else if (positionTarget == ArmPositionEnums.FULL_EXTEND) {
            Robot.armSubsystem.extendFull();
        }
            log.info("Exec MoveArmCommand");
    }


    @Override
    protected void interrupted() {
        log.info("Interrupted MoveArmCommand");
    }

    @Override
    protected boolean isFinished() {

        boolean isFinished = true;
        log.info("MoveArmCommand is Finished : " + isFinished);
        return isFinished;
    }

    @Override
    protected void end() {
        log.info("Ending MoveArmCommand " + timeSinceInitialized());
    }
}
