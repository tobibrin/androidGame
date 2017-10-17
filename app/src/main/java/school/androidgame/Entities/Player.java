package school.androidgame.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.TextureView;

import school.androidgame.Core.GameObject;

/**
 * Created by kezab on 10.10.17.
 */

public class Player extends GameObject {

    public long score;
    public int lifes;

    public Player(int x, int y, int width, int height){
        super();

        this.setName("player");
        this.setVisible(true);

        Bitmap bitmap = BitmapFactory.decodeFile("images/Player.png");

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
