
public class FloorDoor {
	private Floor floor ;

	private FloorDoorController floorDoorController;

	public FloorDoor(DeviceVendor vendor, Floor floor) {
		
		this.floor = floor;

		switch (vendor) {
			case SAMSUNG:
				floorDoorController = new FloorDoorControllerSamsung(vendor, floor);
				break;
			case LG:
				floorDoorController = new FloorDoorControllerLG(vendor, floor);
				break;
			case HYUNDAI:
				floorDoorController = new FloorDoorControllerHyundai(vendor, floor);
				break;
		}

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