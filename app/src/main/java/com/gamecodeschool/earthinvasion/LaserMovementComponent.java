package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;


class LaserMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, Transform t, TargetingComponent targetingComponent) {

        // Laser can only travel two screen widths
        float range = t.getmScreenSize().x ;


        // Where is the laser
        PointF location = t.getLocation();

        t.moveForward();

        // Has the laser gone out of range
        if(location.x < - range || location.x > range ||
        location.y < -range || location.y > range){
            // disable the laser
            return false;
        }

        t.updateCollider();
        return true;
    }

    @Override
    public void reset() {

    }
}