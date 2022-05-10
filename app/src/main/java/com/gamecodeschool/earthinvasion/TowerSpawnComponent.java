package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

class TowerSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform spawnLocation, Transform t) {

        PointF startPosition =
                spawnLocation.getFiringLocation(t.getSize().x);

        t.setLocation((int)startPosition.x, (int)startPosition.y);
    }
}