package com.gamecodeschool.earthinvasion;


interface MovementComponent {

    boolean move(long fps,
                 Transform t,
                 TargetingComponent targeting);

    void reset();
}