package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.Robot;
import frc.team578.robot.subsystems.interfaces.UpdateDashboard;
import frc.team578.robot.utils.PIDFinished;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class CalibrateDrivesCommand extends TimedCommand implements UpdateDashboard {

    private static final Logger log = LogManager.getLogger(CalibrateDrivesCommand.class);

    static final int max_run_time_sec = 10;
    static final int stableCounts = 3;
    static final long checkIntervalMillis = 50;

    PIDFinished pidFinished;

    public CalibrateDrivesCommand() {
        super(max_run_time_sec);
        System.err.println("Constructor");

        Predicate<Double> successTest = (x) -> x == 0;
        pidFinished = new PIDFinished(checkIntervalMillis,stableCounts, successTest);
        requires(Robot.swerveDriveSubsystem);
    }

    @Override
    protected void initialize() {
        log.info("Initializing CalibrateDrivesCommand");

    }

    @Override
    protected void execute() {
        System.err.println("Exec");
        if (!isTimedOut()) {
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

        double currentDeriv = Robot.swerveDriveSubsystem.getSteerErrorDerivitiveSum();
        boolean stableFound = pidFinished.checkIfStable(currentDeriv);
        boolean timeOutFound = isTimedOut();
        boolean isFinished = stableFound || timeOutFound;

        if (isFinished) {
            log.info("Calibration Finish Found");
            if (timeOutFound) {
                log.warn("CalibrateDrivesCommand timed out");
            }
            if (stableFound){
                log.info("CalibrateDrivesCommand found stable");
            }
        }

        System.err.println("isFinished : " + isFinished);
        return isFinished;

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
//        SmartDashboard.putBoolean("calibc.instopz", );

    }
}
