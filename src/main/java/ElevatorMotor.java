
public abstract class ElevatorMotor {
	private ElevatorController elevatorController ;
	private MotorStatus motorStatus = MotorStatus.STOPPED;

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
		if ( elevatorController.isOpenedAt(currentFloor) ) return;
		setMotorStatus(MotorStatus.MOVING) ;
	}

	public void stop() {
		setMotorStatus(MotorStatus.STOPPED);
	}
}
