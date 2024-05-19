
public class ElevatorManager {
	private ElevatorController elevatorController ;
	private MotorStatus motorStatus ;
	private DeviceVendor motorVendor;
	
	public ElevatorManager(DeviceVendor motorVendor) {
		this.motorVendor = motorVendor;
		motorStatus = MotorStatus.STOPPED ;
	}
	public void setElevatorController(ElevatorController elevatorController) {
		this.elevatorController = elevatorController ;
	}
	public MotorStatus getMotorStatus() {
		return motorStatus;
	}
	private void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}

	public void move(Floor currentFloor, Direction direction) {
		if (  getMotorStatus() == MotorStatus.MOVING ) return ;
		
		assert elevatorController != null;
		DoorStatus doorStatus = elevatorController.getDrSts(currentFloor) ;
		if ( doorStatus == DoorStatus.OPEN )
			return;
		
		switch ( motorVendor ) {
		case HYUNDAI:
			System.out.println("move Hyundai Motor, Direction: " + direction) ;
			break;
		case LG:
			System.out.println("move LG Motor, Direction: " + direction) ;
			break;
		case SAMSUNG:
			System.out.println("move Samsung Motor, Direction: " + direction) ;
			break;
		default:
			break;
		}
		setMotorStatus(MotorStatus.MOVING) ;
	}
	public void stop() {
		switch ( motorVendor ) {
		case HYUNDAI:
			System.out.println("Hyundai Elevator Motor Stop") ;
			break;
		case LG:
			System.out.println("LG Elevator Motor Stop") ;
			break;
		case SAMSUNG:
			System.out.println("Samsung Elevator Motor Stop") ;
			break;
		default:
			break;
		}
		setMotorStatus(MotorStatus.STOPPED);
	}
}
