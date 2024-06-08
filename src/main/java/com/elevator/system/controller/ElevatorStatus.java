package com.elevator.system.controller;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

public class ElevatorStatus {
    private Floor currentFloor = new Floor(1);

    private Direction currentDirection = Direction.IDLE;

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}
