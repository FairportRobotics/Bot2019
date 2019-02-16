package frc.team578.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team578.robot.commands.*;
import frc.team578.robot.enums.ArmPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.utils.Gamepad;

public class OI implements Initializable {

    private GP gp1 = new GP(RobotMap.CONTROL_GAMEPAD_ID); // Elevator and arm functions
    private GP gp2 = new GP(RobotMap.ELEVATOR_GAMEPAD_ID); // Climber functions


    public GP getGP1() {
        return gp1;
    }

    public GP getGP2() {
        return gp2;
    }

    public void initialize() {

        // Gamepad 1

        // Arm buttons
//        gp1.buttonA.whenPressed(new MoveArmCommand(ArmPositionEnum.RETRACTED));
//        gp1.buttonB.whenPressed(new MoveArmCommand(ArmPositionEnum.MID_EXTEND));
//        gp1.buttonX.whenPressed(new MoveArmCommand(ArmPositionEnum.MID2_EXTEND));
//        gp1.buttonY.whenPressed(new MoveArmCommand(ArmPositionEnum.FULL_EXTEND));
//        // Intake buttons
//        gp1.lb.whileHeld(new IntakeSpinInwardCommand());
//        gp1.rb.whileHeld(new IntakeSpinOutwardCommand());
//        gp1.lt.whenPressed(new IntakeExtendCommand());
//        gp1.rt.whenPressed(new IntakeRetractCommand());
//
//        // Gamepad 2
//
//        // Climber buttons
//        gp2.buttonA.whenPressed(new ClimberExtendFrontCommand());
//        gp2.buttonB.whenPressed(new ClimberExtendRearCommand());
//        gp2.buttonX.whenPressed(new ClimberRetractFrontCommand());
//        gp2.buttonY.whenPressed(new ClimberRetractRearCommand());

//        gp2.start.whileHeld(new ClimberDriveForwardsCommand());
//        gp2.back.whileHeld(new ClimberDriveReverseCommand());

    }

    public class GP {

        Gamepad gamepad;
        JoystickButton rb;
        JoystickButton lb;
        JoystickButton rt;
        JoystickButton lt;
        JoystickButton buttonA;
        JoystickButton buttonB;
        JoystickButton buttonX;
        JoystickButton buttonY;
        JoystickButton back;
        JoystickButton start;
        boolean dpadLeft;

        // Gamepad controls


        public GP(int id) {
            gamepad = new Gamepad(id);
            rb = gamepad.getRightShoulder();
            lb = gamepad.getLeftShoulder();
            rt = gamepad.getRightTriggerClick();
            lt = gamepad.getLeftTriggerClick();
            buttonA = gamepad.getButtonA();
            buttonB = gamepad.getButtonB();
            buttonX = gamepad.getButtonX();
            buttonY = gamepad.getButtonY();
            back = gamepad.getBackButton();
            start = gamepad.getStartButton();
            boolean dpadLeft = gamepad.getDPadLeft();
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
}