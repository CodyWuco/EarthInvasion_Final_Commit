package com.gamecodeschool.earthinvasion;

class BasicHealthComponent implements HealthComponent {

    float maxHealth;
    float health;

    @Override
    public void initialize(float maxHealth) {
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    @Override
    public void update(GameObject gameObject) {
        if (health <= 0){
            gameObject.setInactive();
            doHeal(maxHealth);
        }
    }

    @Override
    public void doDamage(float rawDamage) {
        health -= rawDamage;
    }

    void doHeal(float heal){
        if ((heal + health) < maxHealth) {
            health = maxHealth;
        }else {
            health = maxHealth;
        }
    }
}
