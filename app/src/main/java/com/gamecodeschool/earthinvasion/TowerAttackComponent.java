package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class TowerAttackComponent implements AttackComponent {

    TargetingComponent targeting;
    protected GameEngine gameEngine;
    protected GameObject selfRef;
    protected float damage;
    private PointF location;
    private Transform transform;
    private FPSTimer attackTimer;
    protected float attacksPerSecond = 1f;
    // change this to vary based on screen
    private float range = 3000;

    TowerAttackComponent(GameEngine gameEngine, GameObject selfRef){
        transform = selfRef.getTransform();
        this.selfRef = selfRef;
        attackTimer = new FPSTimer(true);
        targeting = new FrontTargetingComponent(gameEngine);
        this.gameEngine = gameEngine;
    }



    float distanceToTarget(PointF o){
        location = transform.getLocation();
        return (float) Math.sqrt(Math.pow(o.x - location.x, 2)
                + Math.pow(o.y - location.y, 2));
    }

    @Override
    public void attackTarget() {
        if(targeting.hasTarget()){
            if (readyToAttack()) {
                shootShotType();
                attackTimer.reset();
                transform.setRotation(targeting.getTarget().getTransform().getLocation());
            }
        }else {
            targeting.findTarget();
        }
    }

    // override this to change shot type
    protected void shootShotType(){}

    boolean readyToAttack(){ return attackTimer.checkTimerMil() >= 1000 / attacksPerSecond; }

    public GameObject getShooter() { return selfRef; }

    @Override
    public void setPower(float power) {
        damage = power;
    }

    @Override
    public float getPower(){ return damage; }

    @Override
    public void update(long fps) {
        attackTimer.update(fps);
        attackTarget();
    }
}

