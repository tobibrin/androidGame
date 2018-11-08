package school.androidgame.animations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

public class TransitionAnimation<T> extends Animation<ITransition<T>> {
    public TransitionAnimation()
    {
        super();
    }

    @Override
    public void Start()
    {
        this.isStarted = true;
        this.loop = false;
    }

    @Override
    public void Start(boolean loop)
    {
        this.isStarted = true;
        this.loop = false;
    }
}
