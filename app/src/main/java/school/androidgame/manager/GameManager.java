package school.androidgame.manager;

import android.app.Dialog;
import android.content.Context;

import school.androidgame.entities.Player;
import school.androidgame.GamePanel;
import school.androidgame.MainActivity;
import school.androidgame.MainMenu;
import school.androidgame.R;
import school.androidgame.utils.Config;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tobijasp on 17.12.2017.
 */

public class GameManager {

    private MainActivity activity;
    private Context context;
    private GamePanel gamePanel;
    private Config config;

    private Player player;
    private GuiManager guiManager;
    private EnemyManager enemyManager;
    private GameTimeEventManager gameTimeEventManager;
    private CollectableManager collectableManager;

    private float gameDifficulty;

    public GameManager(final MainActivity activity) {
        this.gameDifficulty = 1f;
        this.activity = activity;
        this.context = activity;
        this.config = activity.getConfig();
        this.gamePanel = new GamePanel(this);
        this.activity.setContentView(this.gamePanel);
        this.player = new Player(this, 5);
        this.initManagers();
    }

    private void initManagers() {
        this.guiManager = new GuiManager(this);
        this.enemyManager = new EnemyManager(this);
        this.collectableManager = new CollectableManager(this);
        this.gameTimeEventManager = new GameTimeEventManager();
        this.gameTimeEventManager.registerPlayerChangeColor(this.player, 3000);

    }

    public void update(float dt) {
        this.collectableManager.update(dt);
        this.enemyManager.update(dt);
        this.player.update(dt);
        this.guiManager.update(dt);
        this.setGameDifficulty();
    }

    public void draw(Canvas canvas) {
        this.collectableManager.draw(canvas);
        this.enemyManager.draw(canvas);
        this.player.draw(canvas);
        this.guiManager.draw(canvas);

        if (this.player.getHealth() == 0) {
            this.gamePanel.stopGame();
            this.showGameOverDialog();
        }
    }

    private void setGameDifficulty() {
        long elapsedGameTime = this.gameTimeEventManager.getElapsedGameTimeInSeconds();
        if (elapsedGameTime > 0) {
            // y=log_2(x + 168) * 35 - 258
            this.gameDifficulty = (float) ((Math.log(elapsedGameTime + 168) / Math.log(2) * 35) - 258);
        }
    }

    private void showGameOverDialog() {
        int points = this.player.getPoints();
        int textColor = Color.YELLOW;
        String pointsString = "SCORE: " + points;

        if (this.config.isNewHighscore(points)) {
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
                        Intent mainMenuIntent = new Intent(context, MainMenu.class);
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

    public float getGameDifficulty() {
        return this.gameDifficulty;
    }

    public Context getContext() {
        return this.context;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Config getConfig() {
        return config;
    }
}
