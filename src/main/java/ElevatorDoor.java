
public class ElevatorDoor {
	private DeviceVendor vendor;
	private ElevatorDoorController elevatorDoorController;

	public ElevatorDoor(DeviceVendor vendor) {
		this.vendor = vendor;
		switch (vendor) 
		{
			case SAMSUNG:
				elevatorDoorController = new ElevatorDoorControllerSamsung(vendor);
				break;	
			case LG:
				elevatorDoorController = new ElevatorDoorControllerLG(vendor);
				break;
			case HYUNDAI:
				elevatorDoorController = new ElevatorDoorControllerHyundai(vendor);
				break;
		}
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