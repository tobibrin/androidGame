package school.androidgame.pickUps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.R;
import school.androidgame.core.CollideableGameObject;
import school.androidgame.core.ICollectableObject;
import school.androidgame.entities.Player;
import school.androidgame.manager.GameManager;
import school.androidgame.utils.Vector2D;

public class SpeedPickUp extends PickUp {

    public static final int halfSize = 64;
    public static final int size = 128;

    public SpeedPickUp(GameManager gameManager, Vector2D spawnPosition) {
        this.setWidth(SpeedPickUp.size);
        this.setHeight(SpeedPickUp.size);
        this.setX(spawnPosition.x);
        this.setY(spawnPosition.y);
        this.setBitmap(BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.bolt));
        this.setBitmap(Bitmap.createScaledBitmap(this.getBitmap(), SpeedPickUp.size, SpeedPickUp.size, false));
        this.rect = new Rect(
                ((int) this.getX()) - SpeedPickUp.halfSize,
                ((int) this.getY()) - SpeedPickUp.halfSize,
                ((int) this.getX()) + SpeedPickUp.halfSize,
                ((int) this.getY()) + SpeedPickUp.halfSize
        );
    }

    @Override
    public void onPlayerCollision(Player player) {

        player.onSpeedChange(1, 10000);
        player.onPickupCollected(this);
        this.destroy();
    }
}

