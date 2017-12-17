package school.androidgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.Core.HudElement;

/**
 * Created by kezab on 28.11.17.
 */

public class Health extends HudElement {
    private int value;
    private String health;

    public Health(){
        this.value = 5;
    }

    public Health(int amount){
        this.value = amount;
    }

    public void setValue(int value){
        this.value = value;
    }

    private String getHealthString()
    {
        String health = "";
        for(int i=0; i < this.value; i++)
        {
            health += "♥";
        }
        return health;
    }


    @Override
    public void draw(Canvas canvas)
    {
        if(this.getVisibility())
        {
            int width = this.value * 50 + 75;
            int x = canvas.getWidth() - width;
            String health = this.getHealthString();

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setAntiAlias(true);

            canvas.drawText(health, x, 100, paint);
        }
    }

    public void update(int playerhealth){
        this.value = playerhealth;
    }
}
