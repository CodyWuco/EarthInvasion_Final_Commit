package com.gamecodeschool.earthinvasion;

import java.util.ArrayList;

public class BackgroundMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform t, TargetingComponent targetingComponent) {
        return true;
    }

    @Override
    public void reset() {

    }
}
