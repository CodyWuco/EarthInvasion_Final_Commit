package com.gamecodeschool.earthinvasion;

class FPSTimer{

    private float timer = 0;
    private boolean isActive;

    FPSTimer(){
        pauseTimer();
    }

    FPSTimer(boolean isActive){
        this.isActive = isActive;
    }


    void reset(){ timer = 0; }

    void update(long fps) {
        if(isActive){
            timer += 1000 * (1/(float)fps);
        }
    }

    // time in milliaseconds
    int checkTimerMil(){ return (int) timer; }

    float checkTimerSec(){ return timer/1000;}

    void startTimer() { isActive = true; }

    void pauseTimer() { isActive = false; }
}