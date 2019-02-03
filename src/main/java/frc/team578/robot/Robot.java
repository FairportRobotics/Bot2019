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
	private static Joystick joystick;
	private static WPI_TalonSRX talon;

	private static final int l0 = 0;
	private static final int l1 = 100;
	private static final int l2 = 500;
	private static final int l3 = 1000;
	private static final int l4 = 1500;
	private static final int l5 = 10000;
	private int m_autoSelected;
	private final SendableChooser<Integer> m_chooser = new SendableChooser<>();

	private DigitalInput lim0;
	private DigitalInput lim1;

	PowerDistributionPanel powerDistributionPanel;

	public void robotInit() {
		System.out.println("Robot Init");

		powerDistributionPanel = new PowerDistributionPanel(0);

		lim0 = new DigitalInput(0);
		lim1 = new DigitalInput(1);

		m_chooser.setDefaultOption("l0", l0);
		m_chooser.addOption("l1", l1);
		m_chooser.addOption("l2", l2);
		m_chooser.addOption("l3", l3);
		m_chooser.addOption("l4", l4);
		m_chooser.addOption("l5", l5);

		SmartDashboard.putData("Auto choices", m_chooser);

		int talonID = 7;
		boolean revMotor = false;
		double pCoeff = 10;
		double iCoeff = 0;
		double dCoeff = 0;
		double fCoeff = 0;
		int iZone = 0;

		talon = TalonUtil.createPIDTalon(talonID, revMotor, pCoeff, iCoeff, dCoeff, fCoeff, iZone);

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
		m_autoSelected = m_chooser.getSelected();
		// m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		talon.set(ControlMode.Position,m_autoSelected);
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
		SmartDashboard.putData("talon", talon);
		SmartDashboard.putNumber("talon.encpos", talon.getSelectedSensorPosition());
		if (talon.getControlMode() == ControlMode.Position) {
			SmartDashboard.putNumber("talon.cle", talon.getClosedLoopError());
			SmartDashboard.putNumber("talon.clt", talon.getClosedLoopTarget());
			SmartDashboard.putNumber("talon.errd", talon.getErrorDerivative());
			SmartDashboard.putBoolean("talon.errd_t", (talon.getErrorDerivative() == 0));
		}
		SmartDashboard.putBoolean("lim0",lim0.get());
		SmartDashboard.putBoolean("lim1",lim1.get());
	}
}
