package com.developments.briffa.lewis.weightless.game;

/**
 * Class: Responsible for defining the background activity for the game
 */
public class GameRunnable implements Runnable {

    private volatile boolean isRunning;

    private GameSurfaceView mGameSurfaceView;


    public GameRunnable(GameSurfaceView gameSurfaceView) {
        isRunning = true;
        mGameSurfaceView = gameSurfaceView;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        while(isRunning) {
            mGameSurfaceView.draw();
        }
    }
}
