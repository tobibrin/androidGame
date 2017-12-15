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

    public Health(){
        this.value = 3;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public void draw(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        paint.setAntiAlias(true);

        canvas.drawText("♥♥♥", canvas.getWidth() - 200, 100, paint);



        //canvas.drawText(String.valueOf(value), 50,50, new Paint());
    }
    @Override
    public void update(float dt){

    }
}
