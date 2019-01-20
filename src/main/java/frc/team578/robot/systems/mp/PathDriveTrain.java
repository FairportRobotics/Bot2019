package frc.team578.robot.systems.mp;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import jaci.pathfinder.PathfinderFRC;

public interface PathDriveTrain {
    public int getLeftEncoderTicks();
    public int getRightEncoderTicks();
    public void setMotors(double left, double right);
    public default double getHeading() {
        return Double.NaN;
    }
    static PathDriveTrain fromParts(Encoder leftEncoder, Encoder rightEncoder, DifferentialDrive drive) {
        return new PathDriveTrain() {
            @Override
            public int getLeftEncoderTicks() {
                return leftEncoder.get();
            }
            @Override
            public int getRightEncoderTicks() {
                return rightEncoder.get();
            }
            @Override
            public void setMotors(double left, double right) {
                drive.tankDrive(left,right);
            }
        };
    }
}
