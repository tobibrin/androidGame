package school.androidgame.manager;

import android.app.Dialog;
import android.content.Context;

import school.androidgame.entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.MainActivity;
import school.androidgame.MainMenu;
import school.androidgame.R;
import school.androidgame.interfaces.ISpeed;
import school.androidgame.powerUps.SpeedPowerUp;
import school.androidgame.utils.Config;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.Assert;

/**
 * Created by tobijasp on 17.12.2017.
 */

public class GameManager {

    public boolean stopped;

    private MainActivity activity;
    public Context context;
    public GamePanel gamePanel;
    public Config config;

    public Player player;
    public GuiManager guiManager;
    public EnemyManager enemyManager;
    public GameTimeEventManager gameTimeEventManager;
    private CollectAbleManager collectAbleManager;

    public int defaultHealth;

    public GameManager(final MainActivity activity) {
        this.defaultHealth = 5;
        this.stopped = false;
        this.activity = activity;
        this.context = activity;
        this.config = activity.getConfig();
        this.gamePanel = new GamePanel(this);
        this.activity.setContentView(this.gamePanel);
        this.player = new Player(this, this.defaultHealth);
        this.guiManager = new GuiManager(this);
        this.enemyManager = new EnemyManager(this);
        this.collectAbleManager = new CollectAbleManager(this.context, this.player);
        this.gameTimeEventManager = new GameTimeEventManager();
        this.gameTimeEventManager.registerPlayerChangeColor(this.player, 3000);
    }

    public void showGameOverDialog() {

        int points = this.player.getPoints();
        int textColor = Color.YELLOW;
        String pointsString = "SCORE: " + points;

        if(this.config.isNewHighscore(points)){
            textColor = Color.RED;
            pointsString += "!";
        }

        final int textColorFinal = textColor;
        final String pointsStringFinal = pointsString;


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
                TextView gameOverScoreView = dialog.findViewById(R.id.gameOverScoreView);

                gameOverScoreView.setTextColor(textColorFinal);
                gameOverScoreView.setText(pointsStringFinal);


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

        this.config.addScore(this.player.getPoints());
        this.config.saveValues();
    }

    public void update(float dt) {
        this.collectAbleManager.update(dt);
        this.enemyManager.update(dt);
        this.player.update(dt);
        this.guiManager.update(dt);
    }

    public void draw(Canvas canvas) {
        this.collectAbleManager.draw(canvas);
        this.enemyManager.draw(canvas);
        this.player.draw(canvas);
        this.guiManager.draw(canvas);

        if (this.player.getHealth() == 0) {

            this.gamePanel.stopGame();
            this.showGameOverDialog();
        }
    }

    public void stop() {
        this.stopped = true;
    }

    public void resume() {
        this.stopped = false;
    }

}
