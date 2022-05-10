package com.gamecodeschool.earthinvasion;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

class Level {

    // these need to be phased out to lessen the maintenance costs
    public static final int BACKGROUND_INDEX = 0;
    public static final int BASE_INDEX = 1;
    public static final int PLAYER_LASER_AMOUNT = 10;
    public static final int FIRST_PLAYER_LASER = 0;
    public static final int LAST_PLAYER_LASER = 9;
    public static int mNextPlayerLaser;
    public static final int FIRST_LARGE_LASER = 0;
    public static final int LAST_LARGE_LASER = 9;
    public static int mNextLargeLaser;
    // delay in milliseconds
    public static final float STARTING_SPAWN_DELAY = 3000;

    public PointF screenRes;
    GameObjectFactory factory;
    private FPSTimer enemySpawnTimer;
    private float spawnDelay = STARTING_SPAWN_DELAY;
    private Random rand;

    // This will hold all the instances of GameObject
    private ArrayList<GameObject> objects;
    private ArrayList<PointF> path;
    private ArrayList<PointF> path2;
    private ArrayList<GameObject> enemies;
    private ArrayList<GameObject> towers;
    // move these to projectile pool
    private ArrayList<GameObject> smallLasers;
    private ArrayList<GameObject> largeLasers;

    public Level(Context context,
                 PointF mScreenSize,
                 GameEngine ge){

        screenRes = mScreenSize;
        objects = new ArrayList<>();
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        smallLasers = new ArrayList<>();
        largeLasers = new ArrayList<>();
        path = new ArrayList<>();
        path2 = new ArrayList<>();
        factory = new GameObjectFactory(context, mScreenSize, ge);


        rand = new Random();
        enemySpawnTimer = new FPSTimer(true);
        createPath();
        createPath2();
        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory){

        factory.setPath(path);

        objects.clear();
        enemies.clear();
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));
        objects.add(BASE_INDEX, factory.create(new BaseSpec()));

        objects.add(2, factory.create(new HitScanTowerSpec()));
        objects.add(3, factory.create(new SmallLaserOrbTowerSpec()));
        objects.add(4, factory.create(new LargeLaserOrbTowerSpec()));

        // Spawn the player's lasers
        for (int i = 0; i < PLAYER_LASER_AMOUNT; i++) {
            spawnPlayerLaser();
        }

        mNextPlayerLaser = FIRST_PLAYER_LASER;


        // Create some alien lasers
        for (int i = FIRST_LARGE_LASER; i != LAST_LARGE_LASER + 1; i++) {
            spawnLargeLaser();
        }
        mNextLargeLaser = FIRST_LARGE_LASER;


        return objects;
    }

    // spawns should be handled by the level
    public void deSpawnReSpawn() {
        ArrayList<GameObject> objects = getGameObjects();

        for(GameObject o : objects){
            o.setInactive();
        }
        objects.get(Level.BACKGROUND_INDEX).spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());
        objects.get(Level.BASE_INDEX).spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());

        objects.get(2).spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());
        objects.get(2).getTransform().setLocation(600, 700);
        objects.get(3).spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());
        objects.get(3).getTransform().setLocation(600, 300);
        objects.get(4).spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());
        objects.get(4).getTransform().setLocation(1200,150);

    }


    void spawnEnemy(){
        GameObject newAlien = factory.create(new AlienTestSpec());
        newAlien.spawn(objects.get(Level.BACKGROUND_INDEX).getTransform());
        enemies.add(newAlien);
    }

    void enemySpawner(long fps){
        enemySpawnTimer.update(fps);
        if (enemySpawnTimer.checkTimerMil() >= spawnDelay) {
            randomPath();
            spawnEnemy();
            enemySpawnTimer.reset();
            spawnDelay = 1000 + rand.nextInt(4000);
        }
    }

    void randomPath(){
        if (rand.nextInt(2) == 1){
            factory.setPath(path);
        }else {
            factory.setPath(path2);
        }
    }

    void spawnPlayerLaser(){ smallLasers.add(factory.create(new SmallLaserOrbSpec())); }

    void spawnLargeLaser(){ largeLasers.add(factory.create(new LargeLaserOrbSpec())); }


    ArrayList<GameObject> getEnemyGameObjects(){ return enemies; }
    ArrayList<GameObject> getPlayerLazers() { return smallLasers; }
    ArrayList<GameObject> getLargeLazers() { return largeLasers; }

    ArrayList<GameObject> getGameObjects(){
        ArrayList<GameObject> temp = new ArrayList<>();
        temp.addAll(objects);
        temp.addAll(enemies);
        temp.addAll(smallLasers);
        temp.addAll(largeLasers);
        return temp;
    }

    void update(long fps){
        enemySpawner(fps);
    }

    private void createPath(){
        // create a path
        float currX= 0, currY = 0;
        currY = tileYLocation(4);
        path.add(new PointF(currX,currY));
        currX = tileXLocation(7);
        path.add(new PointF(currX,currY));

        currY = tileYLocation(6);
        path.add(new PointF(currX,currY));
        currX = tileXLocation(10);
        path.add(new PointF(currX,currY));

        currY = tileYLocation(4);
        path.add(new PointF(currX,currY));
        currX = tileXLocation(12);
        path.add(new PointF(currX,currY));

        currY = tileYLocation(1);
        path.add(new PointF(currX,currY));
        currX = tileXLocation(16);
        path.add(new PointF(currX,currY));
    }

    private void createPath2(){
        // create a path
        float currX= 0, currY = 0;
        currY = tileYLocation(4);
        path2.add(new PointF(currX,currY));
        currX = tileXLocation(7);
        path2.add(new PointF(currX,currY));

        currY = tileYLocation(6);
        path2.add(new PointF(currX,currY));
        currX = tileXLocation(10);
        path2.add(new PointF(currX,currY));

        currY = tileYLocation(4);
        path2.add(new PointF(currX,currY));
        currX = tileXLocation(14);
        path2.add(new PointF(currX,currY));

        currY = tileYLocation(1);
        path2.add(new PointF(currX,currY));
        // the border is 1 tile further than the edge of the screen
        // this prevents alliens from dieing before they are off screen
        currX = tileXLocation(16);
        path2.add(new PointF(currX,currY));
    }

    private float tileXLocation(int mapTile){
        return ((screenRes.x/15) * mapTile );
    }
    private float tileYLocation(int mapTile){
        return (((screenRes.y - 56)/8) * mapTile );
    }
}




















