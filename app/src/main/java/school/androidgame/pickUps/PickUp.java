package school.androidgame.pickUps;

import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.core.CollideableGameObject;
import school.androidgame.core.ICollectableObject;
import school.androidgame.entities.Player;

public abstract class PickUp extends CollideableGameObject implements ICollectableObject {
    public abstract void onPlayerCollision(Player player);
    public abstract boolean isColliding(Rect rect);
    public abstract void draw(Canvas canvas);
    public abstract void update(float dt);
}
