package com.gamecodeschool.earthinvasion;

import android.graphics.PointF;

public class BaseSpawnComponent implements SpawnComponent{

    PointF screenRes;

    @Override
    public void spawn(Transform playerLTransform, Transform t) {
        screenRes = t.getmScreenSize();

        t.setLocation(tileXLocation(16),0);
        t.updateCollider();
    }
    private float tileXLocation(int mapTile){
        return ((screenRes.x/15) * mapTile );
    }
    private float tileYLocation(int mapTile){
        return (((screenRes.y - 56)/8) * mapTile );
    }
}
