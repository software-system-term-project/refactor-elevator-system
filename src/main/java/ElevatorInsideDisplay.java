
public class ElevatorInsideDisplay implements DisplayObserver {
	private ElevatorController elevatorController ;
	public ElevatorInsideDisplay(ElevatorController elevatorController) {
		this.elevatorController = elevatorController ;
	}

	@Override
	public void update() {
		Floor currentFloor = elevatorController.getCurrentFloor() ;
		System.out.println("Elevator Inside Display: " + currentFloor) ;
	}
}
