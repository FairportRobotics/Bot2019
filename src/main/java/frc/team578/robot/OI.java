package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team578.robot.commands.*;
import frc.team578.robot.enums.ArmPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.utils.Gamepad;

public class OI implements Initializable {

    public Joystick leftJoystick = new Joystick(1);
    public Joystick rightJoystick = new Joystick(2);
    public GP gp1 = new GP(RobotMap.CONTROL_GAMEPAD_ID); // Elevator and arm functions
    public GP gp2 = new GP(RobotMap.ELEVATOR_GAMEPAD_ID); // Climber functions


    double lastXValue;
    public double getStrafe() {
        if (!leftJoystick.getTrigger()) {
            lastXValue = leftJoystick.getX();
        }
        return lastXValue;
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

    // This is here to make buttons persistant (i.e. Gamepad makes a new instance every request
    // TODO : Want to fix that at some point.
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
    }
}