package com.elevator.system.button;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;
import com.elevator.system.SimpleElevatorManager;

public class MoveUpCommand implements Command {
    private SimpleElevatorManager elavatorManager;

    private Floor floor;

    public MoveUpCommand(SimpleElevatorManager elavatorManager, Floor floor) {
        this.elavatorManager = elavatorManager;
        this.floor = floor;
    }

    @Override
    public void execute() {
        elavatorManager.requestElevator(floor, Direction.UP);
    }
}
