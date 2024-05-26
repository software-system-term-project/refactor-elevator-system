public abstract class FloorDoorController {
	protected DeviceVendor vendor;
	protected Floor floor ;
	
	protected DoorStatus doorStatus;

	public FloorDoorController(DeviceVendor vendor, Floor floor) {
		this.vendor = vendor;
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
}