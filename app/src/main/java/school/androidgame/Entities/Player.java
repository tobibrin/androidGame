package school.androidgame.Entities;

import school.androidgame.Core.Entity;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends Entity {

    public long score;
    public int lifes;

    public Player(){
        super(0,0,0,0);

        this.score = 0;
        this.lifes = 3;
    }

    public void damage(int dLifes){
        this.lifes -= dLifes;
    }

    public void addScore(long Scorepoints){
        this.score += Scorepoints;
    }

    public long getScore(){
        return this.score;
    }

    public int getLifes(){
        return this.lifes;
    }

}
