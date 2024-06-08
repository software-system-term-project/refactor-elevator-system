
public abstract class ElevatorDoor {
	
protected DoorStatus elevatorDoorStatus = DoorStatus.CLOSED;

	public boolean isOpened() {
		return elevatorDoorStatus == DoorStatus.OPEN;
	}

	abstract public void open();

	abstract public void close();
}