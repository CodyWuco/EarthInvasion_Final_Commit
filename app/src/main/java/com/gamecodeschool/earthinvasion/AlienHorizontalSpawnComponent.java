package com.gamecodeschool.earthinvasion;


class AlienHorizontalSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform playerLTransform, Transform t) {

        // Spawn the ship
        t.setLocation(0, 512);

    }
}