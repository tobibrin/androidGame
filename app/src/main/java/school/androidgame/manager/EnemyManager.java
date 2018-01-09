package school.androidgame.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Iterator;

import school.androidgame.Entities.Enemy;
import school.androidgame.Entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.Utils.Vector2D;

/**
 * Created by Tobi on 28.10.2017.
 */

public class EnemyManager {

    private GameManager game;
    private Player player;

    private int maxEnemies;
    private Context context;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Enemy> enemiesToRemove;

    public EnemyManager(GameManager game) {
        this.game = game;
        this.context = game.context;
        this.player = game.player;

        this.enemyArrayList = new ArrayList<Enemy>();
        this.enemiesToRemove = new ArrayList<Enemy>();
        this.maxEnemies = 10;
    }

    public void draw(Canvas canvas) {

        for (Enemy enemy : enemyArrayList) {
            enemy.draw(canvas);
        }
    }

    public void update(float dt) {
        for (Enemy enemy : enemyArrayList) {
            enemy.update(dt);

            if (!enemy.getIsInScreen()) {
                this.enemiesToRemove.add(enemy);
            } else if (enemy.isVisible() && enemy.getHitPlayer()) {
                this.player.damage(1);
                this.enemiesToRemove.add(enemy);
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

        Enemy enemy = new Enemy(this.context, this.player, enemyPos.x, enemyPos.y);
        enemy.setVisible(true);
        enemy.setDirection(enemyDirection);

        this.enemyArrayList.add(enemy);
    }

    private Vector2D getRandomEnemyPos() {
        Vector2D resultEnemyPos;

        double random = Math.random();

        if (random < 0.25) {

            resultEnemyPos = this.getRandomLeftPos();

        } else if (random < 0.5) {

            resultEnemyPos = this.getRandomTopPos();

        } else if (random < 0.75) {

            resultEnemyPos = this.getRandomRightPos();

        } else {

            resultEnemyPos = this.getRandomBottomPos();

        }

        return resultEnemyPos;
    }

    private Vector2D getRandomLeftPos() {
        return new Vector2D(0, (float) (GamePanel.HEIGHT * Math.random()));
    }

    private Vector2D getRandomRightPos() {
        return new Vector2D(GamePanel.WIDTH, (int) (GamePanel.HEIGHT * Math.random()));
    }

    private Vector2D getRandomTopPos() {
        return new Vector2D((float) (GamePanel.WIDTH * Math.random()), 0);
    }

    private Vector2D getRandomBottomPos() {
        return new Vector2D((float) (GamePanel.WIDTH * Math.random()), GamePanel.HEIGHT);
    }

    public void killAll(){

        Iterator<Enemy> i = this.enemyArrayList.iterator();
        while(i.hasNext()){
            i.next().setVisible(false);
        }
    }

}
