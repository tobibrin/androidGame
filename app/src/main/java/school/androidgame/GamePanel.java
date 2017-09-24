package school.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Tobi on 18.09.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private boolean isAbleToMove = false;
    private Player player;
    private Point playerPos;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.thread = new MainThread(getHolder(), this);
        this.player = new Player(new Rect(100, 100, 200, 200), Color.MAGENTA);
        this.playerPos = new Point(150,150);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // start gameloop after surface is ready
        this.thread = new MainThread(getHolder(), this);

        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        while (true) {
            try {
                this.thread.setRunning(false);
                this.thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.isAbleToMove = this.player.GetRect().contains((int) event.getX(), (int) event.getY());

            case MotionEvent.ACTION_MOVE:
                if(this.isAbleToMove) {
                    this.playerPos.set((int) event.getX(), (int) event.getY());
                }
        }
        return true;
    }

    //Frame update
    public void Update() {
        player.Update(this.playerPos);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        this.player.Draw(canvas);

    }
}

