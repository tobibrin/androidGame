package school.androidgame.animations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

public class Animation<T> {
    protected boolean isStarted;
    private LinkedList<Frame<T>> frames;
    private ListIterator<Frame<T>> frameIterator;
    private Frame<T> currentFrame;
    private long lastFrameChangeTime;
    protected boolean loop;

    public Animation()
    {
        this.currentFrame = null;
        this.isStarted = false;
        this.setFrames(new LinkedList<Frame<T>>());
        this.lastFrameChangeTime = 0;
        this.loop = true;
        this.AnimationFinished = new Observable();
    }

    public void update()
    {
        long currentTime = System.currentTimeMillis();
        if(this.currentFrame == null)
        {
            this.frameIterator = this.frames.listIterator(0);
            this.currentFrame = this.frameIterator.next();
        }
        else if(currentTime >= this.lastFrameChangeTime + this.currentFrame.getFrameTime())
        {
            if(frameIterator.hasNext()) {
                this.currentFrame = frameIterator.next();
                this.lastFrameChangeTime = currentTime;
            }
            else {
                if(this.loop)
                {
                    this.frameIterator = this.frames.listIterator(0);
                    this.lastFrameChangeTime = currentTime;
                }
                else
                {
                    this.isStarted = false;
                    this.AnimationFinished.notifyObservers();
                }
            }

        }
    }

    public void setFrames(LinkedList<Frame<T>> frames)
    {
        this.frames = frames;
        this.frameIterator = frames.listIterator();
    }

    public void Start(boolean loop)
    {
        this.Start();
        this.loop = loop;
    }

    public void Start()
    {
        if(this.frames.size() > 0)
        {
            this.isStarted = true;
            this.currentFrame = this.frameIterator.next();
            this.lastFrameChangeTime = System.currentTimeMillis();
        }
    }

    public Frame<T> getCurrentFrame()
    {
        return this.currentFrame;
    }

    public boolean isStarted()
    {
        return this.isStarted;
    }

    public Observable AnimationFinished;
}
