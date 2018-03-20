package school.androidgame.manager;

import java.util.Timer;

import school.androidgame.Entities.Player;
import school.androidgame.timeContext.PlayerChangeColorTimerTask;
import school.androidgame.timeContext.StopWatch;

/**
 * Created by tobi on 27.02.18.
 */

public class GameTimeEventManager {

    private StopWatch stopWatch;
    private Timer playerChangeColorTimer;

    public GameTimeEventManager(Player player) {
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
        this.playerChangeColorTimer = new Timer();
        this.playerChangeColorTimer.schedule(new PlayerChangeColorTimerTask(player), 0, 3000);
    }

    public void stopGameTime() {
        this.stopWatch.stop();
    }

    public long getPassedMilliseconds() {
        return this.stopWatch.getPassedMilliseconds();
    }
}
