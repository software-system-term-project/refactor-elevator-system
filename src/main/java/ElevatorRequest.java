public class ElevatorRequest {
	private final Floor floor;
	private SimpleElevatorManager elavatorManager;

	public ElevatorRequest(Floor floor, SimpleElevatorManager elavatorManager) {
		this.floor = floor;
		this.elavatorManager = elavatorManager;
	}
	public void up() { elavatorManager.requestElevator(floor, Direction.UP); }
	public void down() { elavatorManager.requestElevator(floor, Direction.DOWN); }
}
