package school.androidgame.pickUps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.R;
import school.androidgame.entities.Player;
import school.androidgame.manager.GameManager;
import school.androidgame.utils.Vector2D;

public class HeartPickUp extends PickUp {
    public static final int halfSize = 64;
    public static final int size = 128;

    public HeartPickUp(GameManager gameManager, Vector2D spawnPosition) {
        this.setWidth(HeartPickUp.size);
        this.setHeight(HeartPickUp.size);
        this.position.x = spawnPosition.x;
        this.position.y = spawnPosition.y;
        Bitmap b = BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.heart);
        this.setBitmap(Bitmap.createScaledBitmap(b, HeartPickUp.size, HeartPickUp.size, false));
        this.rect = new Rect(
                ((int) this.position.x) - HeartPickUp.halfSize,
                ((int) this.position.y) - HeartPickUp.halfSize,
                ((int) this.position.x) + HeartPickUp.halfSize,
                ((int) this.position.y) + HeartPickUp.halfSize
        );
        this.setVisible(true);
    }

    @Override
    public void onPlayerCollision(Player player)
    {
        player.addHealth(1);
        player.onPickupCollected(this);
        this.destroy();
    }

}
