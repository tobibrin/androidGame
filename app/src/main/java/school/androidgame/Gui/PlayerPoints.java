package school.androidgame.Gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import school.androidgame.Core.HudElement;
import school.androidgame.GamePanel;

/**
 * Created by Tobi on 04.02.2018.
 */

public class PlayerPoints extends HudElement {

    private StringBuilder currentPointsStringBuilder;
    private int currentPoints;

    public PlayerPoints() {
        this.currentPointsStringBuilder = new StringBuilder();
        this.currentPoints = 0;
        this.setString();
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
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GRAY);
            paint.setTextSize(50);
            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);

            canvas.drawText(this.currentPointsStringBuilder.toString(), (GamePanel.WIDTH/2), 100, paint);
        }
    }
}
