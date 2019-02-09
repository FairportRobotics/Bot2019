package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team578.robot.commands.CalibrateDrivesCommand;
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

        buttonA.whenPressed(new CalibrateDrivesCommand());

    }

    public double getPadLeftX() {

        double joyVal = gamepad.getLeftX();
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

    public double getPadLeftY() {
        double joyVal = gamepad.getLeftY();
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

    public double getPadRightX() {
        double joyVal = gamepad.getRightX();
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

    public double getPadRightY() {
        double joyVal = gamepad.getRightY();
        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
    }

    final double JOYSTICK_DEADZONE = 0.1;
//    public double getGamepadRawAxis(int axisID) {
//        double joyVal = driveGamepad.getRawAxis(axisID);
//        return (Math.abs(joyVal) > JOYSTICK_DEADZONE) ? joyVal : 0.0;
//    }

}
