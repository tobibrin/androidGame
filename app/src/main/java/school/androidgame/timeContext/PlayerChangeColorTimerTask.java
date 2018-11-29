package school.androidgame.timeContext;

import java.util.TimerTask;

import school.androidgame.gameObjects.Player;

/**
 * Created by tobi on 27.02.18.
 */

public class PlayerChangeColorTimerTask extends TimerTask {

    private boolean shouldRun;
    private Player player;

    public PlayerChangeColorTimerTask(Player player) {
        this.shouldRun = true;
        this.player = player;
    }

    @Override
    public void run() {
        if (this.shouldRun && player != null)
            this.player.nextBitmapColor();
        else
            System.exit(0);
    }
}
