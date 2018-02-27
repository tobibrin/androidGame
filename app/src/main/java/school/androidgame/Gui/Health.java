package school.androidgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.Core.HudElement;
import school.androidgame.GamePanel;

/**
 * Created by kezab on 28.11.17.
 */

public class Health extends HudElement {

    private final static int STRING_PADDING_LEFT = 10;
    private final static int STRING_PADDING_TOP = 10;

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
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setAntiAlias(true);
            // TODO set text direction right to left

            canvas.drawText(this.getHealthString(), Health.STRING_PADDING_LEFT, paint.getTextSize() + Health.STRING_PADDING_TOP, paint);
        }
    }

    public void update(int playerHealth){
        this.healthAmount = playerHealth;
        this.setHealthString();
    }
}
