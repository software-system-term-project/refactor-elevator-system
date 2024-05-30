import java.util.List;

public class HyundaiElevatorControllerFactory extends AbstractElevatorControllerFactory {
    private static HyundaiElevatorControllerFactory instance;

    private HyundaiElevatorControllerFactory() {}

    public static HyundaiElevatorControllerFactory getInstance() {
        if (instance == null) instance = new HyundaiElevatorControllerFactory();
        return instance;
    }

    @Override
    ElevatorMotor createElevatorMotor() {
        return null;
    }

    @Override
    ElevatorDoor createElevatorDoor() {
        return null;
    }

    @Override
    List<FloorDoor> createFloorDoors(int floorCount) {
        return null;
    }

    @Override
    DoorTimer createDoorTimer() {
        return null;
    }

    @Override
    ElevatorControllerKind setElevatorControllerKind() {
        return null;
    }
}
