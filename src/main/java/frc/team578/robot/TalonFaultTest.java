package frc.team578.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;

public class TalonFaultTest extends TimedRobot {
	WPI_TalonSRX _talon = new WPI_TalonSRX(13); /* make a Talon */
	Joystick _joystick = new Joystick(0); /* make a joystick */
	Faults _faults = new Faults(); /* temp to fill with latest faults */

	@Override
	public void teleopInit() {
		/* factory default values */
		_talon.configFactoryDefault();

		_talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);
		_talon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);

		/*
		 * choose whatever you want so "positive" values moves mechanism forward,
		 * upwards, outward, etc...
		 *
		 * Note that you can set this to whatever you want, but this will not fix motor
		 * output direction vs sensor direction.
		 */
		_talon.setInverted(false);

		/*
		 * flip value so that motor output and sensor velocity are the same polarity. Do
		 * this before closed-looping
		 */
		_talon.setSensorPhase(false); // <<<<<< Adjust this
	}

	@Override
	public void teleopPeriodic() {
		double xSpeed = _joystick.getRawAxis(1) * -1; // make forward stick positive

		/* update motor controller */
		_talon.set(ControlMode.PercentOutput, xSpeed);
		/* check our live faults */
		_talon.getFaults(_faults);
		/* hold down btn1 to print stick values */
		if (_joystick.getRawButton(1)) {
			System.out.println("Sensor Vel:" + _talon.getSelectedSensorVelocity());
			System.out.println("Sensor Pos:" + _talon.getSelectedSensorPosition());
			System.out.println("Out %" + _talon.getMotorOutputPercent());
			System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);
		}
	}
}
