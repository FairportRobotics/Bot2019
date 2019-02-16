package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TankDriveCommand extends Command {

    private static final Logger log = LogManager.getLogger(TankDriveCommand.class);

    public TankDriveCommand() {
        requires(Robot.tankDriveSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

//        double ly = Robot.oi.getJoyLeftY();
//        double ry = Robot.oi.getJoyRightY();
        double ly = Robot.oi.getGP1().getPadLeftY();
        double ry = Robot.oi.getGP1().getPadRightY();

        boolean invertJoysticks = true;

        if (invertJoysticks) {
            ly *= -1;
            ry *= -1;
        }

        Robot.tankDriveSubsystem.move(ly, ry);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        log.info("TankDriveCommand Ended");
    }

    @Override
    protected void interrupted() {
        log.info("TankDriveCommand Interrupted");

    }

}
