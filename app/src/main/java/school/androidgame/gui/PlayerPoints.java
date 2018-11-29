package school.androidgame.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.core.HudElement;
import school.androidgame.utils.Vector2D;

/**
 * Created by Tobi on 04.02.2018.
 */

public class PlayerPoints extends HudElement {

    private StringBuilder currentPointsStringBuilder;
    private int currentPoints;
    private Paint paint;

    public PlayerPoints(float x, float y) {
        this.currentPointsStringBuilder = new StringBuilder();
        this.currentPoints = 0;
        this.createPaint();
        this.setString();
        this.position = new Vector2D(x,y + this.paint.getTextSize() / 2);
    }

    private void createPaint() {
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.GRAY);
        this.paint.setTextSize(50);
        this.paint.setAntiAlias(true);
        this.paint.setFakeBoldText(true);
    }

    private void setString() {
        this.currentPointsStringBuilder.setLength(0);
        this.currentPointsStringBuilder.append(this.currentPoints);
    }

    public void update(int points) {
        this.currentPoints = points;
        this.setString();
    }

    public void draw(Canvas canvas) {
        if (this.getVisibility()) {
            canvas.drawText(this.currentPointsStringBuilder.toString(),
                    this.position.x - (this.paint.measureText(this.currentPointsStringBuilder.toString()) / 2),
                    this.position.y,
                    this.paint);
        }
    }
}
