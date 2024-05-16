
public class FloorDoor {
	private DeviceVendor vendor;
	private int floor ;
	
	private int doorOpenedForLG;
	private boolean doorOpenedForHyundai;
	private char doorOpenedForSamsung;

	public FloorDoor(DeviceVendor vendor, int floor) {
		this.vendor = vendor;
		this.floor = floor;
		
		switch ( vendor ) {
		case LG:
			doorOpenedForLG = 0;
			break;
		case HYUNDAI:
			doorOpenedForHyundai = false;
			break;
		case SAMSUNG:
			doorOpenedForSamsung = 'X';
			break;
		}
	}
	public void open() {
		switch ( vendor ) {
		case LG:
			System.out.println(floor + "th Floor Door Open") ;
			doorOpenedForLG = 1;
			break;
		case HYUNDAI:
			System.out.println(floor + "th Floor Door Open") ;
			doorOpenedForHyundai = true;
			break;
		case SAMSUNG:
			System.out.println(floor + "th Floor Door Open") ;
			doorOpenedForSamsung = 'O';
			break;
		}
	}
	public void close() {
		switch ( vendor ) {
		case LG:
			System.out.println(floor + "th Floor Door Close") ;	
			doorOpenedForLG = 0;
			break;
		case HYUNDAI:
			System.out.println(floor + "th Floor Door Close") ;	
			doorOpenedForHyundai = false;
			break;
		case SAMSUNG:
			System.out.println(floor + "th Floor Door Close") ;	
			doorOpenedForSamsung = 'X';
			break;
		}
	}
	public DoorStatus getDoorStatus() {
		DoorStatus status = null;
		switch ( vendor ) {
		case LG:
			status = (doorOpenedForLG == 1) ? DoorStatus.OPEN : DoorStatus.CLOSED;
			break;
		case HYUNDAI:
			status = (doorOpenedForHyundai) ? DoorStatus.OPEN : DoorStatus.CLOSED;
			break;
		case SAMSUNG:
			status = (doorOpenedForSamsung == 'O') ? DoorStatus.OPEN : DoorStatus.CLOSED;
			break;
		}
		return status;
	}
	public int getFloor() { return floor; }
}