
public class ElevatorDoor {
	
protected DoorStatus elevatorDoorStatus;

	public ElevatorDoor() 
	{
		elevatorDoorStatus = DoorStatus.CLOSED;
	}

	public DoorStatus getDoorStatus() {
		DoorStatus status = null;
		status = elevatorDoorStatus.equals(DoorStatus.CLOSED) ? DoorStatus.CLOSED : DoorStatus.OPEN;
		return status;
	}

	public void open() 
	{
	}

	public void close() 
	{
	}
}