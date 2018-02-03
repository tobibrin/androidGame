package school.androidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import school.androidgame.Core.GameObject;
import school.androidgame.Utils.bitmap.colors.BitmapColor;
import school.androidgame.Utils.bitmap.colors.ObjectColorState;
import school.androidgame.GamePanel;
import school.androidgame.R;
import school.androidgame.Utils.Vector2D;
import school.androidgame.manager.GameManager;
import school.androidgame.manager.GyroscopicManager;
import school.androidgame.repositories.BitmapColorRepository;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends GameObject {

    private GameManager game;
    private Rect playerRect;
    private Paint playerPaint;

    private BitmapColorRepository bitmapColorRepository;

    final private float sensorTolerance = 0.0f;

    private int health;

    private Vector2D direction;

    private GyroscopicManager gyroscopicManager;

    private boolean playerIsAbleToMove;

    public Player(GameManager game, int x, int y, int health) {
        super();
        this.bitmapColorRepository = new BitmapColorRepository();
        this.game = game;
        this.setX(x);
        this.setY(y);
        this.health = health;
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        int size = (int) (minValue * 0.05f * GamePanel.DENSITY);

        this.setWidth(size);
        this.setHeight(size);
        this.setName("player");
        this.setVisible(true);
        this.playerIsAbleToMove = false;

        this.setupPlayerImages();

        this.playerPaint = new Paint();
        this.playerRect = new Rect();

        this.updatePlayerRect();

        this.gyroscopicManager = new GyroscopicManager(this.game.context);
        this.direction = new Vector2D(0, 0);
    }

    private void setupPlayerImages() {

        Bitmap greenImage = BitmapFactory.decodeResource(this.game.context.getResources(), R.drawable.player_green);
        greenImage = Bitmap.createScaledBitmap(greenImage, this.getWidth(), this.getHeight(), false);

        Bitmap blueImage = BitmapFactory.decodeResource(this.game.context.getResources(), R.drawable.player_blue);
        blueImage = Bitmap.createScaledBitmap(blueImage, this.getWidth(), this.getHeight(), false);

        Bitmap redImage = BitmapFactory.decodeResource(this.game.context.getResources(), R.drawable.player_red);
        redImage = Bitmap.createScaledBitmap(redImage, this.getWidth(), this.getHeight(), false);

        BitmapColor bitmapColorGreen = new BitmapColor(greenImage, ObjectColorState.COLOR_STATE_GREEN);
        BitmapColor bitmapColorBlue = new BitmapColor(blueImage, ObjectColorState.COLOR_STATE_BLUE);
        BitmapColor bitmapColorRed = new BitmapColor(redImage, ObjectColorState.COLOR_STATE_RED);

        this.bitmapColorRepository.addBitmapColors(new BitmapColor[]{bitmapColorBlue, bitmapColorGreen, bitmapColorRed});
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


                if (Math.abs(xDirection) > sensorTolerance) {

                    this.direction.set(xDirection, this.direction.y);

                } else {

                    this.direction.set(0, this.direction.y);
                }

                if (Math.abs(yDirection) > sensorTolerance) {

                    this.direction.set(this.direction.x, yDirection);

                } else {

                    this.direction.set(this.direction.x, 0);
                }
            }
        }


        float newX = this.getX() + (dt * this.direction.x * GamePanel.MIN_WIDTH_HEIGHT * 0.8f * GamePanel.DENSITY);

        this.setX(newX);
        this.setY(this.getY() + (dt * this.direction.y * GamePanel.MIN_WIDTH_HEIGHT * 0.8f * GamePanel.DENSITY));

        this.updatePlayerRect();
    }

    @Override
    public void draw(Canvas canvas) {

        BitmapColor playerBitmapColor = this.bitmapColorRepository.getBitmapAtCurrentIndex();

        if (playerBitmapColor != null) {
            Bitmap bitmap = playerBitmapColor.getBitmap();
            if (bitmap != null) {
                float xCenter = this.getX() - (this.getWidth() / 2.0f);
                float yCenter = this.getY() - (this.getHeight() / 2.0f);
                canvas.drawBitmap(bitmap,xCenter, yCenter, this.playerPaint);
            }
        }
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

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int amount) {
        this.health = amount;
    }

    public void damage(int damage) {
        this.health -= damage;

        if (this.health <= 0) {

            this.health = 0;
//            this.game.gameOver();
        }
    }

    public BitmapColorRepository getBitmapColorRepository() {
        return this.bitmapColorRepository;
    }

    public void heal(int amount) {
        this.health += amount;
    }
}
