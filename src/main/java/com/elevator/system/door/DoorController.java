package com.elevator.system.door;

import com.elevator.system.util.Floor;
import com.elevator.system.controller.IDoorTimeout;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.floor.FloorDoor;

import java.util.List;

public class DoorController {
    private ElevatorDoor elevatorDoor;
    private FloorDoorList floorDoors;
    private DoorTimer doorTimer;
    public DoorController(ElevatorDoor elevatorDoor, FloorDoorList
            floorDoors, DoorTimer doorTimer) {
        this.elevatorDoor = elevatorDoor;
        this.floorDoors = floorDoors;
        this.doorTimer = doorTimer;
    }
    public void setDoorTimeout(IDoorTimeout doorTimeout) {
        doorTimer.setDoorTimeout(doorTimeout);
    }
    public void openDoor(Floor floor) {
        elevatorDoor.open();
        floorDoors.open(floor);
        doorTimer.start();
    }
    public void closeDoor(Floor floor) {
        elevatorDoor.close() ;
        floorDoors.close(floor);
        doorTimer.stop() ;
    }

    public boolean isOpenedAt(Floor floor) {
        return elevatorDoor.isOpened() && floorDoors.isOpened(floor);
    }
}
