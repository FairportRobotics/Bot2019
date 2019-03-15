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

    // swerve controller joystick USB IDs
    public static final int CONTROL_GAMEPAD_ID = 0;
    public static final int CLIMB_GAMEPAD_ID = 1;
    public static final int LEFT_JOYSTICK_ID = 2;
    public static final int RIGHT_JOYSTICK_ID = 3;

    // Pneumatics Control
    public static final int PCM1 = 40;
    public static final int PCM2 = 41;


    // Climber
    public static final int PCM1_FRONT_CLIMB_UP = 1;
    public static final int PCM1_FRONT_CLIMB_DOWN = 0;
    public static final int PCM1_REAR_CLIMB_UP = 3;
    public static final int PCM1_REAR_CLIMB_DOWN = 2;
    public static final int PCM1_CUTOFF_OPEN = 4;
    public static final int PCM1_CUTOFF_CLOSE = 5;
    public static final int CLIMB_WHEELS_TALON_ID = 23;

    // Intake
    public static final int PCM1_INTAKE_OPEN = 7;
    public static final int PCM1_INTAKE_CLOSED = 6;
    public static final int PCM2_ARM_ONE_EXTEND = 1;
    public static final int PCM2_ARM_ONE_RETRACT = 0;
    public static final int PCM2_ARM_TWO_EXTEND = 3;
    public static final int PCM2_ARM_TWO_RETRACT = 2;
    public static int INTAKE_TALON = 20;

    // Elevator
    public static final int ELEVATOR_ARM_TALON = 22; // TODO: find talon value
    public static final int ELEVATOR_STRUCTURE_TALON = 21; // TODO: find talon value

    // ARM
//    public static final int PCM_ARM_FIRST_EXTEND = -1;
//    public static final int PCM_ARM_FIRST_RETRACT = -1;
//    public static final int PCM_ARM_SECOND_EXTEND = -1;
//    public static final int PCM_ARM_SECOND_RETRACT = -1;






}