package com.developments.briffa.lewis.weightless;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    private SpaceBackgroundObject mSpaceBackgroundObject;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float moveX;

    public GameSurfaceView(Context context) {
        super(context);

        mSurfaceHolder = getHolder();
        mThread = new Thread(this);
        mThread.start();

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

        mSpacemanObject = new SpacemanObject(0, ContextCompat.getDrawable(context, R.drawable.spaceman));
        mSpaceBackgroundObject = new SpaceBackgroundObject();

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    if(mSurfaceHolder.getSurface().isValid()) {

                        mSpacemanObject.setDx(event.values[0]*4);
                        Canvas canvas = mSurfaceHolder.lockCanvas();
                        //canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
                        //mSpaceBackgroundObject.draw(canvas);
                        mSpacemanObject.move(canvas);
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // stub
            }
        }, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void run() {
        while(isRunning)
        {
            if(!mSurfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = mSurfaceHolder.lockCanvas();
            mSpaceBackgroundObject.draw(canvas);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
