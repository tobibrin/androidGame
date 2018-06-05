package school.androidgame.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.core.HudElement;
import school.androidgame.utils.Vector2D;

/**
 * Created by kezab on 28.11.17.
 */

public class Health extends HudElement {

    private StringBuilder health;
    private int healthAmount;
    private Paint paint;

    public Health(float x, float y){
        this.health = new StringBuilder();
        this.healthAmount = 5;
        this.setHealthString();
        this.createPaint();
        this.position = new Vector2D(x, (float)(y + Math.floor(this.paint.getTextSize() / 2.0f)));
    }

    private void createPaint() {
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.RED);
        this.paint.setTextSize(50);
        this.paint.setAntiAlias(true);
    }

    private void setHealthString() {
        this.health.setLength(0);
        for(int i = 0 ; i < this.healthAmount; i++) {
             this.health.append("♥");
        }
    }

    private String getHealthString() {
        return this.health.toString();
    }


    @Override
    public void draw(Canvas canvas)
    {
        if(this.getVisibility())
        {
            canvas.drawText(this.getHealthString(), this.position.x, this.position.y, paint);
        }
    }

    public void update(int playerHealth){
        this.healthAmount = playerHealth;
        this.setHealthString();
    }
}
