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

    public static int HEIGHT;
    private Rect toolbarTopRect;
    private Paint paint;

    public ToolbarTop() {
        ToolbarTop.HEIGHT = (int) (GamePanel.HEIGHT / 15);
        this.toolbarTopRect = new Rect(0, 0, (int) GamePanel.WIDTH, ToolbarTop.HEIGHT);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(this.toolbarTopRect, this.paint);
    }
}
