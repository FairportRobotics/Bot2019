package frc.team578.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team578.robot.commands.SwerveDriveCommand;
import frc.team578.robot.systems.GyroSubsystem;
import frc.team578.robot.systems.SwerveDriveSubsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Robot extends TimedRobot {

    private static final Logger log = LogManager.getLogger(Robot.class);

	// Operator Interface
	public static OI oi;

	// Subsystems
	public static SwerveDriveSubsystem swerveDriveSubsystem;
	public static GyroSubsystem gyroSubsystem;

	@Override
	public void robotInit() {

		try {

			log.info("Starting Robot Init");

			gyroSubsystem = new GyroSubsystem("gyro");
			gyroSubsystem.initialize();
			log.info("Gyro Subsystem Initialized");


			swerveDriveSubsystem = new SwerveDriveSubsystem();
			swerveDriveSubsystem.initialize();
			log.info("Swerve Subsystem Initialized");


			oi = new OI();
			oi.initialize();
			log.info("OI Subsystem Initialized");

		} catch (Throwable t) {
			log.error("Error In Robot Initialization : " + t.getMessage(), t);
			throw t;
		}


	}

	@Override
	public void disabledInit() {
		super.disabledInit();
	}

	@Override
	public void autonomousInit() {
		super.autonomousInit();
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
	public void testInit() {
		swerveDriveSubsystem.moveSteerTrueNorth();
		swerveDriveSubsystem.zeroAllSteerEncoders();
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledPeriodic() {
		updateAllDashboards();
	}

	@Override
	public void autonomousPeriodic() {

		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {

		updateAllDashboards();

		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}

	public void updateAllDashboards() {
		Robot.swerveDriveSubsystem.dashboardUpdate();
		Robot.gyroSubsystem.dashboardUpdate();
	}
}
