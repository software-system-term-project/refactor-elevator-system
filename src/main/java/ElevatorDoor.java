public class ElevatorDoor 
{
	private DeviceVendor vendor;
	private DoorStatus elevatorDoorStatus;

	public ElevatorDoor(DeviceVendor vendor) 
	{
		this.vendor = vendor;
		elevatorDoorStatus = DoorStatus.CLOSED;
		}
	public DoorStatus getDoorStatus() {
		DoorStatus status = null;
		status = elevatorDoorStatus == DoorStatus.CLOSED ? DoorStatus.CLOSED : DoorStatus.OPEN;
		return status;
	}

	public void open() 
	{
		elevatorDoorStatus = DoorStatus.OPEN;
	}
	public void close() 
	{
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}