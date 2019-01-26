package frc.team578.robot.systems;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.RobotMap;
import frc.team578.robot.systems.interfaces.DashUpdate;
import frc.team578.robot.systems.interfaces.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GyroSubsystem extends Subsystem implements Initializable, DashUpdate {

    private static final Logger log = LogManager.getLogger(GyroSubsystem.class);

    // https://www.ctr-electronics.com/downloads/pdf/Pigeon%20IMU%20User's%20Guide.pdf
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/PigeonStraight/src/org/usfirst/frc/team217/robot/Robot.java
    // http://www.ctr-electronics.com/downloads/api/java/html/index.html

    private static PigeonIMU pigeon;
    private String name;


    public GyroSubsystem(String name) {
        this.name = name;
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void initialize() {

        try {
            pigeon = new PigeonIMU(RobotMap.PIGEON_IMU_ID);
        } catch (RuntimeException e) {
            log.error("Gyro Error : " + e.getMessage(),e);
            throw e;
        }

        reset();

        // TODO : Try this custom Sendable
        // TODO : Add gyro / compass widget graph properties
//        this.addChild(new GyroSendable());

    }

    public void reset() {
        log.info("Reset Gyro Heading");

        if (pigeon != null) {
            final int kTimeoutMs = 30;
            pigeon.setFusedHeading(0.0, kTimeoutMs);
        }
    }

    public double getAngle() {
        return Math.abs(pigeon.getFusedHeading() % 360);
    }

    @Override
    public void dashboardUpdate() {
        SmartDashboard.putNumber("pigeon.fusedh", pigeon.getFusedHeading());
        SmartDashboard.putNumber("pigeon.angle",getAngle());
    }

    class GyroSendable implements Sendable {

        String name = "Gyro";
        String subsystem = "Ungrouped";

        public GyroSendable() {
            LiveWindow.add(this);
            setName(name);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getSubsystem() {
            return subsystem;
        }

        @Override
        public void setSubsystem(String subsystem) {
            this.subsystem = subsystem;
        }

        @Override
        public void initSendable(SendableBuilder builder) {
            builder.addDoubleProperty("FusedHeading", pigeon::getFusedHeading, pigeon::setFusedHeading);
        }
    }
}
