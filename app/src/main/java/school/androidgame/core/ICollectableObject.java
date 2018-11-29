package school.androidgame.core;

import android.graphics.Canvas;
import android.graphics.Rect;

import school.androidgame.entities.Player;

public interface ICollectableObject {
    void onPlayerCollision(Player player);
    boolean isColliding(Rect rect);
    void draw(Canvas canvas);
    void update(float dt);
}
