package com.developments.briffa.lewis.weightless;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Represents a game type surface view object responsible
 * for updating the canvas
 *
 * @author lewisbriffa
 * created on 11/11/2017
 */
public class GameSurfaceView extends SurfaceView implements Runnable {

    private SurfaceHolder mSurfaceHolder;
    private Thread mThread;
    private boolean isRunning = true;
    private Paint mPaint;
    private SpacemanObject mSpacemanObject;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float moveX;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private MeteorObject mMeteorObject;
    private Drawable spacebg;
    private SpaceBackgroundObject mSpaceBackgroundObject;

    public GameSurfaceView(final Context context) {
        super(context);

        mSurfaceHolder = getHolder();
        mThread = new Thread(this);
        mThread.start();

        mHandlerThread = new HandlerThread("My First HandlerThread");
        mHandlerThread.start();
        //mHandler = new Handler();

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

        mSpacemanObject = new SpacemanObject(0, ContextCompat.getDrawable(context, R.drawable.rocket));
        spacebg = ContextCompat.getDrawable(context, R.drawable.space_bg);

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    if(mSurfaceHolder.getSurface().isValid()) {

                        if(isRunning) {
                            mSpacemanObject.setDx(-(event.values[0]*8));
                            Canvas canvas = mSurfaceHolder.lockCanvas();
                            canvas.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
                            Paint paint = new Paint();
                            paint.setColor(Color.parseColor("#252525"));
                            canvas.drawRect(0,0,canvas.getWidth(), getHeight(), paint);

                            if (mSpaceBackgroundObject == null) {
                                mSpaceBackgroundObject = new SpaceBackgroundObject(spacebg, 0, canvas.getHeight());
                            } else {
                                mSpaceBackgroundObject.draw(canvas);
                            }

                            if(mMeteorObject == null) {
                                mMeteorObject = new MeteorObject((int)(Math.random()*canvas.getWidth()), canvas.getHeight(), (int)(Math.random()*canvas.getWidth()/2), Color.rgb((int)(Math.random()*100+256), (int)(Math.random()*100+256), (int)(Math.random()*100+256)));
                            }

                            if(mMeteorObject.getWidth() + mMeteorObject.getX() >= mSpacemanObject.getX() && mMeteorObject.getWidth() + mMeteorObject.getX() <= mSpacemanObject.getX() + mSpacemanObject.getWidth() && mMeteorObject.getY() <= mSpacemanObject.getHeight()
                                    || mMeteorObject.getX() <= mSpacemanObject.getX() + mSpacemanObject.getWidth() && mMeteorObject.getX() >= mSpacemanObject.getX() && mMeteorObject.getY() <= mSpacemanObject.getHeight()
                                    || mSpacemanObject.getX() >= mMeteorObject.getX() && mSpacemanObject.getX()+234 <= mMeteorObject.getWidth()+mMeteorObject.getX() && mMeteorObject.getY() <= mSpacemanObject.getHeight()) {
                                Intent gameOverIntent = new Intent(context, GameOverActivity.class);
                                context.startActivity(gameOverIntent);
                            }

                            if(mMeteorObject.getY()+100 <= 0) {
                                //mMeteorObject = new MeteorObject((int)(Math.random()*canvas.getWidth()),canvas.getHeight(), Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
                                mMeteorObject = new MeteorObject((int)(Math.random()*canvas.getWidth()), canvas.getHeight(), (int)(Math.random()*canvas.getWidth()/2), Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
                            } else {
                                mMeteorObject.move(canvas);
                            }

                            mSpacemanObject.move(canvas);
                            mSurfaceHolder.unlockCanvasAndPost(canvas);
                        } else {
                            mHandlerThread.interrupt();
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // stub
            }
        }, mSensor, SensorManager.SENSOR_DELAY_GAME, mHandler);
    }

    @Override
    public void run() {
//        while(isRunning)
//        {
//            if(!mSurfaceHolder.getSurface().isValid()) {
//                continue;
//            }
//            Canvas canvas = mSurfaceHolder.lockCanvas();
//            mMeteorObject.move(canvas);
//            mSurfaceHolder.unlockCanvasAndPost(canvas);
//            try {
//                Thread.sleep((long)(Math.random()*1000+3000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void quitHandlerThread() {
        mHandlerThread.quit();
    }
}
