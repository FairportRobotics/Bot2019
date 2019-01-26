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
	// variables for talons
	private TalonSRX testTalon;

	private boolean firstEnabledPeriodic = true;
	private boolean firstDisabledPeriodic = true;
	private boolean firstAutonomousPeriodic = true;
	private boolean firstTestPeriodic = true;

	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";

    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

	private static Joystick joystick;
	
	private AHRS navx;

	private PowerDistributionPanel PDP;

	public void robotInit() {
		System.out.println("Turned robot on");

		m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
		m_chooser.addOption("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

		joystick = new Joystick(RobotMap.CONTROL_GAMEPAD_ID);

		testTalon = new TalonSRX(RobotMap.MAIN_TALON_ID);

		PDP = new PowerDistributionPanel(0);
	}

	public void disabledInit() {
		System.out.println("Disabled robot :sad face:");
	}

	public void autonomousInit() {
		System.out.println("Enabled robot in autonomous");

		m_autoSelected = m_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);

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
        switch (m_autoSelected) {
            case kDefaultAuto:
                // Put default auto code here
                testTalon.set(ControlMode.PercentOutput, .1);
				System.out.println("Sent from Default Auto");
				break;

            case kCustomAuto:
            	// put custom auto code here
				testTalon.set(ControlMode.PercentOutput, .5);
				System.out.println("Sent from Custom Auto");
				break;
	}

		if (firstAutonomousPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in autonomous mode. For now, it will only run once.");
			firstAutonomousPeriodic = false;
		}
	}

	public void teleopPeriodic() {
		double stick = joystick.getRawAxis(RobotMap.JOYSTICK_X_AXIS_ID);
		//testTalon.set(ControlMode.PercentOutput, stick); // do the funky dance

		SmartDashboard.putNumber("Joystick X value", stick);

		SmartDashboard.putData(PDP);

		SmartDashboard.putNumber("Talon Current Output (Amperage)", testTalon.getOutputCurrent());

	}


	public void testPeriodic() {
		if (firstTestPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in test mode. For now, it will only run once.");
			firstTestPeriodic = false;
		}
	}
}
