
public class ElevatorMotor {
	private ElevatorController elevatorController ;
	private MotorStatus motorStatus ;

	public ElevatorMotor() {
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
		DoorStatus doorStatus = elevatorController.getDoorStatus(currentFloor) ;
		if ( doorStatus == DoorStatus.OPEN ) return;
		setMotorStatus(MotorStatus.MOVING) ;
	}

	public void stop() {
		setMotorStatus(MotorStatus.STOPPED);
	}
}
