package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class AlienTestSpec extends ObjectSpec {
    // This current alien test spec
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_1";
    private static final float speed = 1f;
    private static final float maxHealthPoints = 500f;
    private static final float power = 0f;
    private static final PointF relativeScale = new PointF(16f, 9f);
    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent",
            "BasicHealthComponent"};

    AlienTestSpec(){
        super(tag, bitmapName, speed, maxHealthPoints, power,
                relativeScale, components);
    }
}