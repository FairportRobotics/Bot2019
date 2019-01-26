package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.*;


import frc.team578.robot.RobotMap;

public class Robot extends TimedRobot {
	// variables for talons
	private TalonSRX testTalon;

	private boolean firstEnabledPeriodic = true;
	private boolean firstDisabledPeriodic = true;
	private boolean firstAutonomousPeriodic = true;
	private boolean firstTeleopPeriodic = true;
	private boolean firstTestPeriodic = true;

	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
    private static final String kTestAuto = "test";
    private static final String kTesticlesAuto = "testicles";

    private String m_autoSelected;
	private String t_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
	private final SendableChooser<String> t_chooser = new SendableChooser<>();



	private static Joystick joystick;
	
	private AHRS navx;

	private PowerDistributionPanel PDP;

	public void robotInit() {
		System.out.println("Turned robot on");

		m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
		m_chooser.addOption("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

        t_chooser.setDefaultOption("testicles", kTesticlesAuto);
        t_chooser.addOption("test", kTestAuto);
        SmartDashboard.putData("Test choices", t_chooser);


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
		t_autoSelected = t_chooser.getSelected();

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
                // Put custom auto code here
                testTalon.set(ControlMode.PercentOutput, 360); //no idea what this means
				System.out.println("Sent from Default Auto");

				break;
            case kCustomAuto:
				testTalon.set(ControlMode.PercentOutput, 20); //no idea what this means
				System.out.println("Sent from Custom Auto");
				break;
            default:
                // Put default auto code here


                break;
	}

		if (firstAutonomousPeriodic) {
			System.out.println("This is the periodic message when the robot is enabled in autonomous mode. For now, it will only run once.");
			firstAutonomousPeriodic = false;
		}
	}

	public void teleopPeriodic() {


		double stick = joystick.getRawAxis(RobotMap.JOYSTICK_X_AXIS_ID);

		testTalon.set(ControlMode.PercentOutput, stick); // do the funky dance


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
