package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElevatorMoveAnalogCommand extends Command {

    private static final Logger log = LogManager.getLogger(ElevatorMoveAnalogCommand.class);

    public ElevatorMoveAnalogCommand() {
        requires(Robot.elevatorSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

        double arm = Robot.oi.getGP2().getPadLeftY();

        double structure = Robot.oi.getGP2().getPadRightY();

        Robot.elevatorSubsystem.moveStructureMotor(structure);
        Robot.elevatorSubsystem.moveArmMotor(arm);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        log.info("ElevatorMoveAnalogCommand Ended");
    }

    @Override
    protected void interrupted() {
        log.info("ElevatorMoveAnalogCommand Interrupted");

    }

}
