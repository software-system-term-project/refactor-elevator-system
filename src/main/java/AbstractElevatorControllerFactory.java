import java.util.List;

public abstract class AbstractElevatorControllerFactory {
    abstract ElevatorMotor createElevatorMotor();
    abstract ElevatorDoor createElevatorDoor();
    abstract List<FloorDoor> createFloorDoors();

    abstract DoorTimer createDoorTimer();

    abstract ElevatorControllerKind setElevatorControllerKind();

    public ElevatorController createElevatorController() {
        return new ElevatorController(setElevatorControllerKind(),
                createElevatorMotor(), new DoorController(
                        createElevatorDoor(),
                        createFloorDoors(),
                        createDoorTimer()
                        )
        );
    }
}
