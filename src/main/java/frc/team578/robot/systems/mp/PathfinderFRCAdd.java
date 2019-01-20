package frc.team578.robot.systems.mp;

import edu.wpi.first.wpilibj.Filesystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;

import java.io.File;

public class PathfinderFRCAdd {
    private PathfinderFRCAdd() {}

    /**
     * Default Acceleration for a Kit-of-parts drivetrain (in m/s/s)
     */
    public static double DEFAULT_ACC  = 2.0;

    /**
     * Default Jerk, in m/s/s/s
     */
    public static double DEFAULT_JERK = 60.0;

    /**
     * Get the absolute path of a trajectory file generated with the given name, usually by PathWeaver.
     * This looks in the deploy directory, e.g. for name "testtraj", "/home/lvuser/deploy/paths/testtraj.pf1.csv"
     * is the result (placed in "src/main/deploy/paths/testtraj.pf1.csv" in your project directory).
     *
     * @param name The name of the path
     * @return The absolute file of the trajectory
     */
    public static File getTrajectoryFile(String name) {
        return new File(Filesystem.getDeployDirectory(), "paths/" + name + ".pf1.csv");
    }

    /**
     * Load a Trajectory from file, at the path described by {@link #getTrajectoryFile(String)}
     * This call is expensive, and as such the result should be stored.
     *
     * @param name The name of the path
     * @return The Trajectory loaded from file.
     */
    public static Trajectory getTrajectory(String name) {
        return Pathfinder.readFromCSV(getTrajectoryFile(name));
    }

    /**
     * Load a left Trajectory from file, at the path described by {@link #getTrajectoryFile(String)}
     * This call is expensive, and as such the result should be stored.
     *
     * @param name The name of the path
     * @return The Trajectory loaded from file.
     */
    public static Trajectory getLeftTrajectory(String name) {
        return Pathfinder.readFromCSV(getTrajectoryFile(name + ".left"));
    }

    /**
     * Load a right Trajectory from file, at the path described by {@link #getTrajectoryFile(String)}
     * This call is expensive, and as such the result should be stored.
     *
     * @param name The name of the path
     * @return The Trajectory loaded from file.
     */
    public static Trajectory getRightTrajectory(String name) {
        return Pathfinder.readFromCSV(getTrajectoryFile(name + ".right"));
    }
}
