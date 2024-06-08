package com.elevator.system.door.floor;

import com.elevator.system.util.Floor;

public class FloorDoorLG extends FloorDoor {

    private int doorOpenedLG;

    public FloorDoorLG(Floor floor)
    {
        super(floor);
        doorOpenedLG = 0;
    }

    public void open() {
        super.open();
        doorOpenedLG = 1;
    }
    public void close() {
        super.close();
        doorOpenedLG = 0;
    }

    @Override
    public boolean isOpened() {
        return doorOpenedLG == 1;
    }
}