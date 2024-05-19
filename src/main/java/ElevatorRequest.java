public class ElevatorRequest {
	private final Floor flr;
	private SimpleElevatorManager em;

	public ElevatorRequest(Floor flr, SimpleElevatorManager em) {
		this.flr = flr;
		this.em = em;
	}
	public void up() { em.requestElevator(flr, 1); }
	public void down() { em.requestElevator(flr, -1); }
}
