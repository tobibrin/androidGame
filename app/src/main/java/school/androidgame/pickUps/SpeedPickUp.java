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

public class SpeedPickUp extends CollideableGameObject implements ICollectableObject {

    private Bitmap bitmap;

    public static final int halfSize = 64;
    public static final int size = 128;

    public SpeedPickUp(GameManager gameManager, Vector2D spawnPosition) {
        this.setWidth(SpeedPickUp.size);
        this.setHeight(SpeedPickUp.size);
        this.position.x = spawnPosition.x;
        this.position.y = spawnPosition.y;
        this.bitmap = BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.bolt);
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, SpeedPickUp.size, SpeedPickUp.size, false);
        this.rect = new Rect(
                ((int) this.position.x) - SpeedPickUp.halfSize,
                ((int) this.position.y) - SpeedPickUp.halfSize,
                ((int) this.position.x) + SpeedPickUp.halfSize,
                ((int) this.position.y) + SpeedPickUp.halfSize
        );
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (int) this.position.x - SpeedPickUp.halfSize, (int) this.position.y - SpeedPickUp.halfSize, null);
    }

    @Override
    public void onPlayerCollision(Player player) {
        this.destroy();
        player.onSpeedChange(1, 10000);
        player.onPickupCollected(this);
    }

    private void destroy() {
        this.bitmap.recycle();
        this.bitmap = null;
    }
}

