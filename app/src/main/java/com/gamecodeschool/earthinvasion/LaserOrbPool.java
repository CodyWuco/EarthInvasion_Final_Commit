package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

import java.util.ArrayList;

// Wanna move the laser ArrayList into here and accept Lasers of different types
// maybe even create an ArrayList Pool Factory
// would like to have one pool and change power size and other attributes as needed
// instead of maintaining multiple pools
class LaserOrbPool {

    private Level mLevel;
    private SoundEngine mSoundEngine;
    private GameObject shooter;
    private GameObject target;
    ArrayList<GameObject> objects;

    LaserOrbPool(Level levelRef, SoundEngine soundEngineRef){
        mLevel = levelRef;
        mSoundEngine = soundEngineRef;
        objects = new ArrayList<>();
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public boolean spawnTowerSmallLaserOrb() {
        objects = mLevel.getPlayerLazers();
        if (canUseNextSmallLaser()) {
            setSmallLaserDirection();
            Level.mNextPlayerLaser++;
            mSoundEngine.playShoot();
            if (Level.mNextPlayerLaser == Level.LAST_PLAYER_LASER + 1) {
                // Just used the last laser
                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER;

            }
        }
        return true;
    }

    private boolean canUseNextSmallLaser(){
        return objects.get(Level.mNextPlayerLaser).spawn(shooter.getTransform());
    }

    private void setSmallLaserDirection(){
        objects.get(Level.mNextPlayerLaser).getTransform().setRotation(getLocationOfTarget());
    }

    public void shootSmallLaserOrb(GameObject shooterRef, GameObject targetRef){
        this.shooter = shooterRef;
        this.target = targetRef;
        spawnTowerSmallLaserOrb();
        mSoundEngine.playShoot();
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean spawnTowerLargeLaserOrb() {
        objects = mLevel.getLargeLazers();
        if (canUseNextLargeLaser()) {
            setLargeLaserDirection();
            Level.mNextLargeLaser++;
            mSoundEngine.playShoot();
            if (Level.mNextLargeLaser == Level.LAST_LARGE_LASER + 1) {
                // Just used the last laser
                Level.mNextLargeLaser = Level.FIRST_LARGE_LASER;

            }
        }
        return true;
    }

    private boolean canUseNextLargeLaser(){
        return objects.get(Level.mNextLargeLaser).spawn(shooter.getTransform());
    }

    private void setLargeLaserDirection(){
        objects.get(Level.mNextLargeLaser).getTransform().setRotation(getLocationOfTarget());
    }

    public void shootLargeLaserOrb(GameObject shooterRef, GameObject targetRef){
        this.shooter = shooterRef;
        this.target = targetRef;
        spawnTowerLargeLaserOrb();
        mSoundEngine.playShoot();
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private PointF getLocationOfTarget(){ return target.getLocation(); }
}
