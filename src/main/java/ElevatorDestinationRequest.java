public class ElevatorDestinationRequest {
	private final ElevatorController ctrl;
	private Floor flr;
	
	public ElevatorDestinationRequest(Floor floor, 	ElevatorController ctrl) {
		this.flr = floor;
		this.ctrl = ctrl;
	}
	public void pressed() {
		ctrl.goTo(flr);
	}
}
