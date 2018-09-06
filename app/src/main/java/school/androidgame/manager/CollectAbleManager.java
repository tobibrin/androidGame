package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;

import school.androidgame.entities.Player;
import school.androidgame.powerUps.SpeedPowerUp;
import school.androidgame.utils.Vector2D;

public class CollectAbleManager {

    private Player player;
    private Context context;
    private SpeedPowerUp testSpeedPowerUp;

    public CollectAbleManager(Context context, Player player) {
        this.context = context;
        this.player = player;
//        this.testSpeedPowerUp = new SpeedPowerUp(this.context, this.player, 10, new Vector2D(300,300));
    }

    public void update(float dt) {
//        this.testSpeedPowerUp.update(dt);
    }

    public void draw(Canvas canvas) {
//        this.testSpeedPowerUp.draw(canvas);
    }
}
