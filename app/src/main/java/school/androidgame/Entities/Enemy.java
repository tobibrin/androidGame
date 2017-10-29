package school.androidgame.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;

import school.androidgame.Core.GameObject;
import school.androidgame.GamePanel;
import school.androidgame.R;

/**
 * Created by Tobi on 28.10.2017.
 */

public class Enemy extends GameObject {

    private Bitmap enemyBitmap;
    private PointF direction;
    private float speed;

    private boolean isAlive;


    public Enemy(Context context, float x, float y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(32);
        this.setHeight(32);

        this.speed = 300;
        this.direction = new PointF(0 ,-0.5f);
        this.isAlive = true;

        this.enemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_enemy);
        this.enemyBitmap = Bitmap.createScaledBitmap(this.enemyBitmap, this.getWidth(), this.getHeight(), false);
    }

    private boolean stillInScreen() {
        boolean isInWidth = this.getX() > 0 && this.getX() < GamePanel.WIDTH;
        boolean isInHeight = this.getY() > 0 && this.getY() < GamePanel.HEIGHT;
        return isInWidth && isInHeight;
    }

    @Override
    public void update(float dt) {

        this.setX(this.getX() + (this.direction.x * dt * speed));
        this.setY(this.getY() + (this.direction.y * dt * speed));

        if(!this.stillInScreen()) {
            this.isAlive = false;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        float xCenter = this.getX() - (this.getWidth() /  2);
        float yCenter = this.getY() - (this.getHeight() / 2);
        canvas.drawBitmap(enemyBitmap, xCenter, yCenter, null);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setDirection(PointF direction) {
        this.direction = direction;
    }
}
