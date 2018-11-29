package school.androidgame.animations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;

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
        frames.add(new Frame<ITransition<Canvas>>(c -> {c.drawText(fullString, target.getX() - (target.getWidth() / 4),target.getY() -20, p);
            if(pickupImage != null)
                c.drawBitmap(pickupImage, target.getX(), target.getY(), null);
        }, 150));
        frames.add(new Frame<ITransition<Canvas>>(c -> {c.drawText(fullString, target.getX() - (target.getWidth() / 4),target.getY() -30, p);
            if(pickupImage != null)
                c.drawBitmap(pickupImage, target.getX(), target.getY(), null);
        }, 150));

        for(int i = 2; i < 60; i++) {
            frames.add(CreatePickupAnimationFrame(fullString, target, p, 60, i * 2, pickupImage));
            i++;
        }

        anim.setFrames(frames);
        return anim;
    }

    private static Frame<ITransition<Canvas>> CreatePickupAnimationFrame(String text, GameObject o, Paint p, long millis, int frameIndex, Bitmap image)
    {
        Frame<ITransition<Canvas>> newFrame = new Frame<ITransition<Canvas>>(c -> {
            c.drawText(text, o.getX() - (o.getWidth() / 4), o.getY() - (frameIndex), p);
            if(image != null)
                c.drawBitmap(image, o.getX(), o.getY() - (frameIndex), new Paint());
        }, millis);
        return newFrame;
    }

    public static TextAnimation CreateGetPointAnimation(boolean positive, int amount, Bitmap pickupImage, GameObject target)
    {
        TextAnimation anim = new TextAnimation();

        String positiveString = (positive)? "+" : "-";
        String amountString = amount + "";
        String fullString = positiveString + amountString;
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        p.setTextSize(50);

        LinkedList<Frame<ITransition<Canvas>>> frames = new LinkedList<Frame<ITransition<Canvas>>>();

        for(int i = 2; i < 60; i++) {
            frames.add(CreatePickupAnimationFrame(fullString, target, p, 60, i * 2, pickupImage));
            i++;
        }

        anim.setFrames(frames);

        return anim;
    }
}
