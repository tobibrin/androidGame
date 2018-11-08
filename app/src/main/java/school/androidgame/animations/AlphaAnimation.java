package school.androidgame.animations;

import android.graphics.Paint;

import java.util.LinkedList;

public class AlphaAnimation extends TransitionAnimation<Paint> {
    public int originalARGB;

    public AlphaAnimation()
    {
        super();
        this.originalARGB = 0;
    }

    public static AlphaAnimation CreateDamageAnimation()
    {
        AlphaAnimation anim = new AlphaAnimation();
        LinkedList<Frame<ITransition<Paint>>> frames = new LinkedList<Frame<ITransition<Paint>>>();
        frames.add(new Frame<ITransition<Paint>>((p) -> p.setAlpha(255), 50));
        frames.add(new Frame<ITransition<Paint>>((p) -> p.setAlpha(50), 100));
        frames.add(new Frame<ITransition<Paint>>((p) -> p.setAlpha(255), 50));
        frames.add(new Frame<ITransition<Paint>>((p) -> p.setAlpha(50), 150));
        frames.add(new Frame<ITransition<Paint>>((p) -> p.setAlpha(255), 1));
        anim.setFrames(frames);
        return anim;
    }
}
