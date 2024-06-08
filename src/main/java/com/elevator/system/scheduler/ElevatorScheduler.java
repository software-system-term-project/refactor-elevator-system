package com.elevator.system.scheduler;

import com.elevator.system.controller.ElevatorControllersList;
import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;
import com.elevator.system.controller.ElevatorController;

import java.util.List;

public interface ElevatorScheduler {
    int selectElevator(ElevatorControllersList controllers, Floor destination, Direction direction);
}
