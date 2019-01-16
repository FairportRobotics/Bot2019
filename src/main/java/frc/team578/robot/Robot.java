package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
	// variables for talons
	private TalonSRX rightMaster;
	private TalonSRX rightSlave;
	private TalonSRX leftMaster;
	private TalonSRX leftSlave;

	private boolean firstEnabledPeriodic = true;
	private boolean firstDisabledPeriodic = true;
	private boolean firstAutonomousPeriodic = true;
	private boolean firstTeleopPeriodic = true;
	private boolean firstTestPeriodic = true;
	
	private AHRS navx;

	public void robotInit() {
		System.out.println("Enabled robot");
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
	}

	public void autonomousInit() {
		System.out.println("Enabled robot in autonomous");
	}

	public void teleopInit() {
		System.out.println("Enabled in teleop mode");
	}

	public void testInit() {
		System.out.println("Enabled in test mode");
	}

	public void robotPeriodic() {
		if (firstEnabledPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled. For now, it will only run once.");
			firstEnabledPeriodic = false;
		}
	}

	public void disabledPeriodic() {
		if (firstDisabledPeriodic) {
			System.out.println("This is the periodic message when the robot is disabled. For now, it will only run once.");
			firstDisabledPeriodic = false;
		}
	}

	public void autonomousPeriodic() {
		if (firstAutonomousPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in autonomous mode. For now, it will only run once.");
			firstAutonomousPeriodic = false;
		}
	}

	public void teleopPeriodic() {
		if (firstTeleopPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in teleop mode. For now, it will only run once.");
			firstTeleopPeriodic = false;
		}
	}

	public void testPeriodic() {
		if (firstTestPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in test mode. For now, it will only run once.");
			firstTestPeriodic = false;
		}
	}
}
