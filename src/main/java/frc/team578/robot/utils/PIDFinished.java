package frc.team578.robot.utils;

import java.util.function.Predicate;

public class PIDFinished<T> {
    private long lastChecked = 0;
    private long checkIntervalMillis = 0;
    private int successCount = 0;
    private int stableCounts = 0;
    private boolean finished = false;
    private Predicate<T> successTest;
    private boolean lastTest;

    public PIDFinished(long checkIntervalMillis, int stableCounts, Predicate<T> successTest) {
        this.checkIntervalMillis = checkIntervalMillis;
        this.stableCounts = stableCounts;
        this.successTest = successTest;
    }

    public boolean checkIfStable(T currentDeriv) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastChecked > checkIntervalMillis) {
            lastChecked = currentTime;

            this.lastTest = successTest.test(currentDeriv);
            if (lastTest) {
                successCount++;
            } else {
                successCount = 0;
            }
        }

        this.finished = successCount >= stableCounts;

        return this.finished;
    }


    public boolean getFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        return "PIDFinished{" +
                "lastChecked=" + lastChecked +
                ", checkIntervalMillis=" + checkIntervalMillis +
                ", successCount=" + successCount +
                ", stableCounts=" + stableCounts +
                ", lastTest=" + lastTest +
                ", finished=" + finished +
                '}';
    }
}
