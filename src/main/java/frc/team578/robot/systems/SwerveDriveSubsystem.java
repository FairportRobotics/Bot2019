package frc.team578.robot.systems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team578.robot.systems.mp.PathDriveTrain;
import frc.team578.robot.systems.swerve.SwerveConstants;
import frc.team578.robot.systems.swerve.SwerveDrive;
import frc.team578.robot.systems.swerve.SwerveEnclosure;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;

public class SwerveDriveSubsystem extends Subsystem implements Initializable, PathDriveTrain {


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

    /******************************************/
    // TODO : Review / Calculate / Fix These Value
    private static final double k_wheelDiameter = 0.095; // meters
    private static final int k_ticksPerRev = 2560;
    private static final double k_maxVelocity = 1.60;
    private static final double k_maxAccel = 3.20;
    private static final double k_maxJerk = 60;
    private static final double k_p = 5;
    private static final double k_i = 0;
    private static final double k_d = 0;
    private static final double k_v = 1.0 / k_maxVelocity;
    private static final double k_a = 1.0 / k_maxAccel;
    private static final int k_initial_encoder_position = 0;
    private static final Trajectory.Config k_config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 1.0 / 50.0,
            k_maxVelocity, k_maxAccel, k_maxJerk);


    public EncoderFollower[] generateFollowers(final Waypoint[] points) {
        final SwerveModifier modifier = new SwerveModifier(Pathfinder.generate(points, k_config))
                .modify(SwerveConstants.ROBOT_WHEEL_BASE_WIDTH, SwerveConstants.ROBOT_WHEEL_BASE_LENGTH, SwerveModifier.Mode.SWERVE_DEFAULT);

        final EncoderFollower[] followers = new EncoderFollower[]{
                new EncoderFollower(modifier.getFrontLeftTrajectory()),
                new EncoderFollower(modifier.getFrontRightTrajectory()),
                new EncoderFollower(modifier.getBackRightTrajectory()),
                new EncoderFollower(modifier.getBackLeftTrajectory())
        };

        for (final EncoderFollower follower : followers) {

            // Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
            //      'getEncPosition' function.
            // 1000 is the amount of encoder ticks per full revolution
            // Wheel Diameter is the diameter of your wheel in meters
            follower.configureEncoder(k_initial_encoder_position, k_ticksPerRev, k_wheelDiameter);
            // The first argument is the proportional gain. Usually this will be quite high
            // The second argument is the integral gain. This is unused for motion profiling
            // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
            // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the
            //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
            // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
            follower.configurePIDVA(k_p, k_i, k_d, k_v, k_a);
        }

        return followers;
    }

    public boolean follow() {

        Waypoint[] points = new Waypoint[] {
                new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
                new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
                new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
        };

        // Create the Trajectory Configuration
        //
        // Arguments:
        // Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
        // Sample Count:        SAMPLES_HIGH (100 000)
        //                      SAMPLES_LOW  (10 000)
        //                      SAMPLES_FAST (1 000)
        // Time Step:           0.05 Seconds
        // Max Velocity:        1.7 m/s
        // Max Acceleration:    2.0 m/s/s
        // Max Jerk:            60.0 m/s/s/s

        // Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);

        // Generate the trajectory
        Trajectory trajectory = Pathfinder.generate(points, k_config);

        for (int i = 0; i < trajectory.length(); i++) {
            Trajectory.Segment seg = trajectory.get(i);

            System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n",
                    seg.dt, seg.x, seg.y, seg.position, seg.velocity,
                    seg.acceleration, seg.jerk, seg.heading);
        }

        EncoderFollower flFollower  = null;
        SwerveEnclosure flEnclosure = null;

        double speed = flFollower.calculate(flEnclosure.getDriveEncoderPosition());
        double desiredHeading = Pathfinder.boundHalfDegrees(Pathfinder.r2d(flFollower.getHeading()));    // Bound to -180..180 degrees

        // TODO : The values for these move params are probably very wrong
        flEnclosure.move(speed,desiredHeading);

        return flFollower.isFinished();
    }

    @Override
    public int getLeftEncoderTicks() {
        return 0;
    }

    @Override
    public int getRightEncoderTicks() {
        return 0;
    }

    @Override
    public void setMotors(double left, double right) {

    }
}
