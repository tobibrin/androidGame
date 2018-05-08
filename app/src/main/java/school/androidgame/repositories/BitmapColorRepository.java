package school.androidgame.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import school.androidgame.utils.bitmap.colors.BitmapColor;
import school.androidgame.utils.bitmap.colors.ObjectColorState;

/**
 * Created by tobi on 30.01.18.
 */

public class BitmapColorRepository {

    private Random random;
    private List<BitmapColor> bitmapColors;

    private int index;

    public BitmapColorRepository() {
        this.random = new Random();
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

    public void setRandomIndex() {
        int maxIndex = this.bitmapColors.size();
        this.index = random.nextInt(maxIndex);
    }

    public ObjectColorState getCurrentColorState() {
        return this.getBitmapColorAtCurrentIndex().getObjectColorState();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BitmapColor getBitmapColorAtCurrentIndex() {
        return this.bitmapColors.get(this.index);
    }

    public void nextBitMapColor() {
        this.index = (this.index == this.bitmapColors.size() - 1) ? 0 : this.index +1;
    }
}
