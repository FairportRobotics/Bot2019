package frc.team578.robot.systems;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.RobotMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GyroSubsystem extends Subsystem implements Initializable {

    Logger log = LogManager.getRootLogger();

    // https://www.ctr-electronics.com/downloads/pdf/Pigeon%20IMU%20User's%20Guide.pdf
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/PigeonStraight/src/org/usfirst/frc/team217/robot/Robot.java
    // http://www.ctr-electronics.com/downloads/api/java/html/index.html

    private static PigeonIMU _pigeon;

    @Override
    protected void initDefaultCommand() {
    }

    public void initialize() {

        try {
            _pigeon = new PigeonIMU(RobotMap.PIGEON_IMU_ID);
        } catch (RuntimeException e) {
            log.error("Gyro Error : " + e.getMessage(),e);
            throw e;
        }

        reset();
    }

    public void reset() {
        log.info("Reset Gyro Heading");

        if (_pigeon != null) {
            final int kTimeoutMs = 30;
            _pigeon.setFusedHeading(0.0, kTimeoutMs);
        }
    }

    public void updateDashboard() {

        SmartDashboard.putNumber("pigeon getAngle", getAngle());
        SmartDashboard.putNumber("pigeon fusedHeading", _pigeon.getFusedHeading());
        SmartDashboard.putNumber("pigeon absCompHeading", _pigeon.getAbsoluteCompassHeading());
        SmartDashboard.putNumber("pigeon compHeading", _pigeon.getCompassHeading());
        SmartDashboard.putNumber("pigeon state", _pigeon.getState().value);
    }

    public double getAngle() {
        return Math.abs(_pigeon.getFusedHeading() % 360);
    }
}
