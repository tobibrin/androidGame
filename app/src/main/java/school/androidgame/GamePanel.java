package school.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import school.androidgame.Entities.Player;
import school.androidgame.manager.EnemyManager;

/**
 * Created by Tobi on 18.09.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static float WIDTH;
    public static float HEIGHT;
    public static float DENSITY;

    private MainThread thread;
    private Context context;
    private Player player;
    private EnemyManager enemyManager;

    public GamePanel(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);

        this.getScreenSize();
        this.getDensity();

        this.thread = new MainThread(getHolder(), this);

        float playerWidth = this.getPlayerSize();

        this.player = new Player(this.context, 50, 50, (int)playerWidth, (int)playerWidth);
        this.enemyManager = new EnemyManager(this.context, this.player);

        setFocusable(true);
    }

    private float getPlayerSize() {
        float minValue = Math.min(GamePanel.HEIGHT, GamePanel.WIDTH);
        return minValue * 0.05f * GamePanel.DENSITY;
    }

    private void getDensity() {
        GamePanel.DENSITY = context.getResources().getDisplayMetrics().density;
    }

    private void getScreenSize() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (display != null) {
            display.getSize(size);

            GamePanel.WIDTH = size.x;
            GamePanel.HEIGHT = size.y;
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


            case MotionEvent.ACTION_MOVE:
                this.player.onActionMove(event);
        }
        return true;
    }

    public void update(float dt) {
        dt = dt / 1000;
        this.enemyManager.update(dt);
        this.player.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        this.enemyManager.draw(canvas);
        this.player.draw(canvas);

    }
}

