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

    private Bitmap bitmap;

    public static final int halfSize = 64;
    public static final int size = 128;

    public HeartPickUp(GameManager gameManager, Vector2D spawnPosition) {
        super();
        this.setWidth(HeartPickUp.size);
        this.setHeight(HeartPickUp.size);
        this.position.x = spawnPosition.x;
        this.position.y = spawnPosition.y;
        this.bitmap = BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.heart);
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, HeartPickUp.size, HeartPickUp.size, false);
        this.rect = new Rect(
                ((int) this.position.x) - HeartPickUp.halfSize,
                ((int) this.position.y) - HeartPickUp.halfSize,
                ((int) this.position.x) + HeartPickUp.halfSize,
                ((int) this.position.y) + HeartPickUp.halfSize
        );
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void onPlayerCollision(Player player)
    {
        player.addHealth(1);
        player.onPickupCollected(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (int) this.position.x - HeartPickUp.halfSize, (int) this.position.y - HeartPickUp.halfSize, null);
    }

    @Override
    public boolean isColliding(Rect otherRect) {
        boolean isColliding = false;
        if (this.rect != null) {
            isColliding = this.rect.intersect(otherRect);
        }
        return isColliding;
    }
}
