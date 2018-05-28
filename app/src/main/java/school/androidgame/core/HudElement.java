package school.androidgame.core;

import android.graphics.Canvas;

import java.util.UUID;

/**
 * Created by kezab on 14.11.17.
 */

public abstract class HudElement {

    private float x;
    private float y;
    private int width;
    private int height;
    private String name;
    private boolean visible;
    private UUID uuid;

    public HudElement()
    {
        this.uuid = UUID.randomUUID();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        name = "";
        visible = false;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getWHeight(){
        return this.height;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean getVisibility(){
        return this.visible;
    }

    public UUID getUUID(){
        return this.uuid;
    }

    public void update(float dt){

    }

    public void draw(Canvas canvas){

    }


}
