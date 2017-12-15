package school.androidgame.manager;

import java.util.Timer;

/**
 * Created by kezab on 14.11.17.
 */

public class TimeManager {
    private static long totalTime = 0;
    private static long startTime = 0;

    public static void start(){
        startTime = System.currentTimeMillis();
    }

    public static void stop()
    {
        totalTime += System.currentTimeMillis() - startTime;
    }
    public static long getRelativeTime(){
        return System.currentTimeMillis() - startTime;
    }

    public static long getCurrentTime(){
        return System.currentTimeMillis();
    }

    private static long getTotalTime()
    {
        return System.currentTimeMillis() - startTime + totalTime;
    }

    private static void reset(){
        totalTime = 0;
        startTime = 0;
    }
}
