package com.elevator.system.scheduler;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;
import com.elevator.system.controller.ElevatorController;

import java.util.List;

public interface ElevatorScheduler {
    int selectElevator(List<ElevatorController> controllers, Floor destination, Direction direction);
}
