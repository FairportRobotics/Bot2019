package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CentricModeFieldCommand extends Command {

    private static final Logger log = LogManager.getLogger(CentricModeFieldCommand.class);

    public CentricModeFieldCommand() {
        log.info("CentricModeFieldCommand Constructor");
    }

    @Override
    protected void initialize() {
        log.info("Initializing CentricModeFieldCommand");
    }

    @Override
    protected void execute() {

        log.info("Exec CentricModeFieldCommand");
        Robot.swerveDriveSubsystem.setModeField();
    }


    @Override
    protected void interrupted() {
        log.info("Interrupted CentricModeFieldCommand");
    }

    @Override
    protected boolean isFinished() {

        boolean isFinished = true;
        log.info ("CentricModeFieldCommand is Finished : " + isFinished);
        return isFinished;
    }

    @Override
    protected void end() {

        log.info("Ending CentricModeFieldCommand " + timeSinceInitialized());

    }
}
