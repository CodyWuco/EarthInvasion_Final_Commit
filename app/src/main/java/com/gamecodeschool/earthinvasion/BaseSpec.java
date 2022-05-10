package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class BaseSpec extends ObjectSpec {
    private static final String tag = "Base";
    private static final String bitmapName = "background";
    private static final float speed = 0f;
    private static final float maxHealthPoints = 0f;
    private static final float power = 00f;
    private static final PointF relativeScale =
            new PointF(16f, .5f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "BackgroundMovementComponent",
            "BaseSpawnComponent"};

    BaseSpec() {
        super(tag, bitmapName,
                speed, maxHealthPoints, power, relativeScale,
                components);
    }
}

