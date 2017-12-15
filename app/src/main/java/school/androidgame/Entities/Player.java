package school.androidgame.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;

import school.androidgame.Core.GameObject;
import school.androidgame.GamePanel;
import school.androidgame.R;
import school.androidgame.Utils.Vector2D;
import school.androidgame.manager.GyroscopicManager;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends GameObject {

    private Rect playerRect;
    private Bitmap playerImage;
    private Paint playerPaint;

    private Vector2D direction;

    private GyroscopicManager gyroscopicManager;

    private boolean playerIsAbleToMove;

    public Player(Context context, int x, int y) {
        super();
        this.setX(x);
        this.setY(y);

        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        int size = (int) (minValue * 0.05f * GamePanel.DENSITY);

        this.setWidth(size);
        this.setHeight(size);
        this.setName("player");
        this.setVisible(true);
        this.playerIsAbleToMove = false;

        this.playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        this.playerImage = Bitmap.createScaledBitmap(this.playerImage, this.getWidth(), this.getHeight(), false);

        this.playerPaint = new Paint();
        this.playerRect = new Rect();
        this.updatePlayerRect();

        this.gyroscopicManager = new GyroscopicManager(context);

        this.direction = new Vector2D(0, 0);
    }

    @Override
    public void update(float dt) {

        float[] orientation = gyroscopicManager.getOrientation();
        float[] startOrientation = gyroscopicManager.getStartOrientation();

        if (orientation != null && startOrientation != null) {
            float pitch = orientation[1];
            float roll = orientation[2];

            if (Math.abs(pitch) < Math.PI && Math.abs(roll) < (Math.PI / 2)) {

                float xDirection = (2.0f * roll * 4.0f) / (float) Math.PI;
                xDirection = Math.round(xDirection * 100.0f) / 100.0f;

                float yDirection = (pitch * 4.0f) / (float) Math.PI * -1.0f;
                yDirection = Math.round(yDirection * 100.0f) / 100.0f;

                if (xDirection < -1.0f) {
                    xDirection = -1.0f;
                } else if (xDirection > 1.0f) {
                    xDirection = 1.0f;
                }

                if (yDirection < -1.0f) {
                    yDirection = -1.0f;
                } else if (yDirection > 1.0f) {
                    yDirection = 1.0f;
                }

                if (Math.abs(xDirection) > 0.1f || Math.abs(yDirection) > 0.1f) {

                    this.direction.set(xDirection, yDirection);
                    System.out.println((xDirection + " / " + yDirection));
                } else {
                    this.direction.set(0,0);
                }
            }
        }


        float newX = this.getX() + (dt * this.direction.x * GamePanel.MIN_WIDTH_HEIGHT * 0.8f * GamePanel.DENSITY);
        System.out.println(newX);

        this.setX(newX);
        this.setY(this.getY() + (dt * this.direction.y * GamePanel.MIN_WIDTH_HEIGHT * 0.8f * GamePanel.DENSITY));

        this.updatePlayerRect();
    }

    @Override
    public void draw(Canvas canvas) {
        float xCenter = this.getX() - (this.getWidth() / 2.0f);
        float yCenter = this.getY() - (this.getHeight() / 2.0f);
        canvas.drawBitmap(playerImage, xCenter, yCenter, this.playerPaint);
    }

    public void onActionMove(MotionEvent event) {

        if (this.playerIsAbleToMove) {
            this.setX((int) event.getX());
            this.setY((int) event.getY());

            this.updatePlayerRect();
        }
    }

    public void onActionDown(MotionEvent event) {
        this.playerIsAbleToMove = this.intersectsPlayer(event.getX(), event.getY());
    }

    public Rect getPlayerRect() {
        return this.playerRect;
    }

    private boolean intersectsPlayer(float clickedX, float clickedY) {
        float playerPosX = this.getX();
        float playerPosY = this.getY();

        float halfPlayerWidth = this.getWidth() / 2;
        float halfPlayerHeight = this.getHeight() / 2;

        boolean isInWidth = clickedX >= playerPosX - halfPlayerWidth && clickedX <= playerPosX + halfPlayerWidth;
        boolean isInHeight = clickedY >= playerPosY - halfPlayerHeight && clickedY <= playerPosY + halfPlayerHeight;

        return isInWidth && isInHeight;
    }

    private void updatePlayerRect() {
        float playerHalfWidth = this.getWidth() / 2.0f;
        float playerHalfHeight = this.getHeight() / 2.0f;

        this.playerRect.set((int) (this.getX() - playerHalfWidth),
                (int) (this.getY() - playerHalfHeight),
                (int) (this.getX() + playerHalfWidth),
                (int) (this.getY() + playerHalfHeight));
    }

}
