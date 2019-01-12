package team578.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class Robot {
	// variables for talons
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlave;
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlave;
	
	private AHRS navx;
}
