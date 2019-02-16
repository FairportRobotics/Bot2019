package frc.team578.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team578.robot.commands.SwerveDriveCommand;
import frc.team578.robot.commands.TankDriveCommand;
import frc.team578.robot.subsystems.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Robot extends TimedRobot {

    private static final Logger log = LogManager.getLogger(Robot.class);

    // Operator Interface
    public static OI oi;

    // Subsystems
    public static SwerveDriveSubsystem swerveDriveSubsystem;
    public static TankDriveSubsystem tankDriveSubsystem;
    public static GyroSubsystem gyroSubsystem;
    public static ArmSubsystem armSubsystem;
    public static CargoIntakeSubsystem cargoIntakeSubsystem;
    public static ElevatorSubsystem elevatorSubsystem;
    public static UsbCamera cam;
    public static ClimberSubsystem climberSubsystem;

    public static final boolean useSwerveDrive = true;


    @Override
    public void robotInit() {

        try {

            log.info("Starting Robot Init");

            gyroSubsystem = new GyroSubsystem("gyro");
            gyroSubsystem.initialize();
            log.info("Gyro Subsystem Initialized");

            if (useSwerveDrive) {
                swerveDriveSubsystem = new SwerveDriveSubsystem();
                swerveDriveSubsystem.initialize();
                log.info("Swerve Drive Subsystem Initialized");
            } else {
                tankDriveSubsystem = new TankDriveSubsystem();
                tankDriveSubsystem.initialize();
                log.info("Tank Drive Subsystem Initialized");
            }

            climberSubsystem = new ClimberSubsystem();
            climberSubsystem.initialize();
            log.info("Climber Subsystem Initialized");

//            armSubsystem = new ArmSubsystem();
//            armSubsystem.initialize();
//            log.info("Arm Subsystem Initialized");
//
//            elevatorSubsystem = new ElevatorSubsystem();
//            elevatorSubsystem.initialize();
//            log.info("Elevator Subsystem Initialized");

			cam = CameraServer.getInstance().startAutomaticCapture();
			// cam.setResolution(100, 75);
			// cam.setFPS(-1);
			log.info("Initialize Camera");

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

        if (useSwerveDrive) {
//            CalibrateDrivesCommand calibrateDrivesCommand = new CalibrateDrivesCommand();
//            calibrateDrivesCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {

        updateAllDashboards();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        try {
            // Start the drive command
            if (useSwerveDrive) {
                SwerveDriveCommand drive = new SwerveDriveCommand();
                drive.start();
            } else {
                TankDriveCommand drive = new TankDriveCommand();
                drive.start();
            }
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


    public void updateAllDashboards() {
        if (useSwerveDrive) {
            Robot.swerveDriveSubsystem.updateDashboard();
        } else {
            Robot.tankDriveSubsystem.updateDashboard();
        }
        Robot.gyroSubsystem.updateDashboard();
    }
}
