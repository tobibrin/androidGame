package school.androidgame.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import school.androidgame.Core.GameObject;
import school.androidgame.GamePanel;
import school.androidgame.R;
import school.androidgame.Utils.Tools;

/**
 * Created by Tobi on 28.10.2017.
 */

public class Enemy extends GameObject {

    private Player player;

    private Paint enemyPaint;
    private Rect enemyRect;
    private Bitmap enemyBitmap;
    private PointF direction;
    private float speed;

    private boolean isInScreen;
    private boolean hitPlayer;

    public Enemy(Context context, Player player, float x, float y, int color) {
        this.setX(x);
        this.setY(y);

        float width = this.initWidth();

        this.setWidth((int)width);
        this.setHeight((int)width);

        this.speed = this.initSpeed();
        this.direction = new PointF(0 ,0);

        this.isInScreen = true;
        this.hitPlayer = false;

        this.enemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_enemy);

        this.enemyBitmap = Tools.replaceColor(this.enemyBitmap, Color.BLACK, Color.RED);

        this.enemyBitmap = Bitmap.createScaledBitmap(this.enemyBitmap, this.getWidth(), this.getHeight(), false);

        this.player = player;
        this.enemyRect = new Rect();
        this.enemyPaint = new Paint();
        this.enemyPaint.setColor(Color.BLACK);
        this.updateRect();
    }

    private float initSpeed() {
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        return 150 * (minValue / 1000) * (GamePanel.DENSITY);
    }

    private float initWidth() {
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        return minValue * 0.025f * GamePanel.DENSITY;
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

        this.updateRect();

        if(!this.stillInScreen()) {
            this.isInScreen = false;
        }

        if(this.enemyPlayerCollisionCheck()) {
            this.hitPlayer = true;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.isVisible()) {
            float xCenter = this.getX() - (this.getWidth() / 2.0f);
            float yCenter = this.getY() - (this.getHeight() / 2.0f);
            canvas.drawBitmap(enemyBitmap, xCenter, yCenter, null);
        }
    }

    public boolean getIsInScreen() {
        return this.isInScreen;
    }

    public boolean getHitPlayer() { return this.hitPlayer;}

    public void setDirection(PointF direction) {
        this.direction = direction;
    }

    private boolean enemyPlayerCollisionCheck() {

        //TODO tolerance?
        return Rect.intersects(this.player.getPlayerRect(), this.enemyRect);
    }

    public void destroy() {
        this.enemyBitmap.recycle();
        this.enemyBitmap = null;
    }

    private void updateRect(){
        float enemyX = this.getX();
        float enemyY = this.getY();

        float enemyHalfWidth = this.getWidth() / 2.0f;
        float enemyHalfHeight = this.getHeight() / 2.0f;

        this.enemyRect.set((int)(enemyX - enemyHalfWidth),
                (int)(enemyY - enemyHalfHeight),
                (int)(enemyX + enemyHalfWidth),
                (int)(enemyY + enemyHalfHeight));
    }

}
