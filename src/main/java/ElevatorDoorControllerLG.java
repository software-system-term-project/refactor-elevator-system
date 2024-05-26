
public class ElevatorDoorControllerLG extends ElevatorDoorController {


	public ElevatorDoorControllerLG(DeviceVendor vendor) {
		super(vendor);
	}

	public void open() 
	{
		if ( elevatorDoorStatus == DoorStatus.OPEN ) return ;
		System.out.println("OPEN LG Elevator Door") ;
		elevatorDoorStatus = DoorStatus.OPEN;
	}

	public void close() 
	{
		if ( elevatorDoorStatus == DoorStatus.CLOSED ) return ;
		System.out.println("CLOSE LG Elevator Door") ;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}