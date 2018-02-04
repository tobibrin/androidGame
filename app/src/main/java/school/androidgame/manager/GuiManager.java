package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import school.androidgame.Entities.Player;
import school.androidgame.Gui.PlayTime;
import school.androidgame.Gui.Health;
import school.androidgame.Gui.PlayerPoints;

/**
 * Created by kezab on 28.11.17.
 */

public class GuiManager {
    private GameManager game;
    private PlayerPoints playerPoints;
    private Health health;
    private PlayTime playtime;
    private Player player;
    private Context context;

    public GuiManager(GameManager game){
        this.game = game;
        this.player = game.player;
        this.playtime = new PlayTime(this.game.timeManager);
        this.health = new Health();
        this.playerPoints = new PlayerPoints();
        this.context = game.context;

        this.playerPoints.setVisible(true);
        this.playtime.setVisible(true);
        this.health.setVisible(true);
    }

    public void draw(Canvas canvas)
    {
        this.playtime.draw(canvas);
        this.health.draw(canvas);
        this.playerPoints.draw(canvas);
    }

    public void update(float dt) {
        this.playtime.update(dt);
        this.health.update(this.player.getHealth());
        this.playerPoints.update(this.player.getPoints());
    }
}
