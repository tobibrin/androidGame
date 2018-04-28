package school.androidgame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kezab on 06.02.18.
 */

public class Config {

    private boolean useSensors;
    private int difficulty;
    private Context context;
    private ArrayList<Integer> scores;
    private SharedPreferences prefs;

    public Config(Context context)
    {
        this.context = context;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.useSensors = false;
        this.scores = new ArrayList<Integer>();
        for(int i=0; i < 5;i++)
        {
            this.scores.add(0);
        }
        this.difficulty = 0;
        this.loadValues();
    }

    public void loadValues()
    {
        if(this.prefs != null)
        {
            this.useSensors = prefs.getBoolean("useSensors", true);
            this.difficulty = prefs.getInt("difficulty", 0);
            for(int i=0; i < this.scores.size();i++)
            {
                this.scores.set(i, prefs.getInt("score_"+ i, 0));
            }
        }
    }

    public void saveValues()
    {
        if(this.prefs != null)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("useSensors", useSensors);
            editor.putInt("difficulty", difficulty);

            for(int i=0; i < this.scores.size();i++)
            {
                editor.putInt("score_" + i,this.scores.get(i));
            }

            editor.apply();
        }
    }

    public boolean getUseSensors()
    {
        return this.useSensors;
    }

    public void setUseSensors(boolean value)
    {
        this.useSensors = value;
    }

    public int getDifficulty()
    {
        return this.difficulty;
    }

    public ArrayList<Integer> getScores()
    {
        return this.scores;
    }

    public void addScore(int value)
    {
        int size = this.scores.size();

        for(int i = 0; i < this.scores.size();i++) {
            if (value > this.scores.get(i)) {
                this.scores.add(i, value);

                if (this.scores.size() > size)
                    this.scores.remove(size);
                break;
            }
        }
    }
}
