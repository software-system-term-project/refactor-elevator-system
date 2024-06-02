
public class ElevatorDoorHyundai extends ElevatorDoor {

	public ElevatorDoorHyundai() {
		super();
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