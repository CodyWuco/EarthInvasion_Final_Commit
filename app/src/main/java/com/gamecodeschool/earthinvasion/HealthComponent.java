package com.gamecodeschool.earthinvasion;


interface HealthComponent {

    void initialize(float maxHealth);

    void update(GameObject gameObject);

    void doDamage(float rawDamage);
}
