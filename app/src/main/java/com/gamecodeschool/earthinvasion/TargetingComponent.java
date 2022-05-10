package com.gamecodeschool.earthinvasion;

import java.util.ArrayList;

interface TargetingComponent {

    void setTargetList(ArrayList<GameObject> targets);

    ArrayList<GameObject> getTargetList();

    void setTarget(GameObject target);

    GameObject getTarget();

    void findTarget();

    boolean hasTarget();
}
