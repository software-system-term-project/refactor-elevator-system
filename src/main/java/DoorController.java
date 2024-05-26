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
    public DoorStatus getDoorStatus(Floor floor) {
        DoorStatus elevatorDoorState = elevatorDoor.getDoorStatus();
        DoorStatus floorDS = floorDoors.get(floor.getValue()).getDoorStatus();

        DoorStatus doorStatus = DoorStatus.OPEN;
        if ( elevatorDoorState == DoorStatus.CLOSED && floorDS == DoorStatus.CLOSED )
            doorStatus = DoorStatus.CLOSED;

        return doorStatus;
    }
}
