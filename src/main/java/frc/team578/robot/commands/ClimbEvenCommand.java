package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClimbEvenCommand extends Command {
    private static final Logger log = LogManager.getLogger(ClimbEvenCommand.class);

    public ClimbEvenCommand() {
        requires(Robot.climberSubsystem);
    }

        @Override
        protected void initialize() {
            log.info("Initializing ClimberEvenCommand");
        }

        @Override
        protected void execute() {
            log.info("Exec ClimbEvenCommand");
            Robot.climberSubsystem.extendFrontClimber();
            if (Robot.gyroSubsystem.getTilt() < 3) {
                Robot.climberSubsystem.extendRearClimber();
            } else {
                Robot.climberSubsystem.cutRear();
            }
        }

    @Override
    protected void interrupted() { log.info("Interrupted ClimbEvenCommand"); }

    @Override
    protected void end() {
        log.info("Ending ClimbEvenCommand " + timeSinceInitialized());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
