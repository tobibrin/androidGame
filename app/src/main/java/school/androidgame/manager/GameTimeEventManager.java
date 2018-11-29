package school.androidgame.manager;

import java.util.Timer;

import school.androidgame.entities.Player;
import school.androidgame.timeContext.PlayerChangeColorTimerTask;
import school.androidgame.timeContext.StopWatch;

/**
 * Created by tobi on 27.02.18.
 */

public class GameTimeEventManager {

    private StopWatch stopWatch;

    public GameTimeEventManager() {
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
    }

    public void registerPlayerChangeColor(Player player, int duration) {
        Timer playerChangeColorTimer = new Timer();
        playerChangeColorTimer.scheduleAtFixedRate(new PlayerChangeColorTimerTask(player), 0, duration);
    }

    public long getElapsedGameTimeInSeconds() {
        return this.stopWatch.getPassedSeconds();
    }
}
