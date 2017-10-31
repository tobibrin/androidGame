package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import java.util.ArrayList;

import school.androidgame.Entities.Enemy;
import school.androidgame.Entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.Utils.Vector2D;

/**
 * Created by Tobi on 28.10.2017.
 */

public class EnemyManager {

    private Player player;

    private int maxEnemies;
    private Context context;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Enemy> enemiesToRemove;

    public EnemyManager(Context context, Player player) {
        this.context = context;
        this.player = player;

        this.enemyArrayList = new ArrayList<Enemy>();
        this.enemiesToRemove = new ArrayList<Enemy>();
        this.maxEnemies = 10;
    }

    public void draw(Canvas canvas) {
        for (Enemy enemy: enemyArrayList) {
            enemy.draw(canvas);
        }
    }

    public void update(float dt) {
        for (Enemy enemy: enemyArrayList) {
            enemy.update(dt);

            if (!enemy.getIsInScreen()) {
                this.enemiesToRemove.add(enemy);
            } else if (enemy.getHitPlayer()) {
                //TODO player gets dmg
                this.enemiesToRemove.add(enemy);
            }

        }

        if (this.enemyArrayList.size() < this.maxEnemies && Math.random() < 0.05f) {
            this.spawnEnemy();
        }

        this.cleanUpEnemies();
    }

    private void cleanUpEnemies() {

        for (Enemy enemy: this.enemiesToRemove) {
            enemy.destroy();
            this.enemyArrayList.remove(enemy);
        }

        this.enemiesToRemove.clear();
    }

    private void spawnEnemy() {
        PointF playerPos = new PointF(this.player.getX(), this.player.getY());
        Vector2D enemyPos = new Vector2D((float)(Math.random() * (GamePanel.WIDTH - 16)), GamePanel.HEIGHT - 16);

        PointF enemyDirection = enemyPos.getDirection(playerPos);

        Enemy enemy = new Enemy(this.context, this.player, enemyPos.x, enemyPos.y);
        enemy.setDirection(enemyDirection);

        this.enemyArrayList.add(enemy);
    }

}
