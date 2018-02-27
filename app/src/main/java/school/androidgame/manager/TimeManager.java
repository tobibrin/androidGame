package school.androidgame.manager;

/**
 * Created by kezab on 14.11.17.
 */

public class TimeManager {

    private long startTime = 0;

    public GameManager game;

    public TimeManager(GameManager game){
        this.game = game;
        this.start();
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public long getPlayTimeInMillis(){
        return System.currentTimeMillis() - startTime;
    }

}
