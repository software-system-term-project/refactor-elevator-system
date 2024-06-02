import java.util.ArrayList;
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
        return new ElevatorMotorSamsung();
    }

    @Override
    ElevatorDoor createElevatorDoor() {
        return new ElevatorDoorSamsung();
    }

    @Override
    List<FloorDoor> createFloorDoors(int floorCount) {
        List<FloorDoor> floorDoors = new ArrayList<FloorDoor>();
        
        for(int i=0;i<floorCount;i++)
        {
            floorDoors.add(new FloorDoor(DeviceVendor.SAMSUNG, new Floor(i)));
        }

        return floorDoors;
    }

    @Override
    DoorTimer createDoorTimer() {
        return new DoorTimer();
    }

    @Override
    ElevatorControllerKind setElevatorControllerKind() {
        return ElevatorControllerKind.EveryFloorStop;
    }
}
