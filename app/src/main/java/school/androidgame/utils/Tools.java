package school.androidgame.utils;

import android.graphics.Bitmap;

/**
 * Created by kezab on 16.01.18.
 */

public class Tools {

    public static Bitmap replaceColor(Bitmap bm, int fromColor, int targetColor) {
        if(bm == null) {
            return null;
        }

        int width = bm.getWidth();
        int height = bm.getHeight();
        int[] pixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int x = 0; x < pixels.length; ++x) {
            pixels[x] = (pixels[x] == fromColor) ? targetColor : pixels[x];
        }

        Bitmap newImage = Bitmap.createBitmap(width, height, bm.getConfig());
        newImage.setPixels(pixels, 0, width, 0, 0, width, height);

        return newImage;
    }

    static public final float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }
}
