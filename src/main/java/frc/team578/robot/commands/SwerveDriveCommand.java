package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.OI;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwerveDriveCommand extends Command {

    private static final Logger log = LogManager.getLogger(SwerveDriveCommand.class);

    public SwerveDriveCommand() {
        requires(Robot.swerveDriveSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

        double fwd = Robot.oi.getPadLeftY();
//        double fwd = Robot.oi.getGamepadRawAxis(OI.LEFT_Y_AXIS);

        double str = Robot.oi.getPadLeftX();
//        double str = Robot.oi.getGamepadRawAxis(OI.LEFT_X_AXIS);

        double rot = Robot.oi.getPadRightX();
//        double rot = Robot.oi.getGamepadRawAxis(OI.RIGHT_X_AXIS);

        // The joystick forward is negative for some reason.
        fwd *= -1;
//		str *= -1;

        double angleDeg = Robot.gyroSubsystem.getHeading();

        Robot.swerveDriveSubsystem.move(fwd, str, rot, angleDeg);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        log.info("SwerveDriveCommand Ended");
    }

    @Override
    protected void interrupted() {
        log.info("SwerveDriveCommand Interrupted");

    }

}
