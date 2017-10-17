package school.androidgame.Entities;

import android.graphics.Canvas;

import school.androidgame.Core.*;

/**
 * Created by kezab on 10.10.17.
 */

public class EasyEnemy extends Entity {

    public long lifetime;

    public EasyEnemy(){
        super(0,0,0,0);
        this.lifetime = Long.MAX_VALUE;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