/*
class TowerSpawner implements InputComponent, InputObserver {
    // set state to tower placement on HUD click

    private Transform mTransform;

    TowerSpawner(GameEngine ger) {
        ger.addObserver(this);
        mPLS = ger;
    }

    @Override
    public void setTransform(Transform transform) {
        mTransform = transform;
    }

    // Required method of InputObserver interface called from the onTouchEvent method
    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons) {
        // In each MotionEvent object, every active pointer is present. Therefore looping through them all for an event on only one of them is obviously wrong.
        // The getActionIndex returns the index in the array of the pointer that performed/trigged the action/method call. So using getX(i) and getY(i) only gets a true result
        // on the button that was actually pressed/removed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_UP:
                if (buttons.get(HUD.UP).contains(x,y)
                        || buttons.get(HUD.DOWN).contains(x,y)) {

                    // Player has released either up or down
                    mTransform.stopVertical();
                }
                break;

            case MotionEvent.ACTION_DOWN:
                if (buttons.get(HUD.UP).contains(x,y)) {
                    // Player has pressed up
                    mTransform.setRotation(90);
                    mTransform.moveForward();
                } else if (buttons.get(HUD.DOWN).contains(x,y)) {
                    // Player has pressed down
                    mTransform.headDown();
                } else if (buttons.get(HUD.FLIP).contains(x,y)) {
                    // Player has released the flip button
                    mTransform.flip();
                } else if (buttons.get(HUD.SHOOT).contains(x,y)) {
                }
                break;
        }
    }
}

 */