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
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000 / MainThread.MAX_FPS;
        long targetTime = timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;

        long lastFrameTime = 0;
        long dt = 0;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            dt = System.currentTimeMillis() - lastFrameTime;
            lastFrameTime = System.currentTimeMillis();

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update(dt);
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dt = System.currentTimeMillis();
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if(waitTime > 0) {
                    this.sleep(waitTime);

                }
            } catch (Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MainThread.MAX_FPS) {
//                this.averageFps = 1000 / ( (totalTime/frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }



        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


}
