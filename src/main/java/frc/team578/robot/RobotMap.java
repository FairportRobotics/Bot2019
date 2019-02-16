package frc.team578.robot;

public class RobotMap {

    // drive controller IDs
    public static final int FRONT_LEFT_DRIVE_TALON_ID = 1;
    public static final int FRONT_RIGHT_DRIVE_TALON_ID = 2;
    public static final int BACK_LEFT_DRIVE_TALON_ID = 3;
    public static final int BACK_RIGHT_DRIVE_TALON_ID = 4;

    // steer controllers IDs
    public static final int FRONT_LEFT_ROTATE_TALON_ID = 11;
    public static final int FRONT_RIGHT_ROTATE_TALON_ID = 12;
    public static final int BACK_LEFT_ROTATE_TALON_ID = 13;
    public static final int BACK_RIGHT_ROTATE_TALON_ID = 14;

    public static final int PIGEON_IMU_ID = 5;

    // swerve controller joystick USB IDs
    public static final int CONTROL_GAMEPAD_ID = 0;
    public static final int ELEVATOR_GAMEPAD_ID = 1;

    // Pneumatics Control
    public static final int PCM = 0;


    // Climber
    public static final int PCM_FRONT_CLIMB_UP = 0;
    public static final int PCM_FRONT_CLIMB_DOWN = 0;
    public static final int PCM_REAR_CLIMB_UP = 0;
    public static final int PCM_REAR_CLIMB_DOWN = 0;
    public static final int CLIMB_WHEELS_TALON_ID = 0;

    // Intake
    public static final int PCM_INTAKE_OPEN = 0;
    public static final int PCM_INTAKE_CLOSE = 0;

    // Elevator
    public static final int ELEVATOR_ARM_TALON = 111111; // TODO: find talon value
    public static final int ELEVATOR_STRUCTURE_TALON = 111112; // TODO: find talon value

    // ARM
    public static final int PCM_ARM_FIRST_EXTEND = -1;
    public static final int PCM_ARM_FIRST_RETRACT = -1;
    public static final int PCM_ARM_SECOND_EXTEND = -1;
    public static final int PCM_ARM_SECOND_RETRACT = -1;
}