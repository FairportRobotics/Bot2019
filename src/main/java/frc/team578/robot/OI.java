package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.team578.robot.systems.Initializable;

public class OI implements Initializable {


    public static final double JOYSTICK_DEADZONE = 0.1;
    public static final int CONTROL_GAMEPAD_ID = 0;
    public static final int LEFT_X_AXIS = 0;
    public static final int LEFT_Y_AXIS = 1;
    public static final int RIGHT_X_AXIS = 2;
    public static final int RIGHT_Y_AXIS = 3;
    public static final int A = 1; // Bottom Button
    public static final int B = 2; // Right Button
    public static final int X = 3; // Left button
    public static final int Y = 4; // Top Button

    public static Joystick driveGamepad;

    public void initialize() {
    }

    public static double getGamepadRawAxis(int axisID) {
        double joyVal = driveGamepad.getRawAxis(axisID);
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

}
