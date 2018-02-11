package school.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.w3c.dom.Text;

import school.androidgame.Utils.Config;

public class MainMenu extends Activity {

    public Config config;

    public void openMainMenu()
    {
        setContentView(R.layout.activity_main_menu);

        final Button startButton = (Button)this.findViewById(R.id.startButton);
        final Button exitButton = (Button)this.findViewById(R.id.exitButton);
        final Button settingsButton = (Button)this.findViewById(R.id.settingsButton);
        final Button scoresButton = (Button)this.findViewById(R.id.scoresButton);
        MainActivity.config = this.config;


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        final Button backButton = (Button)this.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.config = new Config();
        this.config.loadValues();

        openMainMenu();
    }
}
