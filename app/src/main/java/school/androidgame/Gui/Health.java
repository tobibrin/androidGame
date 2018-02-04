package school.androidgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.Core.HudElement;

/**
 * Created by kezab on 28.11.17.
 */

public class Health extends HudElement {

    private StringBuilder health;
    private int healthAmount;

    public Health(){
        this.health = new StringBuilder();
        this.healthAmount = 5;
        this.setHealthString();
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
            int width = this.healthAmount * 50 + 75;
            int x = canvas.getWidth() - width;

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setAntiAlias(true);

            canvas.drawText(this.getHealthString(), x, 100, paint);
        }
    }

    public void update(int playerHealth){
        this.healthAmount = playerHealth;
        this.setHealthString();
    }
}
