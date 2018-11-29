package school.androidgame.timeContext;

/**
 * Created by tobi on 27.02.18.
 */

public class StopWatch {

    private long startTime;
    private boolean isRunning;
    private long passedMilliseconds;

    public StopWatch() {
        this.isRunning = false;
        this.passedMilliseconds = 0;
    }

    public void start() {
        this.isRunning = true;
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.isRunning = false;
    }

    public long getPassedMilliseconds() {

        long now = System.currentTimeMillis();

        if (this.isRunning)
            passedMilliseconds = now - this.startTime;

        return this.passedMilliseconds;
    }

    public long getPassedSeconds() {
        return this.getPassedMilliseconds() / 1000;
    }

}
