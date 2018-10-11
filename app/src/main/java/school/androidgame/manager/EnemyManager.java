package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.ArrayList;

import school.androidgame.entities.Enemy;
import school.androidgame.entities.player.Player;
import school.androidgame.GamePanel;
import school.androidgame.utils.Vector2D;
import school.androidgame.utils.bitmap.colors.ObjectColorState;

/**
 * Created by Tobi on 28.10.2017.
 */

public class EnemyManager {

    private Player player;
    private int maxEnemies;
    private Context context;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Enemy> enemiesToRemove;

    public EnemyManager(GameManager game) {
        this.context = game.getContext();
        this.player = game.getPlayer();

        this.enemyArrayList = new ArrayList<>();
        this.enemiesToRemove = new ArrayList<>();
        this.maxEnemies = 10;
    }

    public void draw(Canvas canvas) {

        for (Enemy enemy : enemyArrayList) {
            enemy.draw(canvas);
        }
    }

    private void playerEnemyCollision(Enemy enemy) {
        if (enemy.isVisible() && enemy.isColliding(player.getPlayerRect())) {

            ObjectColorState playerObjectColorState = this.player.getBitmapColorRepository().getCurrentColorState();
            ObjectColorState enemyObjectColorState = enemy.getBitmapColorRepository().getCurrentColorState();

            if (playerObjectColorState == enemyObjectColorState) {
                this.player.addPoint(1);
            } else {
                this.player.damage(1);
            }

            this.enemiesToRemove.add(enemy);
        }
    }


    public void update(float dt) {
        for (Enemy enemy : enemyArrayList) {
            enemy.update(dt);

            if (!enemy.getIsInScreen()) {
                this.enemiesToRemove.add(enemy);
            } else {
                this.playerEnemyCollision(enemy);
            }
        }

        if (this.enemyArrayList.size() < this.maxEnemies && Math.random() < 0.05f) {
            this.spawnEnemy();
        }

        this.cleanUpEnemies();
    }

    private void cleanUpEnemies() {
        for (Enemy enemy : this.enemiesToRemove) {
            enemy.destroy();
            this.enemyArrayList.remove(enemy);
        }
        this.enemiesToRemove.clear();
    }

    private void spawnEnemy() {
        PointF playerPos = new PointF(this.player.getX(), this.player.getY());
        Vector2D enemyPos = this.getRandomEnemyPos();

        PointF enemyDirection = enemyPos.getDirection(playerPos);

        Enemy enemy = new Enemy(this.context, enemyPos.x, enemyPos.y);
        enemy.setVisible(true);
        enemy.setDirection(enemyDirection);

        this.enemyArrayList.add(enemy);
    }

    private Vector2D getRandomEnemyPos() {
        Vector2D resultEnemyPos;
        double random = Math.random();

        if (random < 0.25)
            resultEnemyPos = this.getRandomLeftPos();
        else if (random < 0.5)
            resultEnemyPos = this.getRandomTopPos();
        else if (random < 0.75)
            resultEnemyPos = this.getRandomRightPos();
        else
            resultEnemyPos = this.getRandomBottomPos();

        return resultEnemyPos;
    }

    private Vector2D getRandomLeftPos() {
        return new Vector2D(0, GamePanel.getRandomY(0));
    }

    private Vector2D getRandomRightPos() {
        return new Vector2D(GamePanel.WIDTH, GamePanel.getRandomY(0));
    }

    private Vector2D getRandomTopPos() {
        return new Vector2D(GamePanel.getRandomX(0), 0);
    }

    private Vector2D getRandomBottomPos() {
        return new Vector2D(GamePanel.getRandomX(0), GamePanel.HEIGHT);
    }

}
