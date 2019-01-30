package frc.team578.robot;

import edu.wpi.first.wpilibj.*;
import java.lang.*;

public class Robot extends TimedRobot {

	// Joystick pointer
	private static Joystick joystick;

	/* DoubleSolenoid.Value list
		kOff is off (duh)
		kForward is extended
		kReverse is retracted
	*/
	private DoubleSolenoid testDouble; // piston pointer
	private boolean pistonExtended; // state of the piston, will be found on robot startup (!)

	public void robotInit() {
		System.out.println("Turned robot on");

		// assign pointers to their values
		testDouble = new DoubleSolenoid(RobotMap.DOUBLE_SOLENOID_ID, RobotMap.DS_FORWARD_CHANNEL_ID, RobotMap.DS_REVERSE_CHANNEL_ID);
		joystick = new Joystick(RobotMap.CONTROL_GAMEPAD_ID);
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
		pistonExtended = testDouble.get().equals(DoubleSolenoid.Value.kForward) ? true : false; // gets disabled on startup, no worries
	}

	public void teleopInit() {
		System.out.println("Enabled in teleop mode");
	}

	// long currentTime = System.currentTimeMillis();

	public void teleopPeriodic() {
		boolean button1Pressed = joystick.getRawButton(RobotMap.BUTTON_1_ID);
		if(button1Pressed) {
			if(pistonExtended) {
				testDouble.set(DoubleSolenoid.Value.kReverse);
				pistonExtended = false;
			} else if(!pistonExtended) {
				testDouble.set(DoubleSolenoid.Value.kForward);
				pistonExtended = true;
			}
		}
	}
}
