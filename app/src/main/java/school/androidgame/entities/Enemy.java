package school.androidgame.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import school.androidgame.core.CollideableGameObject;
import school.androidgame.R;
import school.androidgame.utils.bitmap.colors.BitmapColor;
import school.androidgame.utils.bitmap.colors.ObjectColorState;
import school.androidgame.GamePanel;
import school.androidgame.repositories.BitmapColorRepository;

/**
 * Created by Tobi on 28.10.2017.
 */

public class Enemy extends CollideableGameObject {

    private Context context;
    private BitmapColorRepository bitmapColorRepository;
    private Bitmap enemyBitmap;
    private PointF direction;
    private float speed;
    private boolean isInScreen;

    public Enemy(Context context, float x, float y, float speed) {
        this.context = context;
        this.position.x = x;
        this.position.y = y;

        this.bitmapColorRepository = new BitmapColorRepository();
        this.setupEnemyImages();
        this.bitmapColorRepository.setRandomIndex();

        float width = this.initWidth();

        this.setWidth((int)width);
        this.setHeight((int)width);

        this.speed = this.initSpeed(speed);
        this.direction = new PointF(0 ,0);

        this.isInScreen = true;

        BitmapColor enemyBitmapColor = this.bitmapColorRepository.getBitmapColorAtCurrentIndex();
        this.enemyBitmap = Bitmap.createScaledBitmap(enemyBitmapColor.getBitmap(), this.getWidth(), this.getHeight(), false);

        this.rect = new Rect();
        this.updateRect();
    }

    private void setupEnemyImages() {

        Bitmap greenImage = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy_green);
        BitmapColor bitmapColorGreen = new BitmapColor(greenImage, ObjectColorState.COLOR_STATE_GREEN);

        Bitmap blueImage = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy_blue);
        BitmapColor bitmapColorBlue = new BitmapColor(blueImage, ObjectColorState.COLOR_STATE_BLUE);

        Bitmap redImage = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy_red);
        BitmapColor bitmapColorRed = new BitmapColor(redImage, ObjectColorState.COLOR_STATE_RED);

        this.bitmapColorRepository.addBitmapColors(new BitmapColor[] {bitmapColorGreen, bitmapColorBlue, bitmapColorRed});

    }

    private float initSpeed(float speed) {
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        return speed * (minValue / 1000) * (GamePanel.DENSITY);
    }

    private float initWidth() {
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        return minValue * 0.025f * GamePanel.DENSITY;
    }

    private boolean stillInScreen() {
        boolean isInWidth = this.position.x > 0 && this.position.x < GamePanel.WIDTH;
        boolean isInHeight = this.position.y > 0 && this.position.y < GamePanel.HEIGHT + 100;
        return isInWidth && isInHeight;
    }

    @Override
    public void update(float dt) {

        this.position.x = this.position.x + (this.direction.x * dt * speed);
        this.position.y = this.position.y + (this.direction.y * dt * speed);

        this.updateRect();

        if(!this.stillInScreen()) {
            this.isInScreen = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.isVisible()) {
            float xCenter = this.position.x - (this.getWidth() / 2.0f);
            float yCenter = this.position.y - (this.getHeight() / 2.0f);
            canvas.drawBitmap(enemyBitmap, xCenter, yCenter, null);
        }
    }

    public boolean getIsInScreen() {
        return this.isInScreen;
    }

    public void setDirection(PointF direction) {
        this.direction = direction;
    }

    public BitmapColorRepository getBitmapColorRepository() {
        return this.bitmapColorRepository;
    }

    public void destroy() {
        this.enemyBitmap.recycle();
        this.enemyBitmap = null;
    }

    private void updateRect(){
        float enemyX = this.position.x;
        float enemyY = this.position.y;

        float enemyHalfWidth = this.getWidth() / 2.0f;
        float enemyHalfHeight = this.getHeight() / 2.0f;

        this.rect.set((int)(enemyX - enemyHalfWidth),
                (int)(enemyY - enemyHalfHeight),
                (int)(enemyX + enemyHalfWidth),
                (int)(enemyY + enemyHalfHeight));
    }
}
