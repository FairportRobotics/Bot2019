package frc.team578.robot.test;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.subsystems.swerve.SwerveConstants;
import frc.team578.robot.utils.Gamepad;
import frc.team578.robot.utils.PIDFinished;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SwerveSteerTest extends TimedRobot {

    private static final Logger log = LogManager.getLogger(SwerveSteerTest.class);

//    public static final boolean REVERSE_TURN = true;

    //    public static final int TIMEOUT_MS = 0; // set to zero if skipping confirmation
//    public static final int PIDLOOP_IDX = 0; // set to zero if primary loop
//    public static final int PROFILE_SLOT = 0;
//    public static final boolean ALIGNED_TURN_SENSOR = false; // encoder polarity
    public static final double turn_kP = 8;
    public static final double turn_kI = 0.0;
    public static final double turn_kD = 0.0;
    public static final double turn_kF = 0.0;
    public static final int turn_kIZone = 0;
//    public static final double MAX_ENC_VAL = 1024;
//    int target;
//    boolean finished = false;

    private final SendableChooser<Integer> m_chooser = new SendableChooser<>();
    private static final int l0 = 0;
    private static final int l1 = 100;
    private static final int l2 = 500;
    private static final int l3 = 1000;
    private static final int l4 = 1500;
    private static final int l5 = 10000;
    private static final int l6 = -500;
    private static final int l7 = -1000;
    private int m_autoSelected;


    WPI_TalonSRX fl_talon;
    WPI_TalonSRX fr_talon;
    WPI_TalonSRX bl_talon;
    WPI_TalonSRX br_talon;
    Gamepad gamepad = new Gamepad(0);
    JoystickButton buttonA = gamepad.getButtonA();
    Supplier<Integer> supplier = () -> Math.abs(fl_talon.getClosedLoopError()) + Math.abs(fr_talon.getClosedLoopError()) + Math.abs(bl_talon.getClosedLoopError()) + Math.abs(br_talon.getClosedLoopError());
    Predicate<Integer> successTest = (x) -> x < 30;
    PIDFinished<Integer> pidFinished = new PIDFinished(50, 3, supplier, successTest);

    int fl_araw_known_pos = 535;
    int fl_sens_tn;
    int fr_araw_known_pos = 561;
    int fr_sens_tn;
    int bl_araw_known_pos = 244;
    int bl_sens_tn;
    int br_araw_known_pos = 681;
    int br_sens_tn;


//    Faults _faults = new Faults(); /* temp to fill with latest faults */


    @Override
    public void robotInit() {


        m_chooser.setDefaultOption("" + l0, l0);
        m_chooser.addOption("" + l1, l1);
        m_chooser.addOption("" + l2, l2);
        m_chooser.addOption("" + l3, l3);
        m_chooser.addOption("" + l4, l4);
        m_chooser.addOption("" + l5, l5);
        m_chooser.addOption("" + l6, l6);
        m_chooser.addOption("" + l7, l7);
        SmartDashboard.putData("Auto choices", m_chooser);


//        buttonA.whenPressed(new CalibrateDrivesCommand(   fl_talon,
//         fr_talon,
//         bl_talon,
//         br_talon));


        fl_talon = createSteerTalon("t_fl", 11,
                SwerveConstants.FRONT_LEFT_REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);
        fr_talon = TestUtils.createSteerTalon("t_fr", 12,
                SwerveConstants.FRONT_RIGHT_REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);
        bl_talon = TestUtils.createSteerTalon("t_bl", 13,
                SwerveConstants.BACK_LEFT_REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);
        br_talon = TestUtils.createSteerTalon("t_br", 14,
                SwerveConstants.BACK_RIGHT_REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);


        fl_sens_tn = getOffset(fl_talon, fl_araw_known_pos, SwerveConstants.FRONT_LEFT_REVERSE_TURN);
        fr_sens_tn = getOffset(fr_talon, fr_araw_known_pos, SwerveConstants.FRONT_RIGHT_REVERSE_TURN);
        bl_sens_tn = getOffset(bl_talon, bl_araw_known_pos, SwerveConstants.BACK_LEFT_REVERSE_TURN);
        br_sens_tn = getOffset(br_talon, br_araw_known_pos, SwerveConstants.BACK_RIGHT_REVERSE_TURN);


        doEncAssert(fl_talon, fl_araw_known_pos, SwerveConstants.FRONT_LEFT_REVERSE_TURN, fl_sens_tn);
        doEncAssert(fr_talon, fr_araw_known_pos, SwerveConstants.FRONT_RIGHT_REVERSE_TURN, fr_sens_tn);
        doEncAssert(bl_talon, bl_araw_known_pos, SwerveConstants.BACK_LEFT_REVERSE_TURN, bl_sens_tn);
        doEncAssert(br_talon, br_araw_known_pos, SwerveConstants.BACK_RIGHT_REVERSE_TURN, br_sens_tn);


        /****/
//        int absolutePosition = fl_talon.getSensorCollection().getPulseWidthPosition();
//
//
//        /* Choose so that Talon does not report sensor out of phase */
//        boolean kSensorPhase = true;
//
//        /**
//         * Choose based on what direction you want to be positive,
//         * this does not affect motor invert.
//         */
//        boolean kMotorInvert = false;
//        /* Mask out overflows, keep bottom 12 bits */
//        absolutePosition &= 0xFFF;
//        if (kSensorPhase) { absolutePosition *= -1; }
//        if (kMotorInvert) { absolutePosition *= -1; }
//
//        /* Set the quadrature (relative) sensor to match absolute */
//        fl_talon.setSelectedSensorPosition(absolutePosition, 0, 0);

        /****/


//        updateTarget();

        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);
    }

    @Override
    public void robotPeriodic() {

        if (pidFinished.isStable()) {
            doEncAssert(fl_talon, fl_araw_known_pos, SwerveConstants.FRONT_LEFT_REVERSE_TURN, fl_sens_tn);
            doEncAssert(fr_talon, fr_araw_known_pos, SwerveConstants.FRONT_RIGHT_REVERSE_TURN, fr_sens_tn);
            doEncAssert(bl_talon, bl_araw_known_pos, SwerveConstants.BACK_LEFT_REVERSE_TURN, bl_sens_tn);
            doEncAssert(br_talon, br_araw_known_pos, SwerveConstants.BACK_RIGHT_REVERSE_TURN, br_sens_tn);
        }

        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);
    }

    public void doEncAssert(WPI_TalonSRX t, int toADCPos, boolean reverse_turn, int oldOff) {
//        int newOff = getOffset(t, toADCPos, reverse_turn);
//        if (Math.abs(oldOff - newOff) > 50) {
//            throw new RuntimeException(t.getName() + " is off on offset : " + oldOff + " : " + newOff);
//        }
    }


    public int getOffset(WPI_TalonSRX t, int toADCPos, boolean reverse_turn) {

        ArrayList<Integer> rawArr = new ArrayList<>();
        ArrayList<Integer> posArr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            rawArr.add(t.getSensorCollection().getAnalogInRaw());
            posArr.add(t.getSelectedSensorPosition());
        }
        Collections.sort(rawArr);
        Collections.sort(posArr);

        int rawVal = rawArr.get(rawArr.size() / 2);
        int posVal = posArr.get(posArr.size() / 2);

        SmartDashboard.putNumber(t.getName() + "_rawval", rawVal);
        SmartDashboard.putNumber(t.getName() + "_posval", posVal);

