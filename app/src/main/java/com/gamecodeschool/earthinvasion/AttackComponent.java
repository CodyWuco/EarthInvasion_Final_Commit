package com.gamecodeschool.earthinvasion;

interface AttackComponent {

    void attackTarget();

    void setPower(float power);

    float getPower();

    void update(long fps);
}
