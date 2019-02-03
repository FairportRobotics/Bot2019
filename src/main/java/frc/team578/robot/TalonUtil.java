package frc.team578.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonUtil {

    public static final int TIMEOUT_MS = 0; // set to zero if skipping confirmation
    public static final int PIDLOOP_IDX = 0; // set to zero if primary loop
    public static final int PROFILE_SLOT = 0;
    public static final boolean ALIGNED_SENSOR = false; // encoder polarity
    public static final double turn_kP = 18;
    public static final double turn_kI = 0.0;
    public static final double turn_kD = 0.001;
    public static final double turn_kF = 0.0;
    public static final int turn_kIZone = 0;
    public static final int MAX_ENC_VAL = 1024;

    public static WPI_TalonSRX createPIDTalon(int talonID, boolean revMotor, double pCoeff, double iCoeff, double dCoeff,
                                                double fCoeff, int iZone) {

        WPI_TalonSRX talon = new WPI_TalonSRX(talonID);
        talon.configFactoryDefault();

        talon.configSelectedFeedbackSensor(FeedbackDevice.Analog, PIDLOOP_IDX, TIMEOUT_MS);
        talon.configSetParameter(ParamEnum.eFeedbackNotContinuous, 0, 0, 0, TIMEOUT_MS); // wrap the position (1023 -> 0)

        talon.selectProfileSlot(PROFILE_SLOT, PIDLOOP_IDX);
        talon.config_kP(PROFILE_SLOT, pCoeff, TIMEOUT_MS);
        talon.config_kI(PROFILE_SLOT, iCoeff, TIMEOUT_MS);
        talon.config_kD(PROFILE_SLOT, dCoeff, TIMEOUT_MS);
        talon.config_kF(PROFILE_SLOT, fCoeff, TIMEOUT_MS);
        talon.config_IntegralZone(PROFILE_SLOT, iZone, TIMEOUT_MS);

        talon.configNominalOutputForward(0, TIMEOUT_MS);
        talon.configNominalOutputReverse(0, TIMEOUT_MS);

        talon.configPeakOutputForward(1, TIMEOUT_MS);
        talon.configPeakOutputReverse(-1, TIMEOUT_MS);

        talon.setInverted(revMotor);
        talon.setSensorPhase(ALIGNED_SENSOR);


//		_talon.configPeakCurrentLimit(50, TIMEOUT_MS);
//		_talon.enableCurrentLimit(true);

        return talon;
    }
}
