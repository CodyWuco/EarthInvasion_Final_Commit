package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class AlienSlimeSpec extends ObjectSpec {
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_3";
    private static final float speed = .5f;
    private static final float maxHealthPoints = 50f;
    private static final float power = 0f;
    private static final PointF relativeScale = new PointF(32f, 18f);
    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent",
            "BasicHealthComponent"};

    AlienSlimeSpec(){
        super(tag, bitmapName, speed, maxHealthPoints, power,
                relativeScale, components);
    }
}