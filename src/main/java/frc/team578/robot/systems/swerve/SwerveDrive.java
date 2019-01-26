package frc.team578.robot.systems.swerve;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.team578.robot.RobotMap;
import frc.team578.robot.systems.interfaces.DashUpdate;
import frc.team578.robot.systems.swerve.math.CentricMode;
import frc.team578.robot.systems.swerve.math.SwerveDirective;
import frc.team578.robot.systems.swerve.math.SwerveMath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The main class for the SwerveDrive subsystem: This class handles all aspects
 * of controlling the swerve drive.
 */
public class SwerveDrive implements DashUpdate {

    private static final Logger log = LogManager.getLogger(SwerveDrive.class);

	// Enclosures 1-4 are the drive/steer combos
    private final TalonSwerveEnclosure swerveEnclosureFR;
    private final TalonSwerveEnclosure swerveEnclosureFL;
    private final TalonSwerveEnclosure swerveEnclosureBL;
    private final TalonSwerveEnclosure swerveEnclosureBR;
	private final SwerveMath swerveMath;


	public SwerveDrive(TalonSwerveEnclosure swerveEnclosureFL, TalonSwerveEnclosure swerveEnclosureFR,
					   TalonSwerveEnclosure swerveEnclosureBL, TalonSwerveEnclosure swerveEnclosureBR, double width, double length) {

		this.swerveEnclosureFR = swerveEnclosureFR;
		this.swerveEnclosureFL = swerveEnclosureFL;
		this.swerveEnclosureBL = swerveEnclosureBL;
		this.swerveEnclosureBR = swerveEnclosureBR;

		// instantiate the swerve library with a gyro provider using pigeon1
        this.swerveMath = new SwerveMath(width, length);

	}

	/**
	 * move Moves the robot based on 3 inputs - fwd (forward), str(strafe), and
	 * rcw(rotation clockwise) Inputs are between -1 and 1, with 1 being full power,
	 * -1 being full reverse, and 0 being neutral. The method uses gyro for field
	 * centric driving, if it is enabled.
	 *
	 * @param fwd
	 * @param str
	 * @param rcw
	 * @param gyroValue the value of the gyro input to be used by the calculation.
	 *                  Optional. Only used when the robot is in field-centric mode.
	 */
	public void move(double fwd, double str, double rcw, Double gyroValue) {
		// Get the move command calculated
		List<SwerveDirective> swerveDirectives = swerveMath.move(fwd, str, rcw, gyroValue);

		swerveEnclosureFR.move(swerveDirectives.get(0).getSpeed(), swerveDirectives.get(0).getAngle());
		swerveEnclosureFL.move(swerveDirectives.get(1).getSpeed(), swerveDirectives.get(1).getAngle());
		swerveEnclosureBL.move(swerveDirectives.get(2).getSpeed(), swerveDirectives.get(2).getAngle());
		swerveEnclosureBR.move(swerveDirectives.get(3).getSpeed(), swerveDirectives.get(3).getAngle());
	}

	public void stop() {
		swerveEnclosureFR.stop();
		swerveEnclosureFL.stop();
		swerveEnclosureBL.stop();
		swerveEnclosureBR.stop();
	}

	/**
	 * Change the centric-mode of the robot (this can be done dynamically any time
	 * and will affect the robot behavior from that point on)
	 */
	public void setCentricMode(CentricMode centricMode) {
		this.swerveMath.setCentricMode(centricMode);
	}

	public void setModeField() {
		this.swerveMath.setModeField();
	}
	
	public CentricMode getCentricMode() {
		return this.swerveMath.getCentricMode();
	}

	public void zeroAllSteerEncoders() {
		swerveEnclosureFR.zeroSteerEncoder();
		swerveEnclosureFL.zeroSteerEncoder();
		swerveEnclosureBL.zeroSteerEncoder();
		swerveEnclosureBR.zeroSteerEncoder();
	}


	/*
	Putting all of the creation code below.
	 */

