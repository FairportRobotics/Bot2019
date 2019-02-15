package frc.team578.robot.subsystems.swerve;

public class SwerveConstants {


    public static final double ROBOT_LENGTH = .625;
    public static final double ROBOT_WIDTH = .47;

    // Analog: Pos: -485u   |   Vel: 0u/100ms   |   ADC:539   |   1.7 V
    public static final int FRONT_LEFT_TRUE_NORTH_ENC_POS = 535;
    // Analog: Pos: -453u   |   Vel: 0u/100ms   |   ADC:571   |   1.7 V
    public static final int FRONT_RIGHT_TRUE_NORTH_ENC_POS = 573;
    // Analog: Pos: 245u   |   Vel: 0u/100ms   |   ADC:245   |   0.8 V
    public static final int BACK_LEFT_TRUE_NORTH_ENC_POS = 245;
    // Analog: Pos: 679u   |   Vel: 2u/100ms   |   ADC:679   |   2.2 V
    public static final int BACK_RIGHT_TRUE_NORTH_ENC_POS = 679;

    public static final boolean FRONT_LEFT_REVERSE_DRIVE = true;
    public static final boolean FRONT_RIGHT_REVERSE_DRIVE = false;
    public static final boolean BACK_LEFT_REVERSE_DRIVE = true;
    public static final boolean BACK_RIGHT_REVERSE_DRIVE = false;

    public static final boolean FRONT_LEFT_REVERSE_TURN = false;
    public static final boolean FRONT_RIGHT_REVERSE_TURN = false;
    public static final boolean BACK_LEFT_REVERSE_TURN = false;
    public static final boolean BACK_RIGHT_REVERSE_TURN = false;

    public static final int TIMEOUT_MS = 0; // set to zero if skipping confirmation
    public static final int PIDLOOP_IDX = 0; // set to zero if primary loop
    public static final int PROFILE_SLOT = 0;
    public static final boolean ALIGNED_TURN_SENSOR = false; // encoder polarity
    public static final double turn_kP = 8;
    public static final double turn_kI = 0;
    public static final double turn_kD = 0;
    public static final double turn_kD1 = 300;
    public static final double turn_kD2 = 300;
    public static final double turn_kD3 = 750;
    public static final double turn_kD4 = 400;
    public static final double turn_kF = 0.0;
    public static final int turn_kIZone = 0;
    public static final int MAX_ENC_VAL = 1024;
}
