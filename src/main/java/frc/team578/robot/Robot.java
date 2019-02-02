package frc.team578.robot;

import edu.wpi.first.wpilibj.*;
import java.lang.*;

public class Robot extends TimedRobot {

	// Joystick pointer
	private static Joystick joystick;

	public void robotInit() {
		System.out.println("Turned robot on");
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
	}

	public void teleopInit() {
		System.out.println("Enabled in teleop mode");
	}

	public void teleopPeriodic() {
		
	}
}