	public static SwerveDrive create() {

		WPI_TalonSRX frontLeftDriveTalon;
		WPI_TalonSRX frontLeftSwerveTalon;
		WPI_TalonSRX frontRightDriveTalon;
		WPI_TalonSRX frontRightSwerveTalon;
		WPI_TalonSRX backLeftDriveTalon;
		WPI_TalonSRX backRightDriveTalon;
		WPI_TalonSRX backLeftSwerveTalon;
		WPI_TalonSRX backRightSwerveTalon;

		frontLeftDriveTalon = SwerveDrive.createDriveTalon(RobotMap.FRONT_LEFT_DRIVE_TALON_ID,
				SwerveConstants.FRONT_LEFT_REVERSE_DRIVE);
		frontRightDriveTalon = SwerveDrive.createDriveTalon(RobotMap.FRONT_RIGHT_DRIVE_TALON_ID,
				SwerveConstants.FRONT_RIGHT_REVERSE_DRIVE);
		backLeftDriveTalon = SwerveDrive.createDriveTalon(RobotMap.BACK_LEFT_DRIVE_TALON_ID,
				SwerveConstants.BACK_LEFT_REVERSE_DRIVE);
		backRightDriveTalon = SwerveDrive.createDriveTalon(RobotMap.BACK_RIGHT_DRIVE_TALON_ID,
				SwerveConstants.BACK_RIGHT_REVERSE_DRIVE);

		frontLeftSwerveTalon = SwerveDrive.createSteerTalon(RobotMap.FRONT_LEFT_ROTATE_TALON_ID,
				SwerveConstants.FRONT_LEFT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
				SwerveConstants.turn_kIZone);
		frontRightSwerveTalon = SwerveDrive.createSteerTalon(RobotMap.FRONT_RIGHT_ROTATE_TALON_ID,
				SwerveConstants.FRONT_RIGHT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
				SwerveConstants.turn_kIZone);
		backLeftSwerveTalon = SwerveDrive.createSteerTalon(RobotMap.BACK_LEFT_ROTATE_TALON_ID,
				SwerveConstants.BACK_LEFT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
				SwerveConstants.turn_kIZone);
		backRightSwerveTalon = SwerveDrive.createSteerTalon(RobotMap.BACK_RIGHT_ROTATE_TALON_ID,
				SwerveConstants.BACK_RIGHT_REVERSE_TURN, SwerveConstants.turn_kP, SwerveConstants.turn_kI, SwerveConstants.turn_kD, SwerveConstants.turn_kF,
				SwerveConstants.turn_kIZone);

		TalonSwerveEnclosure frontLeft;
		TalonSwerveEnclosure frontRight;
		TalonSwerveEnclosure backLeft;
		TalonSwerveEnclosure backRight;

		frontLeft = new TalonSwerveEnclosure("fl", frontLeftDriveTalon, frontLeftSwerveTalon);
		frontRight = new TalonSwerveEnclosure("fr", frontRightDriveTalon,
				frontRightSwerveTalon);
		backLeft = new TalonSwerveEnclosure("bl", backLeftDriveTalon, backLeftSwerveTalon);
		backRight = new TalonSwerveEnclosure("br", backRightDriveTalon, backRightSwerveTalon);

		return new SwerveDrive(frontLeft, frontRight, backLeft, backRight, SwerveConstants.ROBOT_WIDTH, SwerveConstants.ROBOT_LENGTH);
	}

	private static WPI_TalonSRX createDriveTalon(int talonID, boolean revMotor) {
		WPI_TalonSRX talon = new WPI_TalonSRX(talonID);
		talon.setInverted(revMotor);
//        talon.configSelectedFeedbackSensor(FeedbackDevice.None, 0, 0);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, SwerveConstants.TIMEOUT_MS);
		talon.set(ControlMode.PercentOutput, 0);
		return talon;
	}

