
public class ControlRoomDisplay {
	private ElevatorController elevatorController;

	public ControlRoomDisplay(ElevatorController elevatorController) {
		this.elevatorController = elevatorController;
	}
	public void update() {
		Floor currentFloor = elevatorController.getCurFlr() ;
		System.out.println("Control Room: " + currentFloor) ;
	}
}
