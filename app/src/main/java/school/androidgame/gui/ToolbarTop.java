package school.androidgame.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import school.androidgame.core.HudElement;
import school.androidgame.GamePanel;

/**
 * Created by tobi on 24.04.18.
 */

public class ToolbarTop extends HudElement {

    private int height;
    private int width;
    private Rect toolbarTopRect;
    private Paint paint;

    public ToolbarTop() {
        this.height = (int)(GamePanel.HEIGHT / 15);
        this.width = (int)GamePanel.WIDTH;
        this.toolbarTopRect = new Rect(0,0, this.width, this.height);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(this.toolbarTopRect, this.paint);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
