public class FloorDoorControllerHyundai extends FloorDoorController {

	private boolean doorOpenedHyundai;

	public FloorDoorControllerHyundai(DeviceVendor vendor, Floor floor)
	{
		super(vendor, floor);
		doorOpenedHyundai = false;
	}

	public void open() {
		super.open();
		doorOpenedHyundai = true;
	}
	public void close() {
		super.close();
		doorOpenedHyundai = false;
	}

	public DoorStatus getDoorStatus() {
		DoorStatus status = null;	
		status = (doorOpenedHyundai == true) ? DoorStatus.OPEN : DoorStatus.CLOSED;
		return status;
	}
}