package school.androidgame.animations;

public class Frame<T> {

    private T frameObj;
    private long frameTimeMillis;

    public Frame()
    {
        this.frameObj = null;
        this.frameTimeMillis = 1000;
    }

    public Frame(T frameObject)
    {
        this.frameObj = frameObject;
        this.frameTimeMillis = 1000;
    }

    public Frame(T frameObject, long millis)
    {
        this.frameObj = frameObject;
        this.frameTimeMillis = millis;
    }

    public void setFrameObject(T item)
    {
        this.frameObj = item;
    }

    public T getFrameObject()
    {
        return this.frameObj;
    }

    public void setFrameTime(long millis)
    {
        this.frameTimeMillis = millis;
    }

    public long getFrameTime()
    {
        return this.frameTimeMillis;
    }
}
