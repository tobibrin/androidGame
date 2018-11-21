package school.androidgame.animations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.CalendarContract;
import android.support.annotation.ColorRes;

import java.util.LinkedList;

import school.androidgame.core.Entity;
import school.androidgame.core.GameObject;

public class TextAnimation extends TransitionAnimation<Canvas> {

    public TextAnimation()
    {

    }

    public static TextAnimation CreatePickupAnimation(boolean positive, int amount, Bitmap pickupImage, GameObject target)
    {
        TextAnimation anim = new TextAnimation();

        String positiveString = (positive)? "+" : "-";
        String amountString = amount + "";
        String fullString = positiveString + amountString;
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setTextSize(50);

        LinkedList<Frame<ITransition<Canvas>>> frames = new LinkedList<Frame<ITransition<Canvas>>>();
        frames.add(new Frame<ITransition<Canvas>>(c -> c.drawText(fullString, target.getX(),target.getY() -20, p), 250));
        frames.add(new Frame<ITransition<Canvas>>(c -> c.drawText(fullString, target.getX(),target.getY() -40, p), 250));
        frames.add(new Frame<ITransition<Canvas>>(c -> c.drawText(fullString, target.getX(),target.getY() -60, p), 250));
        frames.add(new Frame<ITransition<Canvas>>(c -> c.drawText(fullString, target.getX(),target.getY() -80, p), 250));
        frames.add(new Frame<ITransition<Canvas>>(c -> c.drawText(fullString, target.getX(),target.getY() -100, p), 250));
        anim.setFrames(frames);

        return anim;
    }
}
