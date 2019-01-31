package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;

public class IntakeSpinOutwardCommand extends Command {

    public IntakeSpinOutwardCommand() {
        requires(Robot.cargoIntakeSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.cargoIntakeSubsystem.spinIntakeOutwards();
    }



    @Override
    protected void interrupted() {
        Robot.cargoIntakeSubsystem.spinIntakeStop();
    }

    @Override
    protected void end() {
        Robot.cargoIntakeSubsystem.spinIntakeStop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
