package school.androidgame.animations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;

import school.androidgame.core.GameObject;
import school.androidgame.pickUps.HeartPickUp;

public class TextAnimation extends TransitionAnimation<Canvas> {

    public TextAnimation()
    {

    }

    public static TextAnimation CreatePickupAnimation(boolean positive, int amount, Bitmap pickupImage, GameObject target)
    {
        TextAnimation anim = new TextAnimation();

        String positiveString = (positive)? "+" : "-";
        String amountString = "";
        String fullString = positiveString + amountString;
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        p.setTextSize(50);

        LinkedList<Frame<ITransition<Canvas>>> frames = new LinkedList<Frame<ITransition<Canvas>>>();

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
            float objectCenter = o.getWidth() / 2;
            int bitmapSize = 64;
            int imageCenter = 32;
            float marginToCenter = 15;
            float textSize = 10;
            float textLeft = o.getPosition().x - imageCenter -marginToCenter;

            float imageLeft = o.getPosition().x -imageCenter + marginToCenter;
            float textPosition = o.getPosition().y - frameIndex - (textSize / 2);
            if(image == null)
                textLeft += (imageCenter /2 );

            c.drawText(text, textLeft, textPosition, p);
            if(image != null) {
                Bitmap scaled = Bitmap.createScaledBitmap(image, bitmapSize, bitmapSize, false);
                c.drawBitmap(scaled, imageLeft , o.getPosition().y - frameIndex - bitmapSize, new Paint());
            }
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
