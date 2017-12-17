package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import school.androidgame.Entities.Player;
import school.androidgame.Gui.PlayTime;
import school.androidgame.Gui.Health;

/**
 * Created by kezab on 28.11.17.
 */

public class GuiManager {
    private GameManager game;
    private Health health;
    private PlayTime playtime;
    private Player player;
    private Context context;

    public GuiManager(GameManager game){
        this.game = game;
        this.player = game.player;
        this.playtime = new PlayTime(this.game.timeManager);
        this.health = new Health();
        this.context = game.context;

        this.playtime.setVisible(true);
        this.health.setVisible(true);
    }


    private void updateHealth(int value){
        this.health.setValue(value);
    }

    public void draw(Canvas canvas)
    {
        this.playtime.draw(canvas);
        this.health.draw(canvas);
    }

    public void update(float dt) {
        this.playtime.update(dt);
        this.health.update(this.player.getHealth());
    }
}
