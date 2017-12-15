package school.androidgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.Core.HudElement;
import school.androidgame.manager.TimeManager;

/**
 * Created by kezab on 14.11.17.
 */

public class PlayTime extends HudElement{
    private long time;

    public PlayTime()
    {
        TimeManager.start();
        this.time = 0;
    }

    public PlayTime(long offset)
    {
        this.time = offset;
    }

    public void add(long time){
        this.time += time;
    }

    public void remove(long time)
    {
        this.time -= time;
    }

    public float getTime(){
        return this.time;
    }

    public String GetString(){
        return "" + time;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        canvas.drawText("" + this.time, 100, 100, paint);
        //canvas.drawText(String.valueOf(this.time), 0,0,new Paint());
    }

    @Override
    public void update(float dt){
        this.time = TimeManager.getRelativeTime();

    }


}
