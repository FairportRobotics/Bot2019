package frc.team578.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.lang.*;

public class Robot extends TimedRobot {

	// Joystick pointer
	private static Joystick joystick;

	public void robotInit() {
		System.out.println("Robot Init");
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
		System.out.println("Disabled Init");
	}

	@Override
	public void disabledPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("Teleop Init");
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println("Auto Init");
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		System.out.println("Test Init");
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}

	public void updateDashboard() {

	}
}
