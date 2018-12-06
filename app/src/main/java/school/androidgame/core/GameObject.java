package school.androidgame.core;

import android.graphics.Canvas;

import java.util.UUID;

import school.androidgame.GamePanel;
import school.androidgame.utils.Vector2D;

/**
 * Created by kezab on 09.10.17.
 */

public abstract class GameObject {

    protected Vector2D position;
    private int width;
    private int height;
    private String name;
    private boolean visible;
    private UUID uuid;

    public GameObject() {
        this.name = "";
        this.position = new Vector2D(0,0);
        this.width = 0;
        this.height = 0;
        this.visible = false;
        this.uuid = UUID.randomUUID();
    }

    public GameObject(float newX, float newY, int newWidth, int newHeight) {
        this.name = "";
        this.position = new Vector2D(newX, newY);
        this.width = newWidth;
        this.height = newHeight;
        this.visible = false;
        this.uuid = UUID.randomUUID();
    }

    public abstract void update(float dt);

    public abstract void draw(Canvas canvas);

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


    public Vector2D getPosition() {
        return position;
    }
}
