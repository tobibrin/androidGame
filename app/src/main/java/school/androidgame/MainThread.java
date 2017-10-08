package school.androidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Tobi on 18.09.2017.
 */

public class MainThread extends Thread{

    public static final int MAX_FPS = 30;

    private double averageFps;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private Canvas canvas;

    private long startTime, timeMillis, targetTime, waitTime, totalTime, dt, lastFrameTime;
    private int frameCount;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

        this.timeMillis = 1000/MainThread.MAX_FPS;
        this.targetTime = timeMillis;
        this.frameCount = 0;
        this.totalTime = 0;
        this.lastFrameTime = 0;
        this.dt = 0;
    }

    @Override
    public void run() {
        this.targetTime = this.timeMillis;
        this.frameCount = 0;
        this.totalTime = 0;
        this.lastFrameTime = 0;
        this.dt = 0;

        while(running) {
            this.startTime = System.nanoTime();
            this.canvas = null;
            this.dt = System.currentTimeMillis() - lastFrameTime;
            this.lastFrameTime = System.currentTimeMillis();

            try {
                this.canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    this.gamePanel.update(this.dt);
                    this.gamePanel.draw(this.canvas);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(this.canvas != null) {
                    try{
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    this.dt = System.currentTimeMillis();
                }
            }

            this.timeMillis = (System.nanoTime() - this.startTime) / 1000000;
            this.waitTime = this.targetTime - this.timeMillis;

            try {
                if(this.waitTime > 0) {
                    MainThread.sleep(this.waitTime);

                }
            } catch (Exception e){
                e.printStackTrace();
            }

            this.totalTime += System.nanoTime() - this.startTime;
            this.frameCount++;
            if (this.frameCount == MainThread.MAX_FPS) {
//                this.averageFps = 1000 / ( (totalTime/frameCount) / 1000000);
                this.frameCount = 0;
                this.totalTime = 0;
            }



        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


}
