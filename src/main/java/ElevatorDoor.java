
public class ElevatorDoor {
	private DeviceVendor vendor;
	private ElevatorDoorController elevatorDoorController;

	public ElevatorDoor(DeviceVendor vendor) {
		this.vendor = vendor;
		elevatorDoorController = new ElevatorDoorController(vendor);
	}
	public DoorStatus getDoorStatus() {
		return elevatorDoorController.getDoorStatus();
	}
	public void open() {
		elevatorDoorController.open();
	}
	public void close() {
		elevatorDoorController.close();
	}
}