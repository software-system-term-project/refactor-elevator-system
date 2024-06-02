
public class ElevatorDoorSamsung extends ElevatorDoor {

	public ElevatorDoorSamsung() {
		super();
	}

	public void open() 
	{
		if ( elevatorDoorStatus == DoorStatus.OPEN ) return ;
		System.out.println("OPEN Samsung Elevator Door") ;
		elevatorDoorStatus = DoorStatus.OPEN;
	}

	public void close() 
	{
		if ( elevatorDoorStatus == DoorStatus.CLOSED ) return ;
		System.out.println("CLOSE Samsung Elevator Door") ;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}