//        System.out.println("raw " + rawVal);
//        System.out.println("pos " + posVal);

        int adcPos = toADCPos - rawVal;
//        System.out.println(t.getName() + "-adcpos " + adcPos);
        int pos = adcPos * (reverse_turn ? -1 : 1);
//        System.out.println(t.getName() + "-pos " + pos);
        posVal=posVal%1024;
        if(posVal<0)
        {
            posVal+=1024;
        }
        pos = posVal + pos;
//        System.out.println(t.getName() + "-pos2 " + pos);
        int round = pos % 1024;
        if(round<0)
        {
            round+=1024;
        }
//        System.out.println("round " + round);
//        trueNorthEncoderOffset = round;

        return round;
    }


    int stableCounts = 2;
    long checkIntervalMillis = 30;

    @Override
    public void autonomousInit() {

        // Supplier<Double> supplier = () -> Math.abs(fl_talon.getErrorDerivative()) + Math.abs(fr_talon.getErrorDerivative()) + Math.abs(bl_talon.getErrorDerivative()) + Math.abs(br_talon.getErrorDerivative());
        // Supplier<Integer> supplier = () -> Math.abs(fl_talon.getClosedLoopError()) + Math.abs(fr_talon.getClosedLoopError()) + Math.abs(bl_talon.getClosedLoopError()) + Math.abs(br_talon.getClosedLoopError());


        m_autoSelected = m_chooser.getSelected();

        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);
    }

    @Override

    public void autonomousPeriodic() {

        // fl_talon.set(ControlMode.Position,m_autoSelected);
        fl_talon.set(ControlMode.Position, fl_sens_tn);
        fr_talon.set(ControlMode.Position, fr_sens_tn);
        bl_talon.set(ControlMode.Position, bl_sens_tn);
        br_talon.set(ControlMode.Position, br_sens_tn);

        if (pidFinished.isStable()) {
            doEncAssert(fl_talon, fl_araw_known_pos, SwerveConstants.FRONT_LEFT_REVERSE_TURN, fl_sens_tn);
            doEncAssert(fr_talon, fr_araw_known_pos, SwerveConstants.FRONT_RIGHT_REVERSE_TURN, fr_sens_tn);
            doEncAssert(bl_talon, bl_araw_known_pos, SwerveConstants.BACK_LEFT_REVERSE_TURN, bl_sens_tn);
            doEncAssert(br_talon, br_araw_known_pos, SwerveConstants.BACK_RIGHT_REVERSE_TURN, br_sens_tn);
        }

        updateSD(fl_talon);
//        updateSD(fr_talon);
//        updateSD(bl_talon);
//        updateSD(br_talon);

        Scheduler.getInstance().run();
    }

