package frc.team578.robot;

import edu.wpi.first.wpilibj.RobotBase;
import frc.team578.robot.test.SwerveSteerTest;
import frc.team578.robot.test.SwerveSteerTest2;

public final class Main {
    public Main() {
    }

    public static void main(String... args) {
        RobotBase.startRobot(SwerveSteerTest2::new);
    }
}
