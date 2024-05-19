
public class ElevatorDoor {
	private DeviceVendor vendor;

	private DoorStatus elevatorDoorStatusForLG ;
	private DoorStatus elevatorDoorStatusForSamsung ;
	private DoorStatus elevatorDoorOpenedForHyundai ;

	public ElevatorDoor(DeviceVendor vendor) {
		this.vendor = vendor;
		
		switch ( vendor ) {
		case LG:
			elevatorDoorStatusForLG = DoorStatus.CLOSED;
			break;
		case HYUNDAI:
			elevatorDoorOpenedForHyundai = DoorStatus.CLOSED;
			break;
		case SAMSUNG:
			elevatorDoorStatusForSamsung = DoorStatus.CLOSED;
			break;
		}
	}
	public DoorStatus getDoorStatus() {
		DoorStatus status = null;
		switch ( vendor ) {
		case LG:
			status = elevatorDoorStatusForLG.equals(DoorStatus.CLOSED) ? DoorStatus.CLOSED : DoorStatus.OPEN;
			break;
		case HYUNDAI:
			status = elevatorDoorOpenedForHyundai == DoorStatus.CLOSED ? DoorStatus.CLOSED : DoorStatus.OPEN;
			break;
		case SAMSUNG:
			status = elevatorDoorStatusForSamsung == DoorStatus.CLOSED ? DoorStatus.CLOSED : DoorStatus.OPEN;
			break;
		}
		return status;
	}
	public void open() {
		switch ( vendor ) {
		case LG:
			if ( elevatorDoorStatusForLG.equals(DoorStatus.OPEN) ) return ;
			System.out.println("open LG Elevator Door") ;
			elevatorDoorStatusForLG = DoorStatus.CLOSED;
			break;
		case HYUNDAI:
			if ( elevatorDoorOpenedForHyundai == DoorStatus.CLOSED ) return ;
			System.out.println("open Hyundai Elevator Door") ;
			elevatorDoorOpenedForHyundai = DoorStatus.CLOSED;
			break;
		case SAMSUNG:
			if ( elevatorDoorStatusForSamsung == DoorStatus.CLOSED ) return ;
			System.out.println("open Samsung Elevator Door") ;
			elevatorDoorStatusForSamsung = DoorStatus.CLOSED;
			break;
		}	
	}
	public void close() {
		switch ( vendor ) {
		case LG:
			if ( elevatorDoorStatusForLG.equals(DoorStatus.CLOSED) ) return ;
			System.out.println("close LG Elevator Door") ;		
			elevatorDoorStatusForLG = DoorStatus.CLOSED;
			break;
		case HYUNDAI:
			if ( elevatorDoorOpenedForHyundai == DoorStatus.CLOSED  ) return ;
			System.out.println("close Hyundai Elevator Door") ;		
			elevatorDoorOpenedForHyundai = DoorStatus.CLOSED;
			break;
		case SAMSUNG:
			if ( elevatorDoorStatusForSamsung == DoorStatus.CLOSED  ) return ;
			System.out.println("close Samsung Elevator Door") ;		
			elevatorDoorStatusForSamsung = DoorStatus.CLOSED;
			break;
		}
	}
}