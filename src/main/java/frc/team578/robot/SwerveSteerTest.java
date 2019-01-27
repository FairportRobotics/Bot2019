package frc.team578.robot;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveSteerTest extends TimedRobot {

    public static final boolean REVERSE_TURN = true;
    public static final int ROTATE_TALON_ID = 11;
    public static final int TIMEOUT_MS = 0; // set to zero if skipping confirmation
    public static final int PIDLOOP_IDX = 0; // set to zero if primary loop
    public static final int PROFILE_SLOT = 0;
    public static final boolean ALIGNED_TURN_SENSOR = false; // encoder polarity
    public static final double turn_kP = 18;
    public static final double turn_kI = 0.0;
    public static final double turn_kD = 0.001;
    public static final double turn_kF = 0.0;
    public static final int turn_kIZone = 0;
    public static final double MAX_ENC_VAL = 1024;
    int target;


    WPI_TalonSRX _talon;
    Joystick _joystick = new Joystick(0); /* make a joystick */
    Faults _faults = new Faults(); /* temp to fill with latest faults */

    @Override
    public void robotInit() {



        _talon = createSteerTalon(ROTATE_TALON_ID,
                REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);

        updateTarget();
    }

    @Override
    public void teleopInit() {
        updateTarget();
    }

    void updateTarget() {
        int pos = _talon.getSelectedSensorPosition();
        int round = (1024 * (pos / 1024));
        target = 420 + round;
    }

    @Override
    public void teleopPeriodic() {

        double xSpeed = _joystick.getRawAxis(1) * -1; // make forward stick positive

//        int wraps = _talon.getSelectedSensorPosition() / 1024;

        /* update motor controller */
        _talon.set(ControlMode.Position, target);

//        _talon.set(ControlMode.PercentOutput, xSpeed);

        /* check our live faults */
        _talon.getFaults(_faults);

        updateSD();

    }



    @Override
    public void disabledPeriodic() {
        updateSD();
        updateTarget();
    }


    /*
    17.1.2. Analog Potentiometer
    When measuring the position of a 3.3V Analog Potentiometer, the position is measured as a 10
    bit ADC value. A value of 1023 corresponds to 3.3V. A value of 0 corresponds to 0.0V.

    Like 3.3V Analog Potentiometers, the 10 bit ADC is used to scale [0 V, 3.3 V] => [0, 1023].
    However when the Analog Encoder “wraps around” from 1023 to 0, the Analog Position will
    continue to 1024. In other words, the sensor is treated as “continuous”.

    https://www.ctr-electronics.com/downloads/pdf/Talon%20SRX%20Software%20Reference%20Manual-4.pdf

     */
    private WPI_TalonSRX createSteerTalon(int talonID, boolean revMotor, double pCoeff, double iCoeff, double dCoeff,
                                          double fCoeff, int iZone) {

        WPI_TalonSRX talon = new WPI_TalonSRX(talonID);


        /* factory default values */
//		talon.configFactoryDefault();

        /*
         * choose whatever you want so "positive" values moves mechanism forward,
         * upwards, outward, etc...
         *
         * Note that you can set this to whatever you want, but this will not fix motor
         * output direction vs sensor direction.
         */
//		talon.setInverted(false);

        /*
         * flip value so that motor output and sensor velocity are the same polarity. Do
         * this before closed-looping
         */
//		talon.setSensorPhase(false); // <<<<<< Adjust this


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

        talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);
        talon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);

        talon.setInverted(revMotor);
        talon.setSensorPhase(ALIGNED_TURN_SENSOR);

        // _talon.getSensorCollection().setAnalogPosition()

//		talon.configPeakCurrentLimit(50, TIMEOUT_MS);
//		talon.enableCurrentLimit(true);

        return talon;
    }

    public void updateSD() {

        SmartDashboard.putData("Talon Data", _talon);
        SmartDashboard.putNumber("Sensor Vel:", _talon.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Sensor Pos:", _talon.getSelectedSensorPosition());
        SmartDashboard.putNumber("Analog In", _talon.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("Analog In Raw", _talon.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber("Out %", _talon.getMotorOutputPercent());
        SmartDashboard.putBoolean("Any Faults:", _faults.hasAnyFault());
        SmartDashboard.putBoolean("Out Of Phase:", _faults.SensorOutOfPhase);
        SmartDashboard.putNumber("target:", target);


    }
}
