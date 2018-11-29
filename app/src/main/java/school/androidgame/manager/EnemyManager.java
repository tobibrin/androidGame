package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.ArrayList;

import school.androidgame.entities.Enemy;
import school.androidgame.entities.player.Player;
import school.androidgame.GamePanel;
import school.androidgame.utils.Tools;
import school.androidgame.utils.Vector2D;
import school.androidgame.utils.bitmap.colors.ObjectColorState;

/**
 * Created by Tobi on 28.10.2017.
 */

public class EnemyManager {

    private Player player;
    private Context context;
    private GameManager gameManager;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Enemy> enemiesToRemove;

    public EnemyManager(GameManager game) {
        this.gameManager = game;
        this.context = game.getContext();
        this.player = game.getPlayer();

        this.enemyArrayList = new ArrayList<>();
        this.enemiesToRemove = new ArrayList<>();
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

        int maxEnemies = (int)Tools.map(this.gameManager.getGameDifficulty(), 0, 100, 1, 40);
        for (Enemy enemy : enemyArrayList) {
            enemy.update(dt);

            if (!enemy.getIsInScreen()) {
                this.enemiesToRemove.add(enemy);
            } else {
                this.playerEnemyCollision(enemy);
            }
        }

        if (this.enemyArrayList.size() < maxEnemies && Math.random() > 0.75f) {
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
        PointF playerPos = new PointF(this.player.getPosition().x, this.player.getPosition().y);
        Vector2D enemyPos = this.getRandomEnemyPos();

        PointF enemyDirection = enemyPos.getDirection(playerPos);

        Enemy enemy = new Enemy(
                this.context,
                enemyPos.x,
                enemyPos.y,
                Tools.map(this.gameManager.getGameDifficulty(), 0, 100, 130, 170)
        );
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
        return new Vector2D(1, GamePanel.getRandomY(1));
    }

    private Vector2D getRandomRightPos() {
        return new Vector2D(GamePanel.WIDTH, GamePanel.getRandomY(1));
    }

    private Vector2D getRandomTopPos() {
        return new Vector2D(GamePanel.getRandomX(1), 1);
    }

    private Vector2D getRandomBottomPos() {
        return new Vector2D(GamePanel.getRandomX(1), GamePanel.HEIGHT);
    }

}
