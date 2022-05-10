package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

abstract class ObjectSpec {
    private String mTag;
    private String mBitmapName;
    private float mSpeed;
    private PointF mSizeScale;
    private String[] mComponents;
    private float mMaxHealthPoint;
    private float mPower;

    ObjectSpec(String tag, String bitmapName, float speed, float maxHealthPoints, float power,
               PointF relativeScale, String[] components) {
        mTag = tag;
        mBitmapName = bitmapName;
        mSpeed = speed;
        mSizeScale = relativeScale;
        mComponents = components;
        mMaxHealthPoint = maxHealthPoints;
        mPower = power;
    }

    String getTag() {
        return mTag;
    }

    String getBitmapName() {
        return mBitmapName;
    }

    float getSpeed() {
        return mSpeed;
    }

    PointF getScale() {
        return mSizeScale;
    }

    String[] getComponents() {
        return mComponents;
    }

    float getMaxHealthPoint() {
        return mMaxHealthPoint;
    }

    float getPower(){ return mPower; }
}