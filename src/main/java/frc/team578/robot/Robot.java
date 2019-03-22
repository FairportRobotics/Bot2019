package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.*;

public class Robot extends TimedRobot {

	// Joystick pointer
	private static Joystick leftJoystick;
	private static Joystick rightJoystick;
	private static WPI_TalonSRX talon;
	private static ExpoScale es;


	public void robotInit() {

		talon = new WPI_TalonSRX(11);
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		es = new ExpoScale(0.1, 0);
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
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("Teleop Init");
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double jvl = leftJoystick.getY();
		double jvr = rightJoystick.getY();
		double sf = es.apply(jvl);
		System.err.println(jvl + " : " + sf);
		SmartDashboard.putNumber("raw", jvl);
		SmartDashboard.putNumber("scaled", sf);
		if(jvl != 0)
			talon.set(ControlMode.PercentOutput,sf);
		else
			talon.set(ControlMode.PercentOutput,jvr);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
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
