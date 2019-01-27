package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.Robot;
import frc.team578.robot.systems.interfaces.UpdateDashboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalibrateDrivesCommand extends TimedCommand implements UpdateDashboard {

    private static final Logger log = LogManager.getLogger(CalibrateDrivesCommand.class);

    static int max_run_time_sec = 10;
    boolean found = false;
    int stableCounts = 3;
    double stopZone = 20;
    int successCount = 0;
    long lastChecked = 0;
    long checkIntervalMillis = 50;

    public CalibrateDrivesCommand() {
        super(max_run_time_sec);
        requires(Robot.swerveDriveSubsystem);
    }

    @Override
    protected void initialize() {
        log.info("Initializing CalibrateDrivesCommand");
    }

    @Override
    protected void execute() {
        if (isTimedOut()) {
            Robot.swerveDriveSubsystem.moveSteerTrueNorth();
        }
    }


    @Override
    protected void interrupted() {
        log.info("Interrupted CalibrateDrivesCommand");
        Robot.swerveDriveSubsystem.stop();
    }

    @Override
    protected boolean isFinished() {

//        double sumSteerCLE = Robot.swerveDriveSubsystem.getSteerCLTErrorSum();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastChecked > checkIntervalMillis) {
            lastChecked = currentTime;

            double currentDeriv = Robot.swerveDriveSubsystem.getSteerErrorDerivitiveSum();
            if (currentDeriv <= stopZone) {
                successCount++;
            } else {
                successCount = 0;
            }
        }

        found = (successCount >= stableCounts || isTimedOut());

        if (found) {
            if (isTimedOut()) {
                log.warn("CalibrateDrivesCommand timed out");
                Robot.swerveDriveSubsystem.stop();
            }
        }

        return found;

    }

    @Override
    protected void end() {
        log.info("Ending CalibrateDrivesCommand " + timeSinceInitialized());

        if (!isTimedOut()) {
            log.info("Zeroing Steer Encoders");
            Robot.swerveDriveSubsystem.zeroAllSteerEncoders();
        }

        Robot.swerveDriveSubsystem.stop();

    }

    @Override
    public void updateDashboard() {
        SmartDashboard.putBoolean("calibc.timedout", isTimedOut());
        SmartDashboard.putNumber("calibc.serrderiv", Robot.swerveDriveSubsystem.getSteerErrorDerivitiveSum());
        SmartDashboard.putBoolean("calibc.instopz", Robot.swerveDriveSubsystem.getSteerErrorDerivitiveSum() <= stopZone);

    }
}
