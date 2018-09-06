package school.androidgame.powerUps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Timer;
import java.util.TimerTask;

import school.androidgame.R;
import school.androidgame.core.GameObject;
import school.androidgame.interfaces.ISpeed;
import school.androidgame.utils.Vector2D;

public class SpeedPowerUp extends GameObject {

    private Paint paint;
    private Bitmap bitmap;
    private Timer timer;
    private ISpeed speedChangeAble;
    private float factor;
    private float oldSpeed;
    private int size = 64;

    private void timerCleanup() {
        if (this.timer != null) {
            this.timer.cancel();
        }
    }

    public SpeedPowerUp(Context context, ISpeed speed, float factor, Vector2D spawnPos) {
        int halfSize = this.size / 2;
        this.setX(spawnPos.x - halfSize);
        this.setY(spawnPos.y - halfSize);
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.speed_power_up);
        System.out.println("gsjdsnfdg " + this.bitmap.getWidth() + " " + this.bitmap.getHeight());

        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, this.size, this.size, false);
        this.paint = new Paint();
        this.factor = factor;
        this.speedChangeAble = speed;
    }

    public void apply(int duration) {
        this.oldSpeed = this.speedChangeAble.getSpeed();
        this.speedChangeAble.setSpeed(this.speedChangeAble.getSpeed() * factor);

        this.timer = new Timer();
        this.timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        speedChangeAble.setSpeed(oldSpeed);
                        timerCleanup();
                    }
                },
                duration
        );
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Canvas canvas) {
        if (this.bitmap != null) {
            canvas.drawBitmap(this.bitmap, this.getX(), this.getY(), this.paint);
        }
    }
}
