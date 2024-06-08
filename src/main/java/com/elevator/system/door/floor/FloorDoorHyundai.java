package com.elevator.system.door.floor;

import com.elevator.system.util.Floor;

public class FloorDoorHyundai extends FloorDoor {

    private boolean doorOpenedHyundai;

    public FloorDoorHyundai(Floor floor)
    {
        super(floor);
        doorOpenedHyundai = false;
    }

    public void open() {
        super.open();
        doorOpenedHyundai = true;
    }
    public void close() {
        super.close();
        doorOpenedHyundai = false;
    }

    @Override
    public boolean isOpened() {
        return doorOpenedHyundai;
    }
}