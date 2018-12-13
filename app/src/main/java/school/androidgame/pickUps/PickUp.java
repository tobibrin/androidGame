package school.androidgame.pickUps;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import school.androidgame.core.CollideableGameObject;
import school.androidgame.entities.Player;

public abstract class PickUp extends CollideableGameObject {
    private Bitmap bitmap;

    public void setBitmap(Bitmap b)
    {
        this.bitmap = b;
    }
    public Bitmap getBitmap()
    {
        return this.bitmap;
    }

    public void destroy()
    {
        this.setVisible(false);
    }

    public void draw(Canvas canvas) {
        if(this.isVisible() && this.getBitmap() != null)
            canvas.drawBitmap(this.bitmap, (int) this.getPosition().x - HeartPickUp.halfSize, (int) this.getPosition().y - HeartPickUp.halfSize, null);
    }

    public abstract void onPlayerCollision(Player player);

    public void update(float dt){}
}
