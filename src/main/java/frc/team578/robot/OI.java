package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.utils.Gamepad;

public class OI implements Initializable {

    Gamepad gamepad = new Gamepad(RobotMap.CONTROL_GAMEPAD_ID);

    // Gamepad controls
    JoystickButton rb = gamepad.getRightShoulder();
    JoystickButton lb = gamepad.getLeftShoulder();
    JoystickButton rt = gamepad.getRightTriggerClick();
    JoystickButton lt = gamepad.getLeftTriggerClick();
    JoystickButton buttonA = gamepad.getButtonA();
    JoystickButton buttonB = gamepad.getButtonB();
    JoystickButton buttonX = gamepad.getButtonX();
    JoystickButton buttonY = gamepad.getButtonY();
    JoystickButton back = gamepad.getBackButton();
    JoystickButton start = gamepad.getStartButton();
    boolean dpadLeft = gamepad.getDPadLeft();

    public void initialize() {

    }

    public double getPadLeftX() {
        return gamepad.getLeftX();
    }

    public double getPadLeftY() {
        return gamepad.getLeftY();
    }

    public double getPadRightX() {
        return gamepad.getRightX();
    }

    public double getPadRightY() {
        return gamepad.getRightY();
    }

//    final double JOYSTICK_DEADZONE = 0.1;
//    public double getGamepadRawAxis(int axisID) {
//        double joyVal = driveGamepad.getRawAxis(axisID);
//        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
//    }

}
