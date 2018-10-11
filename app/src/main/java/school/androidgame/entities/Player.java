package school.androidgame.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

import school.androidgame.core.CollideAbleGameObject;
import school.androidgame.utils.bitmap.colors.BitmapColor;
import school.androidgame.utils.bitmap.colors.ObjectColorState;
import school.androidgame.GamePanel;
import school.androidgame.R;
import school.androidgame.utils.Vector2D;
import school.androidgame.manager.GameManager;
import school.androidgame.manager.GyroscopicManager;
import school.androidgame.repositories.BitmapColorRepository;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends CollideAbleGameObject {

    private final PointF spawnPoint;
    private final int virtualSize = 64;
    private GameManager game;
    private Paint playerPaint;
    private BitmapColorRepository bitmapColorRepository;

    private float speedFactor;
    private Paint playerRectPaint;
    private int health;
    private int points;
    private Vector2D direction;
    private GyroscopicManager gyroscopicManager;
    private boolean playerIsAbleToMove;

    public Player(GameManager game, int health) {
        super();
        this.bitmapColorRepository = new BitmapColorRepository();
        this.game = game;

        this.speedFactor = 1;

        this.spawnPoint = new PointF(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2.0f);
        this.direction = new Vector2D(0, 0);
        this.spawnPlayer();

        this.playerRectPaint = new Paint();
        this.playerRectPaint.setStyle(Paint.Style.STROKE);
        this.playerRectPaint.setColor(Color.RED);
        this.health = health;
        this.points = 0;
        int scaledSize = (int) (this.virtualSize * GamePanel.DENSITY);
        this.setWidth(scaledSize);
        this.setHeight(scaledSize);
        this.setName("player");
        this.setVisible(true);
        this.playerIsAbleToMove = false;

        this.setupPlayerImages();

        this.playerPaint = new Paint();
        this.rect = new Rect();

        this.updatePlayerRect();
        this.gyroscopicManager = new GyroscopicManager(this.game);
    }

    private void setupPlayerImages() {

        Bitmap greenImage = BitmapFactory.decodeResource(this.game.getContext().getResources(), R.drawable.player_green);
        greenImage = Bitmap.createScaledBitmap(greenImage, this.getWidth(), this.getHeight(), false);

        Bitmap blueImage = BitmapFactory.decodeResource(this.game.getContext().getResources(), R.drawable.player_blue);
        blueImage = Bitmap.createScaledBitmap(blueImage, this.getWidth(), this.getHeight(), false);

        Bitmap redImage = BitmapFactory.decodeResource(this.game.getContext().getResources(), R.drawable.player_red);
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
                float xDirection = (roll * 8.0f) / (float) Math.PI;
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

                this.direction.set((Math.abs(xDirection) > 0) ? xDirection : 0, this.direction.y);
                this.direction.set(this.direction.x, (Math.abs(yDirection) > 0) ? yDirection : 0);
            }
        }

        if (dt != 0 && GamePanel.MIN_WIDTH_HEIGHT != 0 && GamePanel.DENSITY != 0) {
            if (Math.abs(this.direction.x) > 0) {
                float newX = this.getX() + (dt * this.direction.x * GamePanel.MIN_WIDTH_HEIGHT * GamePanel.DENSITY * this.speedFactor);
                this.setX(newX);
            }

            if (Math.abs(this.direction.y) > 0) {
                float newY = this.getY() + (dt * this.direction.y * GamePanel.MIN_WIDTH_HEIGHT * GamePanel.DENSITY * this.speedFactor);
                this.setY(newY);
            }

            this.updatePlayerRect();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        BitmapColor playerBitmapColor = this.bitmapColorRepository.getBitmapColorAtCurrentIndex();

        if (playerBitmapColor != null) {
            Bitmap bitmap = playerBitmapColor.getBitmap();
            if (bitmap != null) {
                float xCenter = this.getX() - (this.getWidth() / 2.0f);
                float yCenter = this.getY() - (this.getHeight() / 2.0f);
                canvas.drawBitmap(bitmap, xCenter, yCenter, this.playerPaint);
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

    public int getPoints() {
        return this.points;
    }

    public void onActionDown(MotionEvent event) {
        this.playerIsAbleToMove = this.intersectsPlayer(event.getX(), event.getY());
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

        this.rect.set((int) (this.getX() - playerHalfWidth),
                (int) (this.getY() - playerHalfHeight),
                (int) (this.getX() + playerHalfWidth),
                (int) (this.getY() + playerHalfHeight));
    }

    private void spawnPlayer() {
        this.setX(this.spawnPoint.x);
        this.setY(this.spawnPoint.y);
    }

    public int getHealth() {
        return this.health;
    }

    public void addPoint(int amount) {
        this.points += amount;
    }


    public void damage(int damage) {
        this.health -= damage;

        if (this.health <= 0) {
            this.health = 0;
        }
    }

    public void onSpeedChange(float factor, int duration) {
        Timer timer = new Timer();
        this.speedFactor += factor;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                speedFactor = 1;
            }
        }, duration);
    }

    public void nextBitmapColor() {
        if (this.bitmapColorRepository != null)
            this.bitmapColorRepository.nextBitMapColor();
    }

    public Rect getPlayerRect() {
        return this.rect;
    }

    public BitmapColorRepository getBitmapColorRepository() {
        return this.bitmapColorRepository;
    }

    public void addHealth(int value) {
        this.health += value;
    }
}
