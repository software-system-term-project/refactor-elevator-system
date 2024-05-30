import java.util.List;

//구현 필요
public class SamsungElevatorControllerFactory extends AbstractElevatorControllerFactory {
    private static SamsungElevatorControllerFactory instance;

    private SamsungElevatorControllerFactory() {}

    public static SamsungElevatorControllerFactory getInstance() {
        if (instance == null) instance = new SamsungElevatorControllerFactory();
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
