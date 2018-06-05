package school.androidgame.utils.bitmap.colors;

import android.graphics.Bitmap;

/**
 * Created by tobi on 30.01.18.
 */

public class BitmapColor {

    private Bitmap bitmap;
    private ObjectColorState objectColorState;

    public BitmapColor(Bitmap bitmap, ObjectColorState objectColorState) {
        this.bitmap = bitmap;
        this.objectColorState = objectColorState;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public ObjectColorState getObjectColorState() {
        return objectColorState;
    }
}
