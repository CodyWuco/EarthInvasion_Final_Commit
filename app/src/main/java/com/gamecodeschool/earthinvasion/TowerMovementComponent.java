package com.gamecodeschool.earthinvasion;

class TowerMovementComponent implements MovementComponent {
    public boolean move(long fps, Transform t, TargetingComponent targetingComponent){
        t.updateCollider();
        return true;
    }

    @Override
    public void reset() {

    }
}