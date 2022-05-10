package com.gamecodeschool.earthinvasion;

interface SpawnComponent {
    // target transform was intended to keeps track of the back ground, so
    // objects could move relative to the background as the screen scrolled
    void spawn(Transform target,
               Transform t);
}