package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Iterator;

class AlienChaseMovementComponent implements MovementComponent {

    float mFPSDistanceBuffer = 20f;
    ArrayList<PointF> path;
    Iterator<PointF> itor;
    PointF pathLocation;

    AlienChaseMovementComponent(ArrayList<PointF> newPath){
        setPath(newPath);
        setPathItorator();
        nextPath();
    }

    @Override
    public boolean move(long fps, Transform t, TargetingComponent targeting) {

        isAtPathLocation(t, pathLocation);
        t.updateCollider();

        return true;
    }

    private void isAtPathLocation(Transform t, PointF playerLocation){
        if (Math.abs(t.getLocation().x - playerLocation.x) < mFPSDistanceBuffer &&
                Math.abs(t.getLocation().y - playerLocation.y) < mFPSDistanceBuffer){
            t.setLocation(pathLocation.x, pathLocation.y);
            nextPath();
        }else {
            t.moveToward(pathLocation);
        }
    }


    @Override
    public void reset() {
        setPathItorator();
        nextPath();
    }

    public void setPath(ArrayList<PointF> newPath){
        path = newPath;
    }

    private void setPathItorator(){
        itor = path.iterator();
    }

    private void nextPath(){
        if(itor.hasNext()) {
            pathLocation = itor.next();
        }else {

        }
    }
}

