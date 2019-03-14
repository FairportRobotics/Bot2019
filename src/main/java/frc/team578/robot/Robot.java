package frc.team578.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.lang.*;

public class Robot extends TimedRobot {

	private static Gamepad gp;

	private DoubleSolenoid testDouble;
	private JoystickButton buttonA;
	private JoystickButton buttonB;
	private JoystickButton buttonX;

//	private Solenoid testSingle;

	public void robotInit() {
		System.out.println("Turned robot on");

		int PCM = 40;
		int FW_CHANNEL = 0;
		int REV_CHANNEL = 1;

		testDouble = new DoubleSolenoid(PCM,FW_CHANNEL,REV_CHANNEL);
		gp = new Gamepad(0);
		buttonA = gp.getButtonA();
		buttonB = gp.getButtonB();
		buttonX = gp.getButtonX();

	}

	public void disabledInit() {
		System.out.println("Disabled robot");
	}

	public void teleopInit() {
		System.err.println("Enabled in teleop mode");
	}

	public void teleopPeriodic() {
		if (buttonA.get()) {
			System.err.println("DS Forward");
			testDouble.set(DoubleSolenoid.Value.kForward);
		} else if (buttonB.get()) {
			System.err.println("DS Reverse");
			testDouble.set(DoubleSolenoid.Value.kReverse);
		} else if (buttonX.get()) {
			System.err.println("DS Off");
			testDouble.set(DoubleSolenoid.Value.kOff);
		}
	}
}
