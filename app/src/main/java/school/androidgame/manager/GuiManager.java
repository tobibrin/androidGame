package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;

import school.androidgame.Entities.Player;
import school.androidgame.Gui.Health;
import school.androidgame.Gui.PlayerPoints;

/**
 * Created by kezab on 28.11.17.
 */

public class GuiManager {
    private GameManager game;
    private PlayerPoints playerPoints;
    private Health health;
    private Player player;
    private Context context;

    public GuiManager(GameManager game){
        this.game = game;
        this.player = game.player;
        this.health = new Health();
        this.playerPoints = new PlayerPoints();
        this.context = game.context;

        this.playerPoints.setVisible(true);
        this.health.setVisible(true);
    }

    public void draw(Canvas canvas) {
        this.health.draw(canvas);
        this.playerPoints.draw(canvas);
    }

    public void update(float dt) {
        this.health.update(this.player.getHealth());
        this.playerPoints.update(this.player.getPoints());
    }
}
