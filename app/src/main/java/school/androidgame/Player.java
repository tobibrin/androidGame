package school.androidgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Tobi on 19.09.2017.
 */

public class Player implements GameObject{
    private Rect rect;
    private int color;

    public Player(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(this.rect, paint);
    }

    public Rect GetRect(){
        return this.rect;
    }

    @Override
    public void Update() { }

    public void Update(Point pos) {
        rect.set(pos.x - (rect.width() / 2), pos.y - (rect.height() / 2), pos.x + (rect.width() / 2), pos.y + (rect.height() / 2));
    }
}
