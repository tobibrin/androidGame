package school.androidgame.Utils;

/**
 * Created by kezab on 06.02.18.
 */

public class Config {

    private boolean useSensors;
    private int difficulty;

    public Config()
    {
        this.useSensors = false;
        this.difficulty = 0;

    }

    public boolean getUseSensors(){
        return this.useSensors;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public void setUseSensors(boolean use){
        this.useSensors = use;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    public void loadValues(){

    }
}
