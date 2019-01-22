package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.team578.robot.systems.Initializable;

public class OI implements Initializable {

    /*
    TODO : Change over to use Gamepad class.
     */

    public static final int LEFT_X_AXIS = 0;
    public static final int LEFT_Y_AXIS = 1;
    public static final int RIGHT_X_AXIS = 2;
    final double JOYSTICK_DEADZONE = 0.1;

    public static Joystick driveGamepad;

    public void initialize() {
        driveGamepad = new Joystick(RobotMap.CONTROL_GAMEPAD_ID);
    }

    public double getGamepadRawAxis(int axisID) {
        double joyVal = driveGamepad.getRawAxis(axisID);
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

}
