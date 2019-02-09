package frc.team578.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team578.robot.commands.CalibrateDrivesCommand;
import frc.team578.robot.commands.SampleCommand;
import frc.team578.robot.commands.SwerveDriveCommand;
import frc.team578.robot.subsystems.ArmSubsystem;
import frc.team578.robot.subsystems.ElevatorSubsystem;
import frc.team578.robot.subsystems.GyroSubsystem;
import frc.team578.robot.subsystems.SwerveDriveSubsystem;
import frc.team578.robot.utils.PIDFinished;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Robot extends TimedRobot {

    private static final Logger log = LogManager.getLogger(Robot.class);

    // Operator Interface
    public static OI oi;

    // Subsystems
    public static frc.team578.robot.subsystems.SwerveDriveSubsystem swerveDriveSubsystem;
    public static frc.team578.robot.subsystems.GyroSubsystem gyroSubsystem;
    public static frc.team578.robot.subsystems.ArmSubsystem armSubsystem;
    public static frc.team578.robot.subsystems.CargoIntakeSubsystem cargoIntakeSubsystem;
    public static frc.team578.robot.subsystems.ElevatorSubsystem elevatorSubsystem;

    @Override
    public void robotInit() {

        try {

            log.info("Starting Robot Init");

            gyroSubsystem = new GyroSubsystem("gyro");
            gyroSubsystem.initialize();
            gyroSubsystem.setToZero();
            log.info("Gyro Subsystem Initialized");

            swerveDriveSubsystem = new SwerveDriveSubsystem();
            swerveDriveSubsystem.initialize();

            log.info("Swerve Drive Subsystem Initialized");



            armSubsystem = new ArmSubsystem();
            armSubsystem.initialize();
            log.info("Arm Subsystem Initialized");

            elevatorSubsystem = new ElevatorSubsystem();
            elevatorSubsystem.initialize();
            log.info("Elevator Subsystem Initialized");

            oi = new OI();
            oi.initialize();
            log.info("OI Subsystem Initialized");

        } catch (Throwable t) {
            log.error("Error In Robot Initialization : " + t.getMessage(), t);
            throw t;
        }

        log.info("Robot Init Complete");
    }

    @Override
    public void robotPeriodic() {

        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }

    @Override
    public void disabledPeriodic() {
        updateAllDashboards();
        Scheduler.getInstance().run();

    }


    @Override
    public void autonomousInit() {
        // TODO : Is this running in this method?

//        Robot.swerveDriveSubsystem.moveSteerTrueNorth();
//        Supplier<Double> supplier = Robot.swerveDriveSubsystem::getSteerErrorDerivitiveSum;
//        Predicate<Double> successTest = (x) -> x == 0;
//        PIDFinished pidFinished = new PIDFinished(10,3, supplier, successTest);
//        while (!pidFinished.isStable()) {
//
//        }
//        Robot.swerveDriveSubsystem.calibrateAllSteerEncoders();


//        SampleCommand sampleCommand = new SampleCommand();
//        sampleCommand.start();

//            CalibrateDrivesCommand calibrateDrivesCommand = new CalibrateDrivesCommand();
//            calibrateDrivesCommand.start();
    }

    @Override
    public void autonomousPeriodic() {

        updateAllDashboards();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        try {
            // Start the swerve drive command
            SwerveDriveCommand drive = new SwerveDriveCommand();
            drive.start();
        } catch (Throwable t) {
            log.error("Error In Robot teleopInit : " + t.getMessage(), t);
            throw t;
        }
    }

    @Override
    public void teleopPeriodic() {

        updateAllDashboards();
        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
        Scheduler.getInstance().run();
    }



    long lastUpdate = 0;
    public void updateAllDashboards() {
        long now = System.currentTimeMillis();
        if (now - lastUpdate > 100) {
            lastUpdate = now;
            Robot.swerveDriveSubsystem.updateDashboard();
            Robot.gyroSubsystem.updateDashboard();

        }
    }
}
