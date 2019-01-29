package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.*;

public class Robot extends TimedRobot {
	private static Joystick joystick;

	public void robotInit() {
		System.out.println("Turned robot on");


		joystick = new Joystick(RobotMap.CONTROL_GAMEPAD_ID);
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
	}


	public void teleopInit() {
		System.out.println("Enabled in teleop mode");
	}

	public void robotPeriodic() {

	}

	public void disabledPeriodic() {

	}

	public void teleopPeriodic() {
		double stick = joystick.getRawAxis(RobotMap.JOYSTICK_X_AXIS_ID);
	}
}
