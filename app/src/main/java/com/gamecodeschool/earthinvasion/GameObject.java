package com.gamecodeschool.earthinvasion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

class GameObject {

    private Transform mTransform;
    private boolean isActive = false;
    private String mTag;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;
    private HealthComponent healthComponent;
    private AttackComponent attackComponent;
    private TargetingComponent targetingComponent;

    void setHealthComponent(HealthComponent healthComponent, float maxHealth) {
        this.healthComponent = healthComponent;
        healthComponent.initialize(maxHealth);
    }

    void doDamage(float rawDamage){ healthComponent.doDamage(rawDamage); }

    void setAttackComponent(AttackComponent attackComponent, float power) {
        this.attackComponent = attackComponent;
        attackComponent.setPower(power);
    }

    float getPower(){ return attackComponent.getPower(); }

    // this is intended to clean up the interactions between the attack
    // components and the targeting component
    void setTargetingComponent(TargetingComponent targetingComponent){
        this.targetingComponent = targetingComponent;
    }

    MovementComponent getMovementComponent() { return movementComponent; }

    void setSpawner(SpawnComponent s) {
        spawnComponent = s;
    }

    void setGraphics(GraphicsComponent g, Context c,
                     ObjectSpec spec, PointF objectSize) {

        graphicsComponent = g;
        g.initialize(c, spec, objectSize);
    }

    void setMovement(MovementComponent m) {
        movementComponent = m;
    }

    void setInput(InputComponent s) {
        s.setTransform(mTransform);
    }

    void setmTag(String tag) {
        mTag = tag;
    }

    void setTransform(Transform t) {
        mTransform = t;
    }

    void draw(Canvas canvas, Paint paint) {
        graphicsComponent.draw(canvas, paint, mTransform);
    }

    // array list was intended to create a cleaner way to pass in paths and targeting
    // which might be unessessary
    void update(long fps, ArrayList<GameObject> objects) {
        if (!(movementComponent.move(fps,
                mTransform, targetingComponent)))
        {
            // Component returned false
            isActive = false;
        }

        if (healthComponent != null) {
            healthComponent.update(this);
        }

        if (attackComponent != null){
            attackComponent.update(fps);
        }
    }

    boolean checkActive() {
        return isActive;
    }

    String getTag() {
        return mTag;
    }

    void setInactive() {
        isActive = false;
    }

    boolean spawn(Transform playerTransform) {
        // Only spawnComponent if not already active
        if (!isActive) {
            spawnComponent.spawn(playerTransform, mTransform);
            isActive = true;
            System.out.println("I am a;  " + getTag());
            return true;
        }
        return false;
    }

    Transform getTransform() {
        return mTransform;
    }

    PointF getLocation() { return mTransform.getLocation(); }
}