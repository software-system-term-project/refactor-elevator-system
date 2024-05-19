
public class ElevatorInsideDisplay {
	private ElevatorController elevatorController ;
	public ElevatorInsideDisplay(ElevatorController elevatorController) {
		this.elevatorController = elevatorController ;
	}
	public void update() {
		Floor currentFloor = elevatorController.getCurFlr() ;
		System.out.println("Elevator Inside Display: " + currentFloor) ;
	}
}
