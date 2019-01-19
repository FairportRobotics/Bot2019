package frc.team578.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team578.robot.commands.SwerveDriveCommand;
import frc.team578.robot.systems.GyroSubsystem;
import frc.team578.robot.systems.SwerveDriveSubsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Robot extends TimedRobot {

	Logger log = LogManager.getRootLogger();

	// Operator Interface
	public static OI oi;

	// Subsystems
	public static SwerveDriveSubsystem swerveDriveSubsystem;
	public static GyroSubsystem gyroSubsystem;

	@Override
	public void robotInit() {

		try {

			log.info("Starting Robot Init");

			gyroSubsystem = new GyroSubsystem();
			gyroSubsystem.initialize();
			log.info("Gyro Subsystem Initialized");


			swerveDriveSubsystem = new SwerveDriveSubsystem();
			swerveDriveSubsystem.initialize();
			log.info("Swerve Subsystem Initialized");


			oi = new OI();
			oi.initialize();
			log.info("OI Subsystem Initialized");

		} catch (Throwable e) {
			log.error("Error In Robot Initialization : "+ e.getMessage(), e);
			throw e;
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

			SwerveDriveCommand drive = new SwerveDriveCommand();
			drive.start();


		} catch (Throwable t) {

		}
	}

	@Override
	public void testInit() {
		super.testInit();
	}

	@Override
	public void robotPeriodic() {
		super.robotPeriodic();
	}

	@Override
	public void disabledPeriodic() {
		super.disabledPeriodic();
	}

	@Override
	public void autonomousPeriodic() {
		super.autonomousPeriodic();
	}

	@Override
	public void teleopPeriodic() {
		super.teleopPeriodic();
	}

	@Override
	public void testPeriodic() {
		super.testPeriodic();
	}
}
