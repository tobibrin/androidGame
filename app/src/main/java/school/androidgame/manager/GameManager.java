package school.androidgame.manager;

import android.content.Context;

import school.androidgame.Entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.MainActivity;
import school.androidgame.MainThread;

import android.content.SharedPreferences;
import android.graphics.Canvas;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tobijasp on 17.12.2017.
 */

public class GameManager {

    public boolean stopped;

    private MainActivity activity;
    public Context context;
    public GamePanel gamepanel;

    public Player player;
    public GuiManager guiManager;
    public EnemyManager enemyManager;
    public TimeManager timeManager;

    public int defaultHealth;

    public GameManager(MainActivity activity)
    {
        this.defaultHealth = 5;
        this.stopped = false;
        this.activity = activity;
        this.context = activity;
        this.gamepanel = new GamePanel(this);
        this.activity.setContentView(this.gamepanel);
        this.player = new Player(this, 50, 50, this.defaultHealth);
        this.timeManager = new TimeManager(this);
        this.guiManager = new GuiManager(this);
        this.enemyManager = new EnemyManager(this);
    }

    public void draw(Canvas canvas){
        this.enemyManager.draw(canvas);
        this.player.draw(canvas);
        this.guiManager.draw(canvas);
    }

    public void update(float dt){
        this.enemyManager.update(dt);
        this.player.update(dt);
        this.guiManager.update(dt);
    }

    private void StoreHighScore(){

    }

    public void gameOver()
    {
        this.restart();
    }
    public void stop(){
        this.stopped = true;
    }

    public void resume(){
        this.stopped = false;
    }

    public void restart(){
        this.player.setHealth(this.defaultHealth);
        this.enemyManager.killAll();
        this.timeManager.reset();
    }
}
