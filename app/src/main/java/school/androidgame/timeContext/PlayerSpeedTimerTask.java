package school.androidgame.timeContext;

import java.util.TimerTask;

import school.androidgame.gameObjects.Player;

public class PlayerSpeedTimerTask extends TimerTask {

    private Player player;
    private float factor;

    public PlayerSpeedTimerTask(Player player, float factor) {
        this.player = player;
        this.factor = factor;
    }

    @Override
    public void run() {
        this.player.setSpeedFactor(this.player.getSpeedFactor() - this.factor);
    }
}
