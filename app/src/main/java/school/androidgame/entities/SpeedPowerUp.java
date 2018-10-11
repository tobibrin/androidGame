package school.androidgame.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.R;
import school.androidgame.core.CollideAbleGameObject;
import school.androidgame.entities.player.Player;
import school.androidgame.interfaces.ICollectableObject;
import school.androidgame.manager.GameManager;
import school.androidgame.utils.Vector2D;

public class SpeedPowerUp extends CollideAbleGameObject implements ICollectableObject {

    private Bitmap bitmap;

    public static final int halfSize = 64;
    public static final int size = 128;

    public SpeedPowerUp(GameManager gameManager, Vector2D spawnPosition) {
        this.setWidth(SpeedPowerUp.size);
        this.setHeight(SpeedPowerUp.size);
        this.setX(spawnPosition.x);
        this.setY(spawnPosition.y);
        this.bitmap = BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.speed_power_up);
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, SpeedPowerUp.size, SpeedPowerUp.size, false);
        this.rect = new Rect(
                ((int) this.getX()) - SpeedPowerUp.halfSize,
                ((int) this.getY()) - SpeedPowerUp.halfSize,
                ((int) this.getX()) + SpeedPowerUp.halfSize,
                ((int) this.getY()) + SpeedPowerUp.halfSize
        );
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (int) this.getX() - SpeedPowerUp.halfSize, (int) this.getY() - SpeedPowerUp.halfSize, null);
    }

    @Override
    public void onPlayerCollision(Player player) {
        this.destroy();
        player.onSpeedChange(1, 10000);
    }

    private void destroy() {
        this.bitmap.recycle();
        this.bitmap = null;
    }
}

