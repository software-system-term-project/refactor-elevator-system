import java.util.List;

public abstract class AbstractElevatorControllerFactory {
    abstract ElevatorMotor createElevatorMotor();
    abstract ElevatorDoor createElevatorDoor();
    abstract List<FloorDoor> createFloorDoors(int floorCount);

    abstract DoorTimer createDoorTimer();

    abstract ElevatorControllerKind setElevatorControllerKind();

    public ElevatorController createElevatorController(int floorCount) {
        return new ElevatorController(setElevatorControllerKind(),
                createElevatorMotor(), new DoorController(
                        createElevatorDoor(),
                        createFloorDoors(floorCount),
                        createDoorTimer()
                        )
        );
    }
}
