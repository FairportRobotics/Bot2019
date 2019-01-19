package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.OI;
import frc.team578.robot.Robot;

public class SwerveDriveCommand extends Command {
    public SwerveDriveCommand() {
        requires(Robot.swerveDriveSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

        double joyVal;

        double fwd = OI.getGamepadRawAxis(OI.LEFT_Y_AXIS);

        double str = OI.getGamepadRawAxis(OI.LEFT_X_AXIS);

        double rot = OI.getGamepadRawAxis(OI.RIGHT_X_AXIS);

        // The joystick forward is negative for some reason.
        fwd *= -1;
//		str *= -1;

        double angleDeg = Robot.gyroSubsystem.getAngle();

        Robot.swerveDriveSubsystem.move(fwd, str, rot, angleDeg);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
