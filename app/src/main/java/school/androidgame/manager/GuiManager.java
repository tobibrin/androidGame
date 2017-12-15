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
    private Health health;
    private PlayTime playtime;
    private Player player;
    private Context context;

    public GuiManager(Context context){
        this.playtime = new PlayTime();
        this.health = new Health();
        this.context = context;
    }

    public void setPlayer(Player p){
        this.player = p;
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
        this.health.update(dt);
    }
}
