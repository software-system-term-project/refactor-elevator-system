public class FloorDoorHyundai extends FloorDoor {

	private boolean doorOpenedHyundai;

	public FloorDoorHyundai(Floor floor)
	{
		super(floor);
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