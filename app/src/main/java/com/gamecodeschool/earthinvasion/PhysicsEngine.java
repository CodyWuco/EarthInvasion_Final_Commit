package com.gamecodeschool.earthinvasion;

import android.graphics.RectF;

import java.util.ArrayList;

class PhysicsEngine {

    PhysicsEngine(){

    }
    // This signature and much more will change later in the project
    boolean update(long fps, ArrayList<GameObject> playerObjects, ArrayList<GameObject> enemies,
                   GameState gs, SoundEngine se,
                   ParticleSystem ps){

        // Update all the game objects
        for (GameObject object : playerObjects) {
            if (object.checkActive()) {
                object.update(fps, enemies);
            }
        }

        for (GameObject object : enemies) {
            if (object.checkActive()) {
                object.update(fps, playerObjects);
            }
        }

        if(ps.mIsRunning){
            ps.update(fps);
        }

        return detectCollisions(gs, playerObjects, enemies, se, ps);
    }


    // Collision detection will go here
    private boolean detectCollisions(GameState mGameState, ArrayList<GameObject> playerObjects,
                                     ArrayList<GameObject> enemies, SoundEngine se, ParticleSystem ps ){
        boolean playerHit = false;
        for(GameObject go1 : playerObjects) {

            if(go1.checkActive()){
                // The ist object is active so worth checking

                for(GameObject go2 : enemies) {

                    if(go2.checkActive()){

                        // The 2nd object is active so worth checking
                        if(RectF.intersects(go1.getTransform().getCollider(), go2.getTransform().getCollider())){
                            // There has been a collision - but does it matter

                            switch (go1.getTag() + " with " + go2.getTag()){
                                case "Base with Alien Laser":
                                    playerHit = false;
                                    //mGameState.loseLife(se);

                                    break;

                                case "Base with Alien":
                                    mGameState.loseLife(se);
                                    go2.setInactive();
                                    se.playAlienExplode();

                                    break;

                                case "Small Laser Orb with Alien":
                                    go2.doDamage(5);
                                    go1.setInactive();
                                    se.playAlienExplode();
                                    mGameState.increaseScore(5);

                                    break;

                                case "Large Laser Orb with Alien":
                                    go2.doDamage(50);
                                    go1.setInactive();
                                    se.playAlienExplode();
                                    mGameState.increaseScore(50);

                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return playerHit;
    }
}