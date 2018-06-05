package school.androidgame.manager;

import android.app.Dialog;
import android.content.Context;

import school.androidgame.Entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.MainActivity;
import school.androidgame.MainMenu;
import school.androidgame.R;
import school.androidgame.Utils.Config;
import school.androidgame.repositories.BitmapColorRepository;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by tobijasp on 17.12.2017.
 */

public class GameManager {

    public boolean stopped;

    private MainActivity activity;
    public Context context;
    public GamePanel gamePanel;

    public Player player;
    public GuiManager guiManager;
    public EnemyManager enemyManager;
    public GameTimeEventManager gameTimeEventManager;

    public int defaultHealth;

    public GameManager(final MainActivity activity) {

        this.defaultHealth = 5;
        this.stopped = false;
        this.activity = activity;
        this.context = activity;
        this.gamePanel = new GamePanel(this);
        this.activity.setContentView(this.gamePanel);
        this.player = new Player(this, this.defaultHealth);
        this.guiManager = new GuiManager(this);
        this.enemyManager = new EnemyManager(this);
        this.gameTimeEventManager = new GameTimeEventManager(this.player);

        this.lastSecond = 0;
        Config.context = this.context;
        Config.loadValues();
    }

    public void draw(Canvas canvas) {
        this.enemyManager.draw(canvas);
        this.player.draw(canvas);
        this.guiManager.draw(canvas);

        if (this.player.getHealth() == 0) {

            this.gamePanel.stopGame();
            this.showGameOverDialog();
        }
    }

    public void showGameOverDialog() {
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog dialog = new Dialog(context);
                dialog.setCanceledOnTouchOutside(false);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.game_over_dialog);

                Button backToMenuButton = dialog.findViewById(R.id.backToMenuButton);
                Button restartGameButton = dialog.findViewById(R.id.restartGameButton);

                backToMenuButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent mainMenuIntent = new Intent( context, MainMenu.class);
                        mainMenuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(mainMenuIntent);
                    }
                });

                restartGameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent gameIntent = new Intent(context, MainActivity.class);
                        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(gameIntent);
                    }
                });

                dialog.show();
            }
        });
    }

    private long lastSecond;

    public void update(float dt) {
        this.enemyManager.update(dt);
        this.player.update(dt);
        this.guiManager.update(dt);
    }

    private void storeHighScore() {

    }


    public void stop() {
        this.stopped = true;
    }

    public void resume() {
        this.stopped = false;
    }

}
