
public class FloorDoor {
	private DeviceVendor vendor;
	private Floor floor ;

	private FloorDoorController floorDoorController;

	public FloorDoor(DeviceVendor vendor, Floor floor) {
		this.vendor = vendor;
		this.floor = floor;

		floorDoorController = new FloorDoorController(vendor, floor);
	}
	public void open() {
		floorDoorController.open();
	}
	public void close() {
		floorDoorController.close();
	}
	public DoorStatus getDoorStatus() {
		return floorDoorController.getDoorStatus();
	}
	public Floor getFloor() { return floor; }
}