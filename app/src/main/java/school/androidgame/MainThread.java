package school.androidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Tobi on 18.09.2017.
 */

public class MainThread extends Thread{

    public static final int MAX_FPS = 60;

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
        long timeMillis = 1000 / this.MAX_FPS;;
        long targetTime = timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.Update();
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
            if (frameCount == this.MAX_FPS) {
                this.averageFps = 1000 / ( (totalTime/frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(this.averageFps);
            }



        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


}
