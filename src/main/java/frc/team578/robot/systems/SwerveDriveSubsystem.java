package frc.team578.robot.systems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.systems.interfaces.Initializable;
import frc.team578.robot.systems.interfaces.UpdateDashboard;
import frc.team578.robot.systems.swerve.SwerveDrive;

public class SwerveDriveSubsystem extends Subsystem implements Initializable, UpdateDashboard {

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
        swerveDrive.move(fwd, str, rot, angleDeg);
    }

    public void zeroAllSteerEncoders() {
        swerveDrive.zeroAllSteerEncoders();
    }

    /*
    This should be called when the robot starts up to align the talon with the steering encoder
     */
    public void moveSteerTrueNorth() {
        swerveDrive.moveSteerTrueNorth();
    }

    public void stop() {
        swerveDrive.stop();
    }

    @Override
    public void updateDashboard() {
        SmartDashboard.putData(this);

        swerveDrive.updateDashboard();
    }

    public double getSteerCLTErrorSum() {
        return swerveDrive.getSteerCLTErrorSum();
    }

    public double getSteerErrorDerivitiveSum() {
        return swerveDrive.getSteerErrorDerivitiveSum();
    }
}
