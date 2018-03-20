package school.androidgame.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kezab on 06.02.18.
 */

public class Config {

    public static boolean useSensors = false;
    public static int difficulty = 0;
    public static Context context = null;
    private static boolean isLoaded = false;


    public static void loadValues(){
        if(context != null && isLoaded) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.getBoolean("useSensors", useSensors);
            prefs.getInt("difficulty", difficulty);
            isLoaded = true;
            System.out.println("UseSensors: " + useSensors);
        }
    }

    public static void saveValues(){
        if(context != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("useSensors", useSensors);
            editor.putInt("difficulty", difficulty);
            editor.commit();
        }
    }
}