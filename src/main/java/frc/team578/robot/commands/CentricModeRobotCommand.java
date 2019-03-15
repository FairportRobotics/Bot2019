package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CentricModeRobotCommand extends Command {

    private static final Logger log = LogManager.getLogger(CentricModeRobotCommand.class);

    public CentricModeRobotCommand() {
        log.info("CentricModeRobotCommand Constructor");
    }

    @Override
    protected void initialize() {
        log.info("Initializing CentricModeRobotCommand");
    }

    @Override
    protected void execute() {

        log.info("Exec CentricModeRobotCommand");
        Robot.swerveDriveSubsystem.setModeRobot();
    }


    @Override
    protected void interrupted() {
        log.info("Interrupted CentricModeRobotCommand");
    }

    @Override
    protected boolean isFinished() {

        boolean isFinished = true;
        log.info ("CentricModeRobotCommand is Finished : " + isFinished);
        return isFinished;
    }

    @Override
    protected void end() {

        log.info("Ending CentricModeRobotCommand " + timeSinceInitialized());

    }
}
