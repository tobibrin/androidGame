package school.androidgame.Entities;

import android.graphics.Canvas;

import school.androidgame.Core.GameObject;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends GameObject {

    public long score;
    public int lifes;

    public Player(){
        super();

        this.score = 0;
        this.lifes = 3;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {

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
