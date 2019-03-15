package frc.team578.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team578.robot.commands.*;
import frc.team578.robot.enums.ArmPositionEnum;
import frc.team578.robot.subsystems.interfaces.Initializable;
import frc.team578.robot.utils.Gamepad;

public class OI implements Initializable {

    public Joystick leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK_ID);
    public Joystick rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK_ID);
    public GP gp1 = new GP(RobotMap.CONTROL_GAMEPAD_ID); // Elevator and arm functions
    public GP gp2 = new GP(RobotMap.CLIMB_GAMEPAD_ID); // Climber functions

    int JOYSTICK_TRIGGER_BUTTON_NUMBER = 1;
    JoystickButton leftTrigger = new JoystickButton(leftJoystick, JOYSTICK_TRIGGER_BUTTON_NUMBER);
    JoystickButton rightTrigger = new JoystickButton(rightJoystick, JOYSTICK_TRIGGER_BUTTON_NUMBER);


    public void initialize() {

        gp1.buttonA.whenPressed(new MoveArmCommand(ArmPositionEnum.RETRACTED));
        gp1.buttonX.whenPressed(new MoveArmCommand(ArmPositionEnum.MID_EXTEND));
        gp1.buttonY.whenPressed(new MoveArmCommand(ArmPositionEnum.FULL_EXTEND));
        gp1.lb.whileHeld(new IntakeSpinInwardCommand());
        gp1.rb.whileHeld(new IntakeSpinOutwardCommand());
        gp1.rt.whenPressed(new IntakeExtendCommand());
        gp1.lt.whenPressed(new IntakeRetractCommand());

        gp2.buttonY.whenPressed(new ClimberExtendAllCommand());
        gp2.buttonX.whenPressed(new ClimberRetractFrontCommand());
        gp2.buttonB.whenPressed(new ClimberRetractRearCommand());
        gp2.buttonA.whenPressed(new ClimberRetractAllCommand());
        gp2.lb.whenPressed(new ClimberExtendRearCommand());
        gp2.start.whileHeld(new ClimberDriveForwardsCommand());
        gp2.back.whileHeld(new ClimberDriveReverseCommand());

//        leftJoystick.getTriggerPressed(new CentricModeRobotCommand());
//        gp2.back.whileHeld(new CentricModeFieldCommand());

//        if(leftJoystick.getTriggerPressed())
//            new CentricModeRobotCommand();
//        if(leftJoystick.getTriggerReleased())
//            new CentricModeFieldCommand();

        leftTrigger.whenPressed(new CentricModeRobotCommand());
        rightTrigger.whenPressed(new CentricModeFieldCommand());
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

    public double getArmUp() {

        if (gp1 != null) {
            if (deadband(gp1.getPadLeftY()) != 0) {
                return gp1.getPadLeftY();
            }
        }

        if (gp2 != null) {
            if (deadband(gp2.getPadLeftY()) != 0) {
                return gp2.getPadLeftY();
            }
        }

        return 0d;
    }

    public double getStructureUp() {
        if (gp1 != null) {
            if (deadband(gp1.getPadRightY()) != 0) {
                return gp1.getPadRightY();
            }
        }

        if (gp2 != null) {
            if (deadband(gp2.getPadRightY()) != 0) {
                return gp2.getPadRightY();
            }
        }

        return 0d;
    }

    // This affects drive and arm movement deadbands
    final double DEADBAND = 0.05;
    public double deadband(double value) {
        if (Math.abs(value) < DEADBAND) return 0.0;
        return value*Math.abs(value);
    }
}