
public class ControlRoomDisplay implements DisplayObserver {
	private ElevatorController elevatorController;

	public ControlRoomDisplay(ElevatorController elevatorController) {
		this.elevatorController = elevatorController;
	}

	@Override
	public void update() {
		Floor currentFloor = elevatorController.getCurrentFloor() ;
		System.out.println("Control Room: " + currentFloor) ;
	}
}
