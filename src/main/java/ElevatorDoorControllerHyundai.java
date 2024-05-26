
public class ElevatorDoorControllerHyundai extends ElevatorDoorController {

	public ElevatorDoorControllerHyundai(DeviceVendor vendor) {
		super(vendor);
	}

	public void open() 
	{
		if ( elevatorDoorStatus == DoorStatus.OPEN ) return ;
		System.out.println("OPEN Hyundai Elevator Door") ;
		elevatorDoorStatus = DoorStatus.OPEN;
	}

	public void close() 
	{
		if ( elevatorDoorStatus == DoorStatus.CLOSED ) return ;
		System.out.println("CLOSE Hyundai Elevator Door") ;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}