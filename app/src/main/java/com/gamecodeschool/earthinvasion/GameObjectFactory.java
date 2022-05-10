package com.gamecodeschool.earthinvasion;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;
    ArrayList<PointF> path;

    GameObjectFactory(Context c, PointF screenSize,
                      GameEngine gameEngine) {

        this.mContext = c;
        this.mScreenSize = screenSize;
        mGameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec) {
        GameObject object = new GameObject();

        int mNumComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        object.setmTag(spec.getTag());

        // Configure the speed relative to the screen size
        float mSpeed = (mScreenSize.x / 500) * spec.getSpeed();

        // Configure the object size relative to screen size
        PointF objectSize =
                new PointF(mScreenSize.x / spec.getScale().x,
                        mScreenSize.y / spec.getScale().y);

        // Set the location to somewhere off-screen
        PointF mLocation = new PointF(HIDDEN, HIDDEN);

        object.setTransform(new Transform(mSpeed, objectSize.x,
                objectSize.y, mLocation, mScreenSize));


        // Loop through and add/initialize all the components
        for (int i = 0; i < mNumComponents; i++) {
            switch (spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent
                            (mGameEngineReference));
                    break;
                case "BasicHealthComponent":
                    object.setHealthComponent(new BasicHealthComponent(),
                            spec.getMaxHealthPoint());
                    break;
                case "HitScanAttackComponent":
                    object.setAttackComponent(new HitScanAttackComponent(mGameEngineReference,
                                    object), spec.getPower());
                    break;
                case "SmallLaserOrbAttackComponent":
                    object.setAttackComponent(new SmallLaserOrbAttackComponent(mGameEngineReference,
                            object), spec.getPower());
                    break;
                case "LargeLaserOrbAttackComponent":
                    object.setAttackComponent(new LargeLaserOrbAttackComponent(mGameEngineReference,
                            object), spec.getPower());
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;
                case "TowerMovementComponent":
                    object.setMovement(new TowerMovementComponent());
                    break;
                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;
                case "TowerSpawnComponent":
                    object.setSpawner(new TowerSpawnComponent());
                    break;
                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;
                case "BaseSpawnComponent":
                    object.setSpawner(new BaseSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                case "AlienChaseMovementComponent":
                    object.setMovement(
                            new AlienChaseMovementComponent(path));
                    break;
                case "AlienHorizontalSpawnComponent":
                    object.setSpawner(new AlienHorizontalSpawnComponent());
                    break;


                default:
                    // Error unidentified component
                    break;
            }
        }
        // Return the completed GameObject to the Level class
        return object;
    }

    public void setPath(ArrayList<PointF> newPath){
        path = newPath;
    }
}