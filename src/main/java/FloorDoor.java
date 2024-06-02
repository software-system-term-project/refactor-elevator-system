
public class FloorDoor {
	private Floor floor ;
	protected DoorStatus doorStatus;
	

	public FloorDoor(Floor floor) {
		this.floor = floor;
		doorStatus = DoorStatus.CLOSED;
	}

	public void open() {
		System.out.println(floor + "th Floor Door Open") ;
		doorStatus = DoorStatus.OPEN;
	}
	public void close() {
		System.out.println(floor + "th Floor Door Close") ;
		doorStatus = DoorStatus.CLOSED;
	}
	public DoorStatus getDoorStatus() {
		DoorStatus status = null;	
		status = (doorStatus == DoorStatus.OPEN) ? DoorStatus.OPEN : DoorStatus.CLOSED;
		return status;
	}

	public Floor getFloor() { return floor; }
}