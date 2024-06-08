package com.elevator.system.util;

public class Floor {
    private int value;
    private static final int MIN_FLOOR = -100;
    private static final int MAX_FLOOR = 1000;

    public Floor(int value) {
        if (isValidFloor(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid floor value. com.elevator.system.util.Floor must be between -100 and 1000.");
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (isValidFloor(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid floor value. com.elevator.system.util.Floor must be between -100 and 1000.");
        }
    }

    private boolean isValidFloor(int value) {
        return value >= MIN_FLOOR && value <= MAX_FLOOR;
    }

    public boolean isHigherThan(Floor other) {
        return value > other.value;
    }

    public boolean isLowerThan(Floor other) {
        return value < other.value;
    }

    public boolean isEqual(Floor other) {
        return value == other.value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
