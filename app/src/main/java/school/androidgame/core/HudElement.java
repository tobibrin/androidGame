package school.androidgame.core;

import android.graphics.Canvas;

import java.util.UUID;

import school.androidgame.Utils.Vector2D;

/**
 * Created by kezab on 14.11.17.
 */

public class HudElement {

    protected Vector2D position;
    private boolean visible;

    public HudElement()
    {
        this.position = new Vector2D(0,0);
        visible = true;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean getVisibility(){
        return this.visible;
    }

    public void update(float dt){

    }

    public void draw(Canvas canvas){

    }


}
