public class FloorDoorSamsung extends FloorDoor {

	private char doorOpenedSamsung;

	public FloorDoorSamsung(DeviceVendor vendor, Floor floor)
	{
		super(vendor, floor);
		doorOpenedSamsung = 'X';
	}

	public void open() {
		super.open();
		doorOpenedSamsung = 'O';
	}
	public void close() {
		super.close();
		doorOpenedSamsung = 'X';
	}

	public DoorStatus getDoorStatus() {
		DoorStatus status = null;	
		status = (doorOpenedSamsung == 'O') ? DoorStatus.OPEN : DoorStatus.CLOSED;
		return status;
	}
}