package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team578.robot.Robot;

public class TurnToHeadingCommand extends PIDCommand {

    private final double targetAngle;

    private static final double kP = .1;
    private static final double kI = 0.0;
    private static final double kD = 0.25;

    private final double kToleranceDegrees = 2f;
    private final double gyroDeadbandRate = 0.3;

    private boolean hasRunReturnPidInputAtLeastOnce;

    public TurnToHeadingCommand(double targetAngle) {
        super("turn to " + targetAngle, kP, kI, kD);

        requires(Robot.swerveDriveSubsystem);

        this.targetAngle = targetAngle;

        setTimeout(5);
    }

    @Override
    protected void initialize() {
        getPIDController().setInputRange(0.f, 360.f);
        getPIDController().setOutputRange(-.5, .5);
        getPIDController().setAbsoluteTolerance(kToleranceDegrees);
        getPIDController().setContinuous(true);
        setSetpoint(this.targetAngle);
        hasRunReturnPidInputAtLeastOnce = false;
    }

    @Override
    protected boolean isFinished() {

        boolean isFinished = (this.getPIDController().onTarget() && hasRunReturnPidInputAtLeastOnce && Math.abs(Robot.gyroSubsystem.getRate()) <= gyroDeadbandRate) || isTimedOut();
        System.err.println("AutoTurn isFinished " + isFinished + "," + this.getPIDController().onTarget() + "," + hasRunReturnPidInputAtLeastOnce + "," + Math.abs(Robot.gyroSubsystem.getRate()) + "," + Robot.gyroSubsystem.getHeading());

        if (isTimedOut()) {
            System.err.println("Auto Heading Timed Out");
        }

        return isFinished;
    }

    @Override
    protected void interrupted() {
    }

    @Override
    protected double returnPIDInput() {
        if (!hasRunReturnPidInputAtLeastOnce)
            hasRunReturnPidInputAtLeastOnce = true;

        double heading = Robot.gyroSubsystem.getHeading();

        return heading;
    }

    @Override
    protected void usePIDOutput(double output) {
//		System.err.println("Heading Motor Value " + output);
//        Robot.swerveDriveSubsystem.move(0, 0, output, 0);
          Robot.swerveDriveSubsystem.move(0, 0, output, Robot.gyroSubsystem.getHeading());
    }

    @Override
    protected void end() {
        System.err.println(timeSinceInitialized() + " : AutoTurnToHeader Finished");
    }
}