	private static WPI_TalonSRX createSteerTalon(int talonID, boolean revMotor, double pCoeff, double iCoeff, double dCoeff,
												 double fCoeff, int iZone) {

		WPI_TalonSRX talon = new WPI_TalonSRX(talonID);

		talon.configSelectedFeedbackSensor(FeedbackDevice.Analog, SwerveConstants.PIDLOOP_IDX, SwerveConstants.TIMEOUT_MS);
		talon.configSetParameter(ParamEnum.eFeedbackNotContinuous, 0, 0, 0, SwerveConstants.TIMEOUT_MS); // wrap the position (1023 -> 0)

		talon.selectProfileSlot(SwerveConstants.PROFILE_SLOT, SwerveConstants.PIDLOOP_IDX);
		talon.config_kP(SwerveConstants.PROFILE_SLOT, pCoeff, SwerveConstants.TIMEOUT_MS);
		talon.config_kI(SwerveConstants.PROFILE_SLOT, iCoeff, SwerveConstants.TIMEOUT_MS);
		talon.config_kD(SwerveConstants.PROFILE_SLOT, dCoeff, SwerveConstants.TIMEOUT_MS);
		talon.config_kF(SwerveConstants.PROFILE_SLOT, fCoeff, SwerveConstants.TIMEOUT_MS);
		talon.config_IntegralZone(SwerveConstants.PROFILE_SLOT, iZone, SwerveConstants.TIMEOUT_MS);

		talon.configNominalOutputForward(0, SwerveConstants.TIMEOUT_MS);
		talon.configNominalOutputReverse(0, SwerveConstants.TIMEOUT_MS);

		talon.configPeakOutputForward(1, SwerveConstants.TIMEOUT_MS);
		talon.configPeakOutputReverse(-1, SwerveConstants.TIMEOUT_MS);

		talon.setInverted(revMotor);
		talon.setSensorPhase(SwerveConstants.ALIGNED_TURN_SENSOR);

//		_talon.configPeakCurrentLimit(50, TIMEOUT_MS);
//		_talon.enableCurrentLimit(true);

		return talon;
	}


	@Override
	public void dashboardUpdate() {
		swerveEnclosureFL.dashboardUpdate();
		swerveEnclosureFR.dashboardUpdate();
		swerveEnclosureBL.dashboardUpdate();
		swerveEnclosureBR.dashboardUpdate();
	}

	/*
	This tells the talon to reset its encoders so that true north is where you are now minus some offse to where there wheel north is actually.
	(i.e. we know north(0) is 20, i'm at 90 right now, so we really want to reset the encoder to 70 before we can start moving)
	This will not turn the motor at all.
	Can be used if we don't want the wheels moving on startup.
	 */
	public void setSteerEncoderOffset() {
		swerveEnclosureFL.setSteerEncPosition(swerveEnclosureFL.getSteerEncPosition() - SwerveConstants.FRONT_LEFT_TRUE_NORTH_ENC_POS);
		swerveEnclosureFR.setSteerEncPosition(swerveEnclosureFR.getSteerEncPosition() - SwerveConstants.FRONT_RIGHT_TRUE_NORTH_ENC_POS);
		swerveEnclosureBL.setSteerEncPosition(swerveEnclosureBL.getSteerEncPosition() - SwerveConstants.BACK_LEFT_TRUE_NORTH_ENC_POS);
		swerveEnclosureBR.setSteerEncPosition(swerveEnclosureBR.getSteerEncPosition() - SwerveConstants.BACK_RIGHT_TRUE_NORTH_ENC_POS);
	}

	/*
	Wherever the wheel is right now, move it to where we think true north should be for that wheel so we can see where north is.
	 */
	public void moveSteerTrueNorth() {
		swerveEnclosureFL.moveSteerToEncoderPosition(SwerveConstants.FRONT_LEFT_TRUE_NORTH_ENC_POS);
		swerveEnclosureFR.moveSteerToEncoderPosition(SwerveConstants.FRONT_RIGHT_TRUE_NORTH_ENC_POS);
		swerveEnclosureBL.moveSteerToEncoderPosition(SwerveConstants.BACK_LEFT_TRUE_NORTH_ENC_POS);
		swerveEnclosureBR.moveSteerToEncoderPosition(SwerveConstants.BACK_RIGHT_TRUE_NORTH_ENC_POS);
	}
}
