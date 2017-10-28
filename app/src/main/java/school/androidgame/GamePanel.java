package school.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import school.androidgame.Entities.Player;

/**
 * Created by Tobi on 18.09.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Context Context;
    private school.androidgame.Entities.Player player;

    private boolean isAbleToMove = false;

    public GamePanel(Context context) {
        super(context);
        this.Context = context;
        getHolder().addCallback(this);
        this.thread = new MainThread(getHolder(), this);

        this.player = new Player(this.Context, 50,50, 64, 64);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
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
                this.player.onActionDown(event);
//                this.isAbleToMove = this.player.GetRect().contains((int) event.getX(), (int) event.getY());


            case MotionEvent.ACTION_MOVE:
                this.player.onActionMove(event);
//                if(this.isAbleToMove) {
//                    this.playerPos.set((int) event.getX(), (int) event.getY());
//                }
        }
        return true;
    }

    public void update(float dt) {

        this.player.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        this.player.draw(canvas);

    }
}

