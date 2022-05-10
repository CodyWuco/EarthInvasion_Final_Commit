package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;
import android.graphics.RectF;

class Transform {

    private boolean mReversedFirst = false;

    private RectF mCollider;
    private PointF mLocation;
    private double mRotation;


    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;

    // these might be useless now
    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private int mXClip;

    Transform(float speed, float objectWidth,
              float objectHeight,
              PointF startingLocation,
              PointF screenSize){

        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
    }

    boolean getReversedFirst(){
        return mReversedFirst;
    }


    int getXClip(){
        return mXClip;
    }


    PointF getmScreenSize(){
        return mScreenSize;
    }


    void headUp(){
        mHeadingUp = true;
        mHeadingDown = false;

    }

    void headDown(){
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void updateCollider(){
        // Pull the borders in a bit (10%)
        mCollider.top = mLocation.y + (mObjectHeight / 10);
        mCollider.left = mLocation.x + (mObjectWidth /10);
        mCollider.bottom = (mCollider.top + mObjectHeight)
                - mObjectHeight/10;

        mCollider.right = (mCollider.left + mObjectWidth)
                -  mObjectWidth/10;
    }

    void stopVertical(){
        mHeadingDown = false;
        mHeadingUp = false;
    }

    float getSpeed(){
        return mSpeed;
    }

    void setLocation(float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }


    PointF getLocation() { return mLocation; }

    PointF getSize(){
        return new PointF((int)mObjectWidth,
                (int)mObjectHeight);
    }

    // this likely needs removal
    void flip(){
        mFacingRight = !mFacingRight;
    }

    RectF getCollider(){
        return mCollider;
    }

    void moveToward(PointF target){
        setRotation(target);
        moveForward();
    }

    void moveForward(){
        mLocation.x += mSpeed * (Math.cos(mRotation));
        mLocation.y += mSpeed * (Math.sin(mRotation));
    }

    private double angleToTarget(PointF target){
        return Math.toRadians(Math.toDegrees(Math.atan2(target.y - mLocation.y,
                target.x - mLocation.x)));
    }

    void setRotation(float rotation){ mRotation = Math.toRadians(rotation); }

    void setRotation(PointF target){
        mRotation = angleToTarget(target);
    }

    double getRotation(){ return mRotation; }

    // this needs to be updated and used for new tower models
    PointF getFiringLocation(float laserLength){
        PointF mFiringLocation = new PointF();

        if(mFacingRight) {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f);
        }else
        {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f) - (laserLength);
        }
        // Move the height down a bit of ship height from origin
        mFiringLocation.y = mLocation.y + (mObjectHeight / 1.28f);
        return mFiringLocation;
    }
}