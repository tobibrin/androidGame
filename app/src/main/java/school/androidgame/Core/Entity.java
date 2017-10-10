package school.androidgame.Core;

/**
 * Created by kezab on 10.10.17.
 */

public abstract class Entity extends GameObject{

    public long lifetime;

    public Entity(int newX, int newY, int newWidth, int newHeight){
        super(newX, newY, newWidth, newHeight);
        this.lifetime = 10000000;
    }

    public void addLifetime(long newLifetime){
        this.lifetime += newLifetime;
    }

    public void addLifetime(int newLifeTime){
        this.lifetime += (long)newLifeTime;
    }

    public void setLifetime(long newLifetime){
        this.lifetime = newLifetime;
    }

    public long getLifeTime(){
        return this.lifetime;
    }
}
