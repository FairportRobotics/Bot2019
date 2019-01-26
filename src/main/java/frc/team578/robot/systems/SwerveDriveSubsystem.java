package frc.team578.robot.systems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.systems.interfaces.DashUpdate;
import frc.team578.robot.systems.interfaces.Initializable;
import frc.team578.robot.systems.swerve.SwerveDrive;

public class SwerveDriveSubsystem extends Subsystem implements Initializable, DashUpdate {

    private SwerveDrive swerveDrive;

    @Override
    protected void initDefaultCommand() {
        /*
        TODO : Should SwerveDriveCommand go here?
         */
    }

    @Override
    public void initialize() {
        swerveDrive = SwerveDrive.create();
    }

    public void move(double fwd, double str, double rot, double angleDeg) {
        swerveDrive.move(fwd,str,rot,angleDeg);
    }

    public void zeroAllSteerEncoders() {
        swerveDrive.zeroAllSteerEncoders();
    }

    public void dashboardUpdate() {
        SmartDashboard.putData(this);

        swerveDrive.dashboardUpdate();
    }


}
