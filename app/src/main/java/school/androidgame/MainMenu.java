package school.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

import school.androidgame.manager.GameManager;
import school.androidgame.utils.Config;

public class MainMenu extends Activity {
    private Config config;


    public void openMainMenu()
    {
        setContentView(R.layout.activity_main_menu);

        final Button startButton = (Button)this.findViewById(R.id.startButton);
        final Button exitButton = (Button)this.findViewById(R.id.exitButton);
        final Button settingsButton = (Button)this.findViewById(R.id.settingsButton);
        final Button scoresButton = (Button)this.findViewById(R.id.scoresButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.config = config;
                Intent gameIntent = new Intent(MainMenu.this, MainActivity.class);
                gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gameIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsMenu();
            }

        });
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScoresMenu();
            }

        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    public void openSettingsMenu()
    {
        setContentView(R.layout.activity_main_menu_settings);

        final Button backButton = (Button)this.findViewById(R.id.backButton);
        final Switch useSensorsSwitch = (Switch)this.findViewById(R.id.useSensorsSwitch);
        useSensorsSwitch.setChecked(this.config.getUseSensors());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        useSensorsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               config.setUseSensors(isChecked);
            }
        });
    }

    public void openScoresMenu()
    {
        setContentView(R.layout.activity_main_menu_scores);

        final Button backButton = (Button)this.findViewById(R.id.backButtonScores);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        final ListView scoreListView = (ListView)this.findViewById(R.id.scoreList);
        System.out.println("SCORECOUNT: " + this.config.getScores().size());

        ArrayList<Integer> scores = this.config.getScores();
        ArrayAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,this.config.getScores());
        scoreListView.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.config = new Config(this);
        this.config.loadValues();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        openMainMenu();
    }
}
