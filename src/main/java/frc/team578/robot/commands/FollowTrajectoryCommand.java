package frc.team578.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.Robot;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowTrajectoryCommand extends Command {

    private final EncoderFollower[] m_followers;

    public FollowTrajectoryCommand(final Waypoint[] points) {
        requires(Robot.swerveDriveSubsystem);

        m_followers = Robot.swerveDriveSubsystem.generateFollowers(points);
    }

    @Override
    protected void initialize() {
//        Robot.swerveDriveSubsystem.startFollowing(m_followers);
    }

    @Override
    protected void execute() {
        // See isFinished()
    }

    @Override
    protected boolean isFinished() {
//        return Robot.swerveDriveSubsystem.follow(m_followers);
        return false;
    }

    @Override
    protected void end() {
//        Robot.swerveDriveSubsystem.drive(0.0, 0.0);
    }
}
