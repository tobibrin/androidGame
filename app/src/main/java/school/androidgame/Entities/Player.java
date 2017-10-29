package school.androidgame.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import school.androidgame.Core.GameObject;
import school.androidgame.R;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends GameObject {

    private Bitmap playerImage;
    private Paint playerPaint;

    private boolean playerIsAbleToMove;

    public Player(Context context, int x, int y, int width, int height) {
        super();
        this.setX(x);

        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setName("player");
        this.setVisible(true);
        this.playerIsAbleToMove = false;

        this.playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        this.playerImage = Bitmap.createScaledBitmap(this.playerImage, this.getWidth(), this.getHeight(), false);

        this.playerPaint = new Paint();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {
        float xCenter = this.getX() - (this.getWidth() /  2);
        float yCenter = this.getY() - (this.getHeight() / 2);
        canvas.drawBitmap(playerImage, xCenter, yCenter, this.playerPaint);
    }

    public void onActionMove(MotionEvent event) {

        if (this.playerIsAbleToMove) {
            this.setX((int)event.getX());
            this.setY((int)event.getY());
        }
    }

    public void onActionDown(MotionEvent event) {
        this.playerIsAbleToMove = this.intersectsPlayer(event.getX(), event.getY());
    }

    private boolean intersectsPlayer(float clickedX, float clickedY) {
        float playerPosX = this.getX();
        float playerPosY = this.getY();

        int halfPlayerWidth = this.getWidth()/2;
        int halfPlayerHeight = this.getHeight()/2;

        boolean isInWidth = clickedX >= playerPosX - halfPlayerWidth && clickedX <= playerPosX + halfPlayerWidth;
        boolean isInHeight = clickedY >= playerPosY - halfPlayerHeight && clickedY <= playerPosY + halfPlayerHeight;

        return isInWidth && isInHeight;
    }
}
