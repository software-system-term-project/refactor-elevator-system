package com.elevator.system.controller;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

public interface ElevatorStatusProvider {
    Floor getCurrentFloor();
    Direction getCurrentDirection();
}
