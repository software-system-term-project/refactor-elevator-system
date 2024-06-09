package com.elevator.system.button;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;
import com.elevator.system.SimpleElevatorManager;

public class MoveDownCommand implements Command {
    private SimpleElevatorManager elevatorManager;

    private Floor floor;

    public MoveDownCommand(SimpleElevatorManager elevatorManager, Floor floor) {
        this.elevatorManager = elevatorManager;
        this.floor = floor;
    }

    @Override
    public void execute() {
        elevatorManager.requestElevator(floor, Direction.DOWN);
    }
}
