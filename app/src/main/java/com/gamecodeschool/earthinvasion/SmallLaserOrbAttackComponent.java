package com.gamecodeschool.earthinvasion;

class SmallLaserOrbAttackComponent extends TowerAttackComponent{

    SmallLaserOrbAttackComponent(GameEngine gameEngine, GameObject selfRef) {
        super(gameEngine, selfRef);
    }

    // override this to change shot type
    protected void shootShotType(){
        gameEngine.shootSmallLaserOrb(getShooter(), targeting.getTarget());
    }
}

