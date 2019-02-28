package school.androidgame.manager;

import android.graphics.Canvas;

import java.util.ArrayList;

import school.androidgame.GamePanel;
import school.androidgame.pickUps.HeartPickUp;
import school.androidgame.pickUps.PickUp;
import school.androidgame.pickUps.SpeedPickUp;
import school.androidgame.core.ICollectableObject;
import school.androidgame.utils.Vector2D;

public class PickUpManager {

    private float elapsedTime;
    private ArrayList<PickUp> pickUps;
    private ArrayList<PickUp> removeablePickUps;
    private GameManager gameManager;

    public PickUpManager(GameManager gameManager) {
        this.pickUps = new ArrayList<PickUp>();
        this.removeablePickUps = new ArrayList<PickUp>();
        this.gameManager = gameManager;
    }

    public void draw(Canvas canvas) {
        for (PickUp pickUp : this.pickUps) {
            pickUp.draw(canvas);
        }
    }

    public void update(float dt) {
        this.elapsedTime += dt;
        if (elapsedTime > 1) {
            this.elapsedTime = 0;
            if (this.pickUps.size() < 2 && Math.random() <= 0.15f) {
                PickUp pickUp = this.getRandomPickUp();
                if (pickUp != null) {
                    this.pickUps.add(pickUp);
                }
            }
        }
        for (PickUp pickUp : this.pickUps) {
            pickUp.update(dt);
            this.playerPickUpCollision(pickUp);
        }

        this.cleanUpCollectableObjects();
    }

    private void cleanUpCollectableObjects() {
        for (PickUp collectableObject: this.removeablePickUps) {
            this.pickUps.remove(collectableObject);
        }

        this.removeablePickUps.clear();
    }

    private void playerPickUpCollision(PickUp collectableObject) {
        if (collectableObject.isColliding(this.gameManager.getPlayer().getPlayerRect())) {
            collectableObject.onPlayerCollision(this.gameManager.getPlayer());
            this.removeablePickUps.add(collectableObject);
        }
    }

    private PickUp getRandomPickUp() {
        int randomInt = (int)(Math.random() * 2);




        PickUp result = null;
        switch (randomInt) {
            case 0:
                result = new SpeedPickUp(this.gameManager, new Vector2D(GamePanel.getRandomX(SpeedPickUp.halfSize), GamePanel.getRandomY(SpeedPickUp.halfSize)));
                break;
            case 1:
                result = new HeartPickUp(this.gameManager, new Vector2D(GamePanel.getRandomX(HeartPickUp.halfSize), GamePanel.getRandomY(HeartPickUp.halfSize)));
                break;
        }

        return result;
    }
}
