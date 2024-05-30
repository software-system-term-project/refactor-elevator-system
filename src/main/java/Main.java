
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		int floorCount = 5;
		
		List<ElevatorController> elevatorControllers = createElevatorControllers(floorCount);
		
		SimpleElevatorManager simpleElevatorManager = new SimpleElevatorManager(elevatorControllers);
		
		List<Button> requestButtons = new ArrayList<>();
		for ( int i = 0; i < floorCount; i ++ ) {
			Floor floor = new Floor(i + 1);

			requestButtons.add(new Button(new MoveDownCommand(simpleElevatorManager, floor)));
			requestButtons.add(new Button(new MoveUpCommand(simpleElevatorManager, floor)));
		}
		requestButtons.get(0).pressed();
	}

	private static List<ElevatorController> createElevatorControllers(int floorCount) {
		List<ElevatorController> elevatorControllers = new ArrayList<>();
		
		// Devices for Elevator 1
		ElevatorMotor elevatorMotor1 = new ElevatorMotor(DeviceVendor.SAMSUNG);
		DoorTimer doorTimer1 = new DoorTimer();
			
		ElevatorDoor elevatorDoor1 = new ElevatorDoor(DeviceVendor.SAMSUNG);
		List<FloorDoor> floorDoors1 = createFloorDoors(floorCount, DeviceVendor.SAMSUNG);
		
		// every floor stop
		DoorController doorController1 =
				new DoorController(elevatorDoor1, floorDoors1, doorTimer1) ;
		ElevatorController elevatorController1 = new ElevatorController(ElevatorControllerKind.EveryFloorStop,
				elevatorMotor1, doorController1);
		doorTimer1.setDoorTimeout(elevatorController1);
		elevatorMotor1.setElevatorController(elevatorController1);

		ControlRoomDisplay controlRoomDisplay = new ControlRoomDisplay(elevatorController1);
		elevatorController1.attach(controlRoomDisplay);
		ElevatorInsideDisplay elevatorInsideDisplay = new ElevatorInsideDisplay(elevatorController1);
		elevatorController1.attach(elevatorInsideDisplay);

		elevatorControllers.add(elevatorController1);
		
		// Devices for Elevator 2
		ElevatorMotor elevatorMotor2 = new ElevatorMotor(DeviceVendor.HYUNDAI);
		ElevatorDoor elevatorDoor2 = new ElevatorDoor(DeviceVendor.HYUNDAI);
		List<FloorDoor> floorDoors2 = createFloorDoors(floorCount, DeviceVendor.HYUNDAI);
		
		// demand only stop
		DoorController doorController2 =
				new DoorController(elevatorDoor2, floorDoors2, null) ;
		ElevatorController elevatorController2 = new ElevatorController(ElevatorControllerKind.DemandOnly,
					elevatorMotor2, doorController2);
		elevatorMotor2.setElevatorController(elevatorController2);

		IFloorDisplayImplementor imp = new SamsungFloorDisplayImplementor();
		AdvancedFloorDisplay advancedFloorDisplay = new AdvancedFloorDisplay(elevatorController2, imp);
		elevatorController2.attach(advancedFloorDisplay);
		
		elevatorControllers.add(elevatorController2);
		return elevatorControllers;
	}

	private static List<FloorDoor> createFloorDoors(int floorCount, DeviceVendor vendor) {
		List<FloorDoor> floorDoors = new ArrayList<>();
		
		for ( int i = 0 ; i < floorCount; i ++ )
			floorDoors.add(new FloorDoor(vendor, new Floor(i)));
		return floorDoors;
	}
}
