package school.androidgame.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.Timer;

import school.androidgame.animations.AlphaAnimation;
import school.androidgame.animations.Frame;
import school.androidgame.animations.ITransition;
import school.androidgame.animations.TextAnimation;
import school.androidgame.core.CollideableGameObject;
import school.androidgame.pickUps.PickUp;
import school.androidgame.timeContext.PlayerSpeedTimerTask;
import school.androidgame.core.ICollectableObject;
import school.androidgame.utils.bitmap.colors.BitmapColor;
import school.androidgame.utils.bitmap.colors.ObjectColorState;
import school.androidgame.GamePanel;
import school.androidgame.R;
import school.androidgame.utils.Vector2D;
import school.androidgame.manager.GameManager;
import school.androidgame.repositories.BitmapColorRepository;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends CollideableGameObject {

    private final PointF spawnPoint;
    private final int virtualSize = 64;
    private GameManager game;
    private Paint playerPaint;
    private BitmapColorRepository bitmapColorRepository;

    private Vector2D destination;
    private float speedFactor;
    private Paint playerRectPaint;
    private int health;
    private int points;
    private Vector2D direction;
    private AlphaAnimation damageAnimation;
    private LinkedList<TextAnimation> getPointAnimations;
    private LinkedList<TextAnimation> pickUpAnimations;

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

        this.setupPlayerImages();

        this.playerPaint = new Paint();
        this.rect = new Rect();

        this.updatePlayerRect();
        this.damageAnimation = null;
        this.getPointAnimations = new LinkedList<TextAnimation>();
        this.pickUpAnimations = new LinkedList<TextAnimation>();
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

        if (this.destination != null) {
            this.direction = this.position.getDirection(this.destination, 10f);
        }

        float newX = this.position.x + (dt * this.direction.x * GamePanel.MIN_WIDTH_HEIGHT * GamePanel.DENSITY * this.speedFactor / 2.5f);
        if (newX > 0 && newX < GamePanel.WIDTH) {
            this.position.x = newX;
        }
        float newY = this.position.y + (dt * this.direction.y * GamePanel.MIN_WIDTH_HEIGHT * GamePanel.DENSITY * this.speedFactor / 2.5f);
        if (newY > 0 && newY < GamePanel.HEIGHT) {
            this.position.y = newY;
        }

        this.updatePlayerRect();

        if (this.damageAnimation != null)
            this.damageAnimation.update();

        if (this.pickUpAnimations != null)
        {
            for (TextAnimation anim : this.pickUpAnimations) {
                if (anim != null)
                    anim.update();
            }
        }


        if (this.getPointAnimations != null && this.getPointAnimations.size() > 0) {
            for (TextAnimation anim : this.getPointAnimations) {
                if (anim != null)
                    anim.update();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        BitmapColor playerBitmapColor = this.bitmapColorRepository.getBitmapColorAtCurrentIndex();
        Bitmap playerBitmap = null;
        Paint paint = this.playerPaint;
        if (playerBitmapColor != null) {
            playerBitmap = playerBitmapColor.getBitmap();
        }

        if (this.damageAnimation != null && this.damageAnimation.isStarted()) {
            Frame<ITransition<Paint>> currentFrame = this.damageAnimation.getCurrentFrame();
            if (currentFrame != null && currentFrame.getFrameObject() != null)
                currentFrame.getFrameObject().transform(paint);
        }

        if (playerBitmap != null) {
            float xCenter = this.position.x - (this.getWidth() / 2.0f);
            float yCenter = this.position.y - (this.getHeight() / 2.0f);
            canvas.drawBitmap(playerBitmap, xCenter, yCenter, paint);
        }

        if (this.pickUpAnimations != null && this.pickUpAnimations.size() > 0) {
            for (TextAnimation anim : this.pickUpAnimations) {

                if (anim.isStarted()) {
                    Frame<ITransition<Canvas>> currentFrame = anim.getCurrentFrame();
                    if (currentFrame != null && currentFrame.getFrameObject() != null)
                        currentFrame.getFrameObject().transform(canvas);
                }
            }
        }

        if (this.getPointAnimations != null && this.getPointAnimations.size() > 0) {
            for (TextAnimation anim : this.getPointAnimations) {

                if (anim.isStarted()) {
                    Frame<ITransition<Canvas>> currentFrame = anim.getCurrentFrame();
                    if (currentFrame != null && currentFrame.getFrameObject() != null)
                        currentFrame.getFrameObject().transform(canvas);
                }
            }
        }
    }

    public int getPoints() {
        return this.points;
    }

    private void updatePlayerRect() {
        float playerHalfWidth = this.getWidth() / 2.0f;
        float playerHalfHeight = this.getHeight() / 2.0f;

        this.rect.set((int) (this.position.x - playerHalfWidth),
                (int) (this.position.y - playerHalfHeight),
                (int) (this.position.x + playerHalfWidth),
                (int) (this.position.y + playerHalfHeight));
    }

    private void spawnPlayer() {
        this.position.x = this.spawnPoint.x;
        this.position.y = this.spawnPoint.y;
    }

    public int getHealth() {
        return this.health;
    }

    public void addPoint(int amount) {
        this.points += amount;
        this.onGetPoint(amount);
    }


    public void damage(int damage) {
        this.health -= damage;

        if (this.health <= 0) {
            this.health = 0;
        } else {
            this.damageAnimation = AlphaAnimation.CreateDamageAnimation();
            this.damageAnimation.AnimationFinished.addObserver((o, a) -> this.damageAnimation = null);
            this.damageAnimation.Start();
        }
    }

    public void onSpeedChange(float additionToFactor, int duration) {
        Timer timer = new Timer();
        this.speedFactor += additionToFactor;
        timer.schedule(new PlayerSpeedTimerTask(this, additionToFactor), duration);
    }

    public void nextBitmapColor() {
        if (this.bitmapColorRepository != null)
            this.bitmapColorRepository.nextBitMapColor();
    }

    public void onTouch(MotionEvent event) {
        if (this.destination == null) {
            this.destination = new Vector2D(event.getX(), event.getY());
        } else {
            this.destination.x = event.getX();
            this.destination.y = event.getY();
        }
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

    public float getSpeedFactor() {
        return this.speedFactor;
    }

    public void setSpeedFactor(float factor) {
        if (factor < 1) {
            this.speedFactor = 1;
        } else {
            this.speedFactor = factor;
        }
    }

    public void onPickupCollected(PickUp pickUp)
    {
        TextAnimation anim = TextAnimation.CreatePickupAnimation(true, 1,pickUp.getBitmap(), this);
        this.pickUpAnimations.add(anim);

        anim.AnimationFinished.addObserver((obs, o) -> this.removePointAnimation(anim));

        anim.Start();
    }

    public void onGetPoint(int amount) {
        TextAnimation anim = TextAnimation.CreateGetPointAnimation(true, amount, null, this);
        this.getPointAnimations.add(anim);

        anim.AnimationFinished.addObserver((obs, o) -> this.removePointAnimation(anim));

        anim.Start();
    }

    public void removePointAnimation(TextAnimation animation) {
        this.getPointAnimations.remove(animation);
    }
}
