package school.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import school.androidgame.manager.GameManager;

/**
 * Created by Tobi on 18.09.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static float WIDTH;
    public static float HEIGHT;
    public static float DENSITY;
    public static float MIN_WIDTH_HEIGHT;


    private GameManager game;
    private MainThread thread;
    final private Context context;


    public GamePanel(GameManager game) {
        super(game.context);
        this.game = game;
        this.context = game.context;
        getHolder().addCallback(this);

        this.getScreenSize();
        this.getDensity();

        this.thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }


    private void getDensity() {
        GamePanel.DENSITY = (float)this.context.getResources().getDisplayMetrics().densityDpi / 320.0f;
    }

    private void getScreenSize() {
        WindowManager wm = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (display != null) {
            display.getSize(size);

            GamePanel.WIDTH = size.x;
            GamePanel.HEIGHT = size.y;

            GamePanel.MIN_WIDTH_HEIGHT = Math.min(GamePanel.WIDTH, GamePanel.HEIGHT);
        }
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
        while (this.thread.isAlive()) {
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
                this.game.player.onActionDown(event);


            case MotionEvent.ACTION_MOVE:
                this.game.player.onActionMove(event);
        }
        return true;
    }

    public void update(float dt) {
        dt = dt / 1000;
        this.game.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        this.game.draw(canvas);
    }

    public void stopGame() {
        if (this.thread != null) {

            this.thread.setRunning(false);
            this.thread.interrupt();
        }
    }
}

