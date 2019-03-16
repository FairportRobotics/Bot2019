package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        double fwd = Robot.oi.leftJoystick.getY();

        // double str = Robot.oi.getStrafe();
        double str = Robot.oi.leftJoystick.getX();

        double rot = Robot.oi.rightJoystick.getX();

//      fwd *= -1;
//		str *= -1;

        double angleDeg = Robot.gyroSubsystem.getHeading();
        str=0;
        rot=0;
        angleDeg=0;

        Robot.swerveDriveSubsystem.move(Robot.oi.deadband(fwd), Robot.oi.deadband(str), Robot.oi.deadband(rot), angleDeg);

        SmartDashboard.putNumber("swrv.fwd", fwd);
        SmartDashboard.putNumber("swrv.str", str);
        SmartDashboard.putNumber("swrv.rot", rot);
        SmartDashboard.putNumber("swrv.angleDeg", angleDeg);

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
