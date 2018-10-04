package school.androidgame.core;

import android.graphics.Rect;

public abstract class CollideAbleGameObject extends GameObject {

    protected Rect rect;

    public boolean isColliding(Rect otherRect) {
        boolean isColliding = false;
        if (this.rect != null) {
            isColliding = this.rect.intersect(otherRect);
        }
        return isColliding;
    }
}
