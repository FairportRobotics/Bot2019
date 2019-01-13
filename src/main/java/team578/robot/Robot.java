package team578.robot;

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

	@Override
	public void robotInit() {
		super.robotInit();
	}

	@Override
	public void disabledInit() {
		super.disabledInit();
	}

	@Override
	public void autonomousInit() {
		super.autonomousInit();
	}

	@Override
	public void teleopInit() {
		super.teleopInit();
	}

	@Override
	public void testInit() {
		super.testInit();
	}

	@Override
	public void robotPeriodic() {
		super.robotPeriodic();
	}

	@Override
	public void disabledPeriodic() {
		super.disabledPeriodic();
	}

	@Override
	public void autonomousPeriodic() {
		super.autonomousPeriodic();
	}

	@Override
	public void teleopPeriodic() {
		super.teleopPeriodic();
	}

	@Override
	public void testPeriodic() {
		super.testPeriodic();
	}
}
