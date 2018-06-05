package school.androidgame.Core;

import android.graphics.Canvas;

import java.util.UUID;

import school.androidgame.GamePanel;

/**
 * Created by kezab on 09.10.17.
 */

public abstract class GameObject {
    //TODO x und y mit der Vector2D-Klasse ersetzen
    private float x;
    private float y;
    private int width;
    private int height;
    private String name;
    private boolean visible;
    private UUID uuid;

    public GameObject() {
        this.name = "";
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.visible = false;
        this.uuid = UUID.randomUUID();
    }

    public GameObject(float newX, float newY, int newWidth, int newHeight) {
        this.name = "";
        this.x = newX;
        this.y = newY;
        this.width = newWidth;
        this.height = newHeight;
        this.visible = false;
        this.uuid = UUID.randomUUID();
    }

    public abstract void update(float dt);

    public abstract void draw(Canvas canvas);

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setX(float newX) {

        if (newX > GamePanel.WIDTH) {
            newX = GamePanel.WIDTH;
        }

        if (newX < 0) {
            newX = 0;
        }

        this.x = newX;
    }

    public void setY(float newY) {

        if (newY > GamePanel.HEIGHT) {
            newY = GamePanel.HEIGHT;
        }

        if (newY < 0) {
            newY = 0;
        }


        this.y = newY;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setVisible(boolean newVisible) {
        this.visible = newVisible;
    }


}
