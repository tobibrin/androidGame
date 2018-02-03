package school.androidgame.repositories;

import java.util.ArrayList;
import java.util.List;

import school.androidgame.Utils.bitmap.colors.BitmapColor;

/**
 * Created by tobi on 30.01.18.
 */

public class BitmapColorRepository {

    private List<BitmapColor> bitmapColors;

    private int index;

    public BitmapColorRepository() {
        this.bitmapColors = new ArrayList<BitmapColor>();
        this.index = 0;
    }

    public void addBitmapColor(BitmapColor bitmapColor) {
        this.bitmapColors.add(bitmapColor);
    }

    public void addBitmapColors(BitmapColor[] bitmapColors) {

        for (BitmapColor bitmapColor : bitmapColors) {
            this.addBitmapColor(bitmapColor);
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BitmapColor getBitmapAtIndex() {
        return this.bitmapColors.get(this.index);
    }

    public void nextBitMapColor() {

        if (this.index == this.bitmapColors.size() - 1) {
            this.index = 0;
        } else {
            this.index++;
        }
    }
}
