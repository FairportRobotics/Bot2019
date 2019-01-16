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
	
	private AHRS navx;

	public void robotInit() {
		System.out.println("turned on robot beep boop beep boop");
	}

	public void disabledInit() {
		System.out.println("turned off robot :sad face:");
	}

	public void autonomousInit() {
		System.out.println("im sorry dave, i cant do that");
	}

	public void teleopInit() {
		System.out.println("take the wheel");
	}

	public void testInit() {
		System.out.println("test init");
	}

	public void robotPeriodic() {
		super.robotPeriodic();
	}

	public void disabledPeriodic() {
		super.disabledPeriodic();
	}

	public void autonomousPeriodic() {
		super.autonomousPeriodic();
	}

	public void teleopPeriodic() {
		super.teleopPeriodic();
	}

	public void testPeriodic() {
		super.testPeriodic();
	}
}
