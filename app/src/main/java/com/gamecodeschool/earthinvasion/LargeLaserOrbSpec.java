package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class LargeLaserOrbSpec extends ObjectSpec {
    // This is all the unique specifications
    // for a player laser
    private static final String tag = "Large Laser Orb";
    private static final String bitmapName = "laser_orb";
    private static final float speed = 5f;
    private static final float maxHealthPoints = 1f;
    private static final float power = 10f;
    private static final PointF relativeScale =
            new PointF(32f, 18f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"};

    LargeLaserOrbSpec(){
        super(tag, bitmapName,
                speed, maxHealthPoints, power, relativeScale,
                components);
    }
}

