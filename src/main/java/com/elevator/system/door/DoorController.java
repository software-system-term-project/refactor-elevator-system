package com.elevator.system.door;

import com.elevator.system.util.Floor;
import com.elevator.system.controller.IDoorTimeout;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.floor.FloorDoor;

import java.util.List;

public class DoorController {
    private ElevatorDoor elevatorDoor;
    private List<FloorDoor> floorDoors;
    private DoorTimer doorTimer;
    public DoorController(ElevatorDoor elevatorDoor, List<FloorDoor>
            floorDoors, DoorTimer doorTimer) {
        this.elevatorDoor = elevatorDoor;
        this.floorDoors = floorDoors;
        this.doorTimer = doorTimer;
    }
    public void setDoorTimeout(IDoorTimeout doorTimeout) {
        doorTimer.setDoorTimeout(doorTimeout);
    }
    public void openDoor(Floor floor) {
        elevatorDoor.open() ;
        floorDoors.get(floor.getValue() - 1).open() ;
        doorTimer.start() ;
    }
    public void closeDoor(Floor floor) {
        elevatorDoor.close() ;
        floorDoors.get(floor.getValue() - 1).close() ;
        doorTimer.stop() ;
    }

    public boolean isOpenedAt(Floor floor) {
        if ( !elevatorDoor.isOpened() && !floorDoors.get(floor.getValue()).isOpened()) return false;
        return true;
    }
}