package school.androidgame.manager;

import java.util.Timer;

/**
 * Created by kezab on 14.11.17.
 */

public class TimeManager {
    private long totalTime = 0;
    private long startTime = 0;

    public GameManager game;

    public TimeManager(GameManager game){
        this.game = game;
        this.start();
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        totalTime += System.currentTimeMillis() - startTime;
    }
    public long getRelativeTime(){
        return System.currentTimeMillis() - startTime;
    }

    public long getCurrentTime(){
        return System.currentTimeMillis();
    }

    private long getTotalTime()
    {
        return System.currentTimeMillis() - startTime + totalTime;
    }

    public void reset(){
        totalTime = 0;
        this.start();
    }
}
