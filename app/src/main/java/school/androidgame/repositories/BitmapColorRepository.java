package school.androidgame.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import school.androidgame.Utils.bitmap.colors.BitmapColor;
import school.androidgame.Utils.bitmap.colors.ObjectColorState;

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

    public BitmapColor getBitmapColorAtNextIndex() {

        int nextIndex = this.index;
        if (nextIndex == this.bitmapColors.size() - 1) {
           nextIndex = 0;
        } else {
            nextIndex += 1;
        }

        return this.bitmapColors.get(nextIndex);
    }

    public void nextBitMapColor() {

        if (this.index == this.bitmapColors.size() - 1) {
            this.index = 0;
        } else {
            this.index++;
        }
    }
}
