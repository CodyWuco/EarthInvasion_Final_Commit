package com.gamecodeschool.earthinvasion;

import java.util.ArrayList;

class FrontTargetingComponent implements TargetingComponent {

    GameObject target;
    GameEngine gameEngineRef;

    FrontTargetingComponent(GameEngine gameEngineRef){
        this.gameEngineRef = gameEngineRef;
    }

    @Override
    public void findTarget(){
        for(GameObject o : gameEngineRef.mLevel.getEnemyGameObjects()){
            if (o.checkActive()){
                target = o;
                return;
            }
        }
    }

    @Override
    public boolean hasTarget() {
        if (target != null){
            if(target.checkActive()){
                return true;
            }
        }
        return false;
    }


    @Override
    public void setTargetList(ArrayList<GameObject> targets) {

    }

    @Override
    public ArrayList<GameObject> getTargetList() {
        return null;
    }

    @Override
    public void setTarget(GameObject target) {
        this.target = target;
    }

    @Override
    public GameObject getTarget() {
        return target;
    }
}
