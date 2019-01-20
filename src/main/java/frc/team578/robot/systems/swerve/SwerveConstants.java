package frc.team578.robot.systems.swerve;

public class SwerveConstants {


    public static final double ROBOT_WHEEL_BASE_LENGTH = .625;
    public static final double ROBOT_WHEEL_BASE_WIDTH = .47;

    public static final boolean FRONT_LEFT_REVERSE_DRIVE = true;
    public static final boolean FRONT_RIGHT_REVERSE_DRIVE = false;
    public static final boolean BACK_LEFT_REVERSE_DRIVE = true;
    public static final boolean BACK_RIGHT_REVERSE_DRIVE = false;

    public static final boolean FRONT_LEFT_REVERSE_TURN = true;
    public static final boolean FRONT_RIGHT_REVERSE_TURN = true;
    public static final boolean BACK_LEFT_REVERSE_TURN = true;
    public static final boolean BACK_RIGHT_REVERSE_TURN = true;

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
}