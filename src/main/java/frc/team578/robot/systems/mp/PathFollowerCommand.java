package frc.team578.robot.systems.mp;

import edu.wpi.first.wpilibj.command.Command;
import frc.team578.robot.systems.SwerveDriveSubsystem;

public class PathFollowerCommand extends Command {

    private PathFollower pf;
    public PathFollowerCommand(String pathName, SwerveDriveSubsystem drive, PathFollowerConfig cfg) {
        requires(drive);
        pf = new PathFollower(pathName,drive,cfg);
    }

    @Override
    protected void initialize() {
        pf.reset();
    }

    @Override
    public void execute() {
        pf.run();
    }

    @Override
    public boolean isFinished() {
        return pf.isFinished();
    }



}

