package school.androidgame.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.concurrent.TimeUnit;

import school.androidgame.core.HudElement;
import school.androidgame.utils.Vector2D;

/**
 * Created by tobi on 24.04.18.
 */

public class NextPlayerColorHudElement extends HudElement {

    private Bitmap lastBitmap;
    private Bitmap previewColorBitmap;

    private String timeString;
    private Paint previewColorPaint;

    private Paint timeStringPaint;
    private int textPaddingLeft = 20;

    private int millis;
    private int bitmapSizeToUse;
    private int halfBitmapSize;
    private int timeForNextBitmap;

    public NextPlayerColorHudElement(Vector2D position, Bitmap previewColorBitmap, int timeForNextBitmap) {
        this.position = position;
        this.timeString = "0.0";

        this.setupPaint();
        this.setupPreviewColorPaint();
        this.setPreviewColorBitmap(previewColorBitmap);
        this.lastBitmap = previewColorBitmap;
        this.timeForNextBitmap = timeForNextBitmap;
        this.millis = timeForNextBitmap;
    }



    private void setupPreviewColorPaint() {
        this.previewColorPaint = new Paint();
        this.previewColorPaint.setColor(Color.RED);
        this.previewColorPaint.setAlpha(255);
    }

    private void setupPaint() {
        int textSize = 50;
        this.timeStringPaint = new Paint();
        this.timeStringPaint.setColor(Color.WHITE);
        this.timeStringPaint.setStyle(Paint.Style.FILL);
        this.timeStringPaint.setTextSize(textSize);
        this.timeStringPaint.setAntiAlias(true);
        this.timeStringPaint.setFakeBoldText(true);

        this.bitmapSizeToUse = textSize;
        this.halfBitmapSize = (int) textSize/2;
    }

    public void draw(Canvas canvas) {

        if (this.previewColorBitmap != null) {
            canvas.drawBitmap(this.previewColorBitmap,
                    this.position.x - this.halfBitmapSize,
                    this.position.y - this.halfBitmapSize,
                    this.previewColorPaint);
        }

        canvas.drawText(
                this.timeString,
                this.position.x + this.halfBitmapSize + this.textPaddingLeft,
                this.position.y + this.halfBitmapSize,
                this.timeStringPaint
        );
    }

    public void setTimeLeft(float dt) {
        this.millis -= dt*1000;
        if (this.millis < 0) {
            this.millis = 0;
        }
    }


    public void setPreviewColorBitmap(Bitmap bitmap) {

        if (bitmap != this.lastBitmap) {
            this.previewColorBitmap = Bitmap.createScaledBitmap(bitmap, this.bitmapSizeToUse, this.bitmapSizeToUse, false);
            this.lastBitmap = bitmap;
            this.millis = this.timeForNextBitmap;
        }

        float seconds = this.millis / 1000.0f;
        this.timeString = String.format("%.2f", seconds);
    }
}
