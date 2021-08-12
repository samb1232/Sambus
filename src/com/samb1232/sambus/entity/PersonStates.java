package com.samb1232.sambus.entity;

public enum PersonStates {
    UP(3),
    DOWN(2),
    LEFT(1),
    RIGHT(0),
    FALLEN(4);

    private final int sideNumber;

    PersonStates(int sideNumber) {
        this.sideNumber = sideNumber;
    }

    public int getSideNumber() {
        return sideNumber;
    }
}