//    CalibrateDrivesCommand c = new CalibrateDrivesCommand( fl_talon,
//            fr_talon,
//            bl_talon,
//            br_talon);

    @Override
    public void teleopInit() {

//        c.start();

        m_autoSelected = m_chooser.getSelected();


    }

    public void doSet(WPI_TalonSRX t, int target, int offset) {
        t.set(ControlMode.Position, target + offset);
    }


    @Override
    public void teleopPeriodic() {

        doSet(fl_talon, m_autoSelected, fl_sens_tn);
        doSet(fr_talon, m_autoSelected, fr_sens_tn);
        doSet(bl_talon, m_autoSelected, bl_sens_tn);
        doSet(br_talon, m_autoSelected, br_sens_tn);

        if (pidFinished.isStable()) {
            doEncAssert(fl_talon, fl_araw_known_pos, SwerveConstants.FRONT_LEFT_REVERSE_TURN, fl_sens_tn);
            doEncAssert(fr_talon, fr_araw_known_pos, SwerveConstants.FRONT_RIGHT_REVERSE_TURN, fr_sens_tn);
            doEncAssert(bl_talon, bl_araw_known_pos, SwerveConstants.BACK_LEFT_REVERSE_TURN, bl_sens_tn);
            doEncAssert(br_talon, br_araw_known_pos, SwerveConstants.BACK_RIGHT_REVERSE_TURN, br_sens_tn);
        }

//        fl_talon.set(ControlMode.Position, m_autoSelected);
//        fr_talon.set(ControlMode.Position, m_autoSelected);
//        bl_talon.set(ControlMode.Position, m_autoSelected);
//        br_talon.set(ControlMode.Position, m_autoSelected);


        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);

        Scheduler.getInstance().run();

    }

    @Override
    public void disabledPeriodic() {
        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);
    }

    @Override
    public void testInit() {
//        fl_talon.set(ControlMode.Position,1024);
//        fr_talon.set(ControlMode.Position,1024);
//        bl_talon.set(ControlMode.Position,1024);
//        br_talon.set(ControlMode.Position,1024);

    }

    @Override
    public void testPeriodic() {

        updateSD(fl_talon);
        updateSD(fr_talon);
        updateSD(bl_talon);
        updateSD(br_talon);
    }


    public boolean isTimedOut() {
        return false;
    }

    long lastUpdate = 0;

    public void updateSD(WPI_TalonSRX t) {
        long now = System.currentTimeMillis();
//        if (now - lastUpdate > 100) {
//            lastUpdate = now;


        Faults faults = new Faults();
        t.getFaults(faults);

        // SmartDashboard.putData(t.getName() + "_Talon Data", t);
        SmartDashboard.putNumber(t.getName() + "_Sensor Vel:", t.getSelectedSensorVelocity());
        SmartDashboard.putNumber(t.getName() + "_Sensor Pos:", t.getSelectedSensorPosition());
        SmartDashboard.putNumber(t.getName() + "_CLT:", t.getClosedLoopTarget());
        SmartDashboard.putNumber(t.getName() + "_CLE:", t.getClosedLoopError());
        SmartDashboard.putNumber(t.getName() + "_Analog In", t.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber(t.getName() + "_Analog In Raw", t.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber(t.getName() + "_Out %", t.getMotorOutputPercent());
        SmartDashboard.putBoolean(t.getName() + "_Any Faults:", faults.hasAnyFault());
        SmartDashboard.putBoolean(t.getName() + "_Out Of Phase:", faults.SensorOutOfPhase);
        SmartDashboard.putNumber(t.getName() + "_errDeriv:", t.getErrorDerivative());
        SmartDashboard.putString(t.getName() + "_pidf", pidFinished.toString());
        SmartDashboard.putString(t.getName() + "_scstr", t.getSensorCollection().toString());

//        SmartDashboard.putNumber(t.getName() + "_tn", trueNorthEncoderOffset);
//        }


    }

    public static WPI_TalonSRX createSteerTalon(String name, int talonID, boolean revMotor, double pCoeff,
                                                double iCoeff, double dCoeff,
                                                double fCoeff, int iZone) {

        WPI_TalonSRX talon = new WPI_TalonSRX(talonID);
        talon.configFactoryDefault();
        talon.setName(name);
//        talon.configAllowableClosedloopError(0,10);

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
}


