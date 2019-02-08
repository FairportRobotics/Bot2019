package frc.team578.robot.test;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team578.robot.Robot;
import frc.team578.robot.commands.CalibrateDrivesCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwerveSteerTest extends TimedRobot {

    private static final Logger log = LogManager.getLogger(SwerveSteerTest.class);

    public static final boolean REVERSE_TURN = true;
    public static final int ROTATE_TALON_ID = 13;
    public static final int TIMEOUT_MS = 0; // set to zero if skipping confirmation
    public static final int PIDLOOP_IDX = 0; // set to zero if primary loop
    public static final int PROFILE_SLOT = 0;
    public static final boolean ALIGNED_TURN_SENSOR = false; // encoder polarity
    public static final double turn_kP = 18;
    public static final double turn_kI = 0.0;
    public static final double turn_kD = 0.0;
    public static final double turn_kF = 0.0;
    public static final int turn_kIZone = 0;
    public static final double MAX_ENC_VAL = 1024;
    int target;
    boolean finished = false;


    WPI_TalonSRX _talon;
    Joystick _joystick = new Joystick(0); /* make a joystick */
    Faults _faults = new Faults(); /* temp to fill with latest faults */

    @Override
    public void robotInit() {


        _talon = TestUtils.createSteerTalon(ROTATE_TALON_ID,
                REVERSE_TURN, turn_kP, turn_kI, turn_kD, turn_kF,
                turn_kIZone);

        updateTarget();
    }

    @Override
    public void teleopInit() {
        updateTarget();
    }

    void updateTarget() {
        int offset = 454;
        int pos = _talon.getSelectedSensorPosition();
        int round = (1024 * (pos / 1024));
        target = offset + round;
    }

    int offset = -245;
    @Override
    public void teleopPeriodic() {

        //        double xSpeed = _joystick.getRawAxis(1) * -1; // make forward stick positive
        //        _talon.set(ControlMode.PercentOutput, xSpeed);

        /* update motor controller */
        _talon.set(ControlMode.Position, offset);

//        if (isFinished()) {
//            // new WaitCommand(1).start();
//            _talon.set(ControlMode.Position, _talon.getSelectedSensorPosition() < 500 ? 5000 : 0);
//        }



        /* check our live faults */
        _talon.getFaults(_faults);

        updateSD();

    }



    @Override
    public void disabledPeriodic() {
        updateSD();
        updateTarget();
    }


    static int max_run_time_sec = 10;
    boolean found = false;
    int stableCounts = 3;
    double stopZone = 0;
    int successCount = 0;
    long lastChecked = 0;
    long checkIntervalMillis = 50;

    protected boolean isFinished() {

//        double sumSteerCLE = Robot.swerveDriveSubsystem.getSteerCLTErrorSum();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastChecked > checkIntervalMillis) {
            lastChecked = currentTime;

//            double currentDeriv = Robot.swerveDriveSubsystem.getSteerErrorDerivitiveSum();
            double currentDeriv = Math.abs(_talon.getErrorDerivative());
            if (currentDeriv <= stopZone) {
                successCount++;
            } else {
                successCount = 0;
            }
        }

        found = (successCount >= stableCounts || isTimedOut());

        if (found) {
            if (isTimedOut()) {
                log.warn("CalibrateDrivesCommand timed out");
                Robot.swerveDriveSubsystem.stop();
            }
        }

        return found;

    }

    public boolean isTimedOut() {
        return false;
    }

    public void updateSD() {

        SmartDashboard.putData("Talon Data", _talon);
        SmartDashboard.putNumber("Sensor Vel:", _talon.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Sensor Pos:", _talon.getSelectedSensorPosition());
        SmartDashboard.putNumber("Analog In", _talon.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("Analog In Raw", _talon.getSensorCollection().getAnalogInRaw());
        SmartDashboard.putNumber("Out %", _talon.getMotorOutputPercent());
        SmartDashboard.putBoolean("Any Faults:", _faults.hasAnyFault());
        SmartDashboard.putBoolean("Out Of Phase:", _faults.SensorOutOfPhase);
        SmartDashboard.putNumber("target:", target);
        SmartDashboard.putNumber("errDeriv:", _talon.getErrorDerivative());
        SmartDashboard.putNumber("clerr", _talon.getClosedLoopError());
        SmartDashboard.putBoolean("isFinished", isFinished());


    }
}

