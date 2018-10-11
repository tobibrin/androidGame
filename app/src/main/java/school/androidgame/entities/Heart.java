package school.androidgame.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.R;
import school.androidgame.core.CollideAbleGameObject;
import school.androidgame.interfaces.ICollectableObject;
import school.androidgame.manager.GameManager;
import school.androidgame.utils.Vector2D;

public class Heart extends CollideAbleGameObject implements ICollectableObject {

    private Bitmap bitmap;

    public static final int halfSize = 64;
    public static final int size = 128;

    public Heart(GameManager gameManager, Vector2D spawnPosition) {
        this.setWidth(Heart.size);
        this.setHeight(Heart.size);
        this.setX(spawnPosition.x);
        this.setY(spawnPosition.y);
        this.bitmap = BitmapFactory.decodeResource(gameManager.getContext().getResources(), R.drawable.heart);
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, Heart.size, Heart.size, false);
        this.rect = new Rect(
                ((int) this.getX()) - Heart.halfSize,
                ((int) this.getY()) - Heart.halfSize,
                ((int) this.getX()) + Heart.halfSize,
                ((int) this.getY()) + Heart.halfSize
        );
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void onPlayerCollision(Player player) {
        player.addHealth(1);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (int) this.getX() - Heart.halfSize, (int) this.getY() - Heart.halfSize, null);
    }
}
