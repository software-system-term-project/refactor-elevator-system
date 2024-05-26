
public abstract class ElevatorDoorController {
	protected DeviceVendor vendor;
	protected DoorStatus elevatorDoorStatus;

	public ElevatorDoorController(DeviceVendor vendor) {
		this.vendor = vendor;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}

	public DoorStatus getDoorStatus() {
		DoorStatus status = null;
		status = elevatorDoorStatus.equals(DoorStatus.CLOSED) ? DoorStatus.CLOSED : DoorStatus.OPEN;
		return status;
	}

	public abstract void open();
	public abstract void close();
}