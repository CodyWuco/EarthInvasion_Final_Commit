package com.gamecodeschool.earthinvasion;

class LargeLaserOrbAttackComponent extends TowerAttackComponent{

    LargeLaserOrbAttackComponent(GameEngine gameEngine, GameObject selfRef) {
        super(gameEngine, selfRef);
        attacksPerSecond = .2f;
    }

    // override this to change shot type
    protected void shootShotType(){
        gameEngine.shootLargeLaserOrb(getShooter(), targeting.getTarget());
    }
}