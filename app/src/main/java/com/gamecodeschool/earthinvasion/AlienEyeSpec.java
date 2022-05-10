package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class AlienEyeSpec extends ObjectSpec {
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_2";
    private static final float speed = 1.5f;
    private static final float maxHealthPoints = 50f;
    private static final float power = 0f;
    private static final PointF relativeScale = new PointF(16f, 9f);
    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent",
            "BasicHealthComponent"};

    AlienEyeSpec(){
        super(tag, bitmapName, speed, maxHealthPoints, power,
                relativeScale, components);
    }
}