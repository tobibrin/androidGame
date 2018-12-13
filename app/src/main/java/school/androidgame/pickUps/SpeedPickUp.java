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
        this.position.x = spawnPosition.x;
        this.position.y = spawnPosition.y;
        this.setBitmap(BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.bolt));
        this.setBitmap(Bitmap.createScaledBitmap(this.getBitmap(), SpeedPickUp.size, SpeedPickUp.size, false));
        this.rect = new Rect(
                ((int) this.position.x) - SpeedPickUp.halfSize,
                ((int) this.position.y) - SpeedPickUp.halfSize,
                ((int) this.position.x) + SpeedPickUp.halfSize,
                ((int) this.position.y) + SpeedPickUp.halfSize
        );
        this.setVisible(true);
    }

    @Override
    public void onPlayerCollision(Player player) {

        player.onSpeedChange(1, 10000);
        player.onPickupCollected(this);
        this.destroy();
    }
}

