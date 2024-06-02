
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
		elevatorControllers.add(createFirstElevator(floorCount));

		// Devices for Elevator 2
		elevatorControllers.add(createSecondElevator(floorCount));

		return elevatorControllers;
	}

	private static ElevatorController createSecondElevator(int floorCount) {
		ElevatorController elevatorController = HyundaiElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		/* 아래 코드를 참고하여 HyundaiElevatorControllerFactory 구현
		ElevatorMotor elevatorMotor2 = new ElevatorMotor(DeviceVendor.HYUNDAI);
		ElevatorDoor elevatorDoor2 = new ElevatorDoor(DeviceVendor.HYUNDAI);
		List<FloorDoor> floorDoors2 = createFloorDoors(floorCount, DeviceVendor.HYUNDAI);

		// demand only stop
		DoorController doorController2 =
				new DoorController(elevatorDoor2, floorDoors2, null) ;
		ElevatorController elevatorController2 = new ElevatorController(ElevatorControllerKind.DemandOnly,
					elevatorMotor2, doorController2);
		elevatorMotor2.setElevatorController(elevatorController2);
		 */

		IFloorDisplayImplementor imp = new SamsungFloorDisplayImplementor();
		elevatorController.attach(new AdvancedFloorDisplay(elevatorController, imp));
		return elevatorController;
	}

	private static ElevatorController createFirstElevator(int floorCount) {
		ElevatorController elevatorController = SamsungElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		/* 아래 코드를 참고하여 SamsungElevatorControllerFactory 구현
		ElevatorMotor elevatorMotor1 = new ElevatorMotor(DeviceVendor.SAMSUNG);
		DoorTimer doorTimer = new DoorTimer();

		ElevatorDoor elevatorDoor = new ElevatorDoor(DeviceVendor.SAMSUNG);
		List<FloorDoor> floorDoors = createFloorDoors(floorCount, DeviceVendor.SAMSUNG);

		// every floor stop
		DoorController doorController =
				new DoorController(elevatorDoor, floorDoors, doorTimer) ;
		ElevatorController elevatorController = new ElevatorController(ElevatorControllerKind.EveryFloorStop,
				elevatorMotor1, doorController);
		doorTimer.setDoorTimeout(elevatorController);
		elevatorMotor1.setElevatorController(elevatorController);
		 */

		elevatorController.attach(new ControlRoomDisplay(elevatorController));
		elevatorController.attach(new ElevatorInsideDisplay(elevatorController));

		return elevatorController;
	}

	private static List<FloorDoor> createFloorDoors(int floorCount, DeviceVendor vendor) {
		List<FloorDoor> floorDoors = new ArrayList<>();
		
		for ( int i = 0 ; i < floorCount; i ++ )
			floorDoors.add(new FloorDoor(new Floor(i)));
		return floorDoors;
	}
}
