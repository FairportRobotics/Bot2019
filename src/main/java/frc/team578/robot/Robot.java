package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team578.robot.RobotMap;

public class Robot extends TimedRobot {
	// variables for talons
	private TalonSRX testTalon;

	private boolean firstEnabledPeriodic = true;
	private boolean firstDisabledPeriodic = true;
	private boolean firstAutonomousPeriodic = true;
	private boolean firstTeleopPeriodic = true;
	private boolean firstTestPeriodic = true;

	private static Joystick joystick;
	
	private AHRS navx;

	public void robotInit() {
		System.out.println("Turned robot on");

		joystick = new Joystick(RobotMap.CONTROL_GAMEPAD_ID);

		testTalon = new TalonSRX(RobotMap.MAIN_TALON_ID);
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
	}

	public void autonomousInit() {
		System.out.println("Enabled robot in autonomous");
	}

	public void teleopInit() {
		System.out.println("Enabled in teleop mode");

		testTalon.set(ControlMode.PercentOutput, 0);
		testTalon.setInverted(true);
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
		double stick = joystick.getRawAxis(RobotMap.JOYSTICK_Y_AXIS_ID);

		testTalon.set(ControlMode.PercentOutput, stick); // do the funky dance

		System.out.println("Joystick Y Axis Position:" + (stick * -1));
	}

	public void testPeriodic() {
		if (firstTestPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in test mode. For now, it will only run once.");
			firstTestPeriodic = false;
		}
	}
}
