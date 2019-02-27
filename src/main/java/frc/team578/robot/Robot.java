package frc.team578.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.lang.*;

public class Robot extends TimedRobot {

    // Joystick pointer
    private static Joystick joystick;

    Gamepad gamepad = new Gamepad(0);
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

    public void robotInit() {
        System.out.println("Robot Init");

        gamepad = new Gamepad(0);
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
    }

    @Override
    public void robotPeriodic() {

        back.whenPressed(new CommandPrint("BACK"));
//        buttonY.whileHeld(new CommandPrint("Y"));

        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        System.out.println("Disabled Init");
    }

    @Override
    public void disabledPeriodic() {
        updateDashboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        System.out.println("Teleop Init");
    }

    @Override
    public void teleopPeriodic() {
        updateDashboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        System.out.println("Auto Init");
    }

    @Override
    public void autonomousPeriodic() {
        updateDashboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        System.out.println("Test Init");
    }

    @Override
    public void testPeriodic() {
        Scheduler.getInstance().run();
    }

    public void updateDashboard() {

    }

    class CommandPrint extends Command {

        String message;

        public CommandPrint(String back) {

            message = back;
        }

        long last = System.currentTimeMillis();
        protected void execute() {

            if (System.currentTimeMillis() - last > 500) {
                last = System.currentTimeMillis();
                if (buttonY.get()) {
                    System.err.println(System.currentTimeMillis() + " SHIFTED " + message);
                } else {
                    System.err.println(System.currentTimeMillis() + " " + message);
                }
            }
        }

        protected void end() {
            System.err.println(message + " END");
        }


        @Override
        protected boolean isFinished() {
            return false;
        }
    }
}
