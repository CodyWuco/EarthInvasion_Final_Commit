package com.gamecodeschool.earthinvasion;

class HitScanAttackComponent extends TowerAttackComponent{

    HitScanAttackComponent(GameEngine gameEngine, GameObject selfRef) {
        super(gameEngine, selfRef);
    }

    // override this to change shot type
    protected void shootShotType(){ targeting.getTarget().doDamage(damage); }
}
