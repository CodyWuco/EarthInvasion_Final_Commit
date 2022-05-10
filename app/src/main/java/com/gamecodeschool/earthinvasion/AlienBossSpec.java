package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class AlienBossSpec extends ObjectSpec {
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_1";
    private static final float speed = 1f;
    private static final float maxHealthPoints = 200f;
    private static final float power = 0f;
    private static final PointF relativeScale = new PointF(16f, 9f);
    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent",
            "BasicHealthComponent"};

    AlienBossSpec(){
        super(tag, bitmapName, speed, maxHealthPoints, power,
                relativeScale, components);
    }
}