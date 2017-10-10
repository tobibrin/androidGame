package school.androidgame.Entities;

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
}
