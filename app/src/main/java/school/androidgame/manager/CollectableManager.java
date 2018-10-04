package school.androidgame.manager;

import android.graphics.Canvas;

import java.util.ArrayList;

import school.androidgame.GamePanel;
import school.androidgame.entities.Heart;
import school.androidgame.entities.SpeedPowerUp;
import school.androidgame.interfaces.ICollectableObject;
import school.androidgame.utils.Vector2D;

public class CollectableManager {

    private float elapsedTime;
    private ArrayList<ICollectableObject> collectableObjects;
    private ArrayList<ICollectableObject> collectableObjectsToRemove;
    private GameManager gameManager;

    public CollectableManager(GameManager gameManager) {
        this.collectableObjects = new ArrayList<ICollectableObject>();
        this.collectableObjectsToRemove = new ArrayList<ICollectableObject>();
        this.gameManager = gameManager;
    }

    public void draw(Canvas canvas) {
        for (ICollectableObject collectable : this.collectableObjects) {
            collectable.draw(canvas);
        }
    }

    public void update(float dt) {
        this.elapsedTime += dt;
        if (elapsedTime > 1) {
            this.elapsedTime = 0;
            if (this.collectableObjects.size() < 2 && Math.random() <= 0.15f) {
                ICollectableObject collectableObject = this.getRandomCollectableObject();
                if (collectableObject != null) {
                    this.collectableObjects.add(collectableObject);
                }
            }
        }
        for (ICollectableObject collectable : this.collectableObjects) {
            collectable.update(dt);
            this.playerCollectableCollision(collectable);
        }

        this.cleanUpCollectableObjects();
    }

    private void cleanUpCollectableObjects() {
        for (ICollectableObject collectableObject: this.collectableObjectsToRemove) {
            this.collectableObjects.remove(collectableObject);
        }

        this.collectableObjectsToRemove.clear();
    }

    private void playerCollectableCollision(ICollectableObject collectableObject) {
        if (collectableObject.isColliding(this.gameManager.getPlayer().getPlayerRect())) {
            collectableObject.onPlayerCollision(this.gameManager.getPlayer());
            this.collectableObjectsToRemove.add(collectableObject);
        }
    }

    private ICollectableObject getRandomCollectableObject() {
        int randomInt = (int)(Math.random() * 2);
        ICollectableObject result = null;
        switch (randomInt) {
            case 0:
                result = new SpeedPowerUp(this.gameManager, new Vector2D(GamePanel.getRandomX(SpeedPowerUp.halfSize), GamePanel.getRandomY(SpeedPowerUp.halfSize)));
                break;
            case 1:
                result = new Heart(this.gameManager, new Vector2D(GamePanel.getRandomX(Heart.halfSize), GamePanel.getRandomY(Heart.halfSize)));
                break;
        }

        return result;
    }
}
