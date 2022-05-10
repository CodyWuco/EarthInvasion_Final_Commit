package com.gamecodeschool.earthinvasion;


import android.graphics.PointF;

class SmallLaserOrbSpec extends ObjectSpec {
    // This is all the unique specifications
    // for a player laser
    private static final String tag = "Small Laser Orb";
    private static final String bitmapName = "laser_orb";
    private static final float speed = 30f;
    private static final float maxHealthPoints = 1f;
    private static final float power = 5f;
    private static final PointF relativeScale =
            new PointF(64f, 36f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"};

    SmallLaserOrbSpec(){
        super(tag, bitmapName,
                speed, maxHealthPoints, power, relativeScale,
                components);
    }
}