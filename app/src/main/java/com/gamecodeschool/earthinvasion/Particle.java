package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

// either needs to be removed or reduced in size to be used as a death animation
class Particle {

    PointF mVelocity;
    PointF mPosition;

    Particle(PointF direction)
    {
        mVelocity = new PointF();
        mPosition = new PointF();

        // Determine the direction
        mVelocity.x = direction.x;
        mVelocity.y = direction.y;
    }

    void update(float fps)
    {
        // Move the particle
        mPosition.x += mVelocity.x;
        mPosition.y += mVelocity.y;
    }

    void setPosition(PointF position)
    {
        mPosition.x = position.x;
        mPosition.y = position.y;
    }

    PointF getPosition()
    {
        return mPosition;
    }
}