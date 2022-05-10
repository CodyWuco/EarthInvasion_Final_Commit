package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class SmallLaserOrbTowerSpec extends ObjectSpec {
    // This is all the unique specifications
    // for a player
    private static final String tag = "Tower";
    private static final String bitmapName = "cannon_2";
    private static final float speed = 1f;
    private static final float maxHealthPoints = 1f;
    private static final float power = 5f;
    private static final PointF relativeScale =
            new PointF(16f, 9f);

    private static final String[] components = new String [] {
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "TowerMovementComponent",
            "TowerSpawnComponent",
            "BasicHealthComponent",
            "SmallLaserOrbAttackComponent"};

    SmallLaserOrbTowerSpec() {
        super(tag, bitmapName,
                speed, maxHealthPoints, power, relativeScale,
                components);
    }
}