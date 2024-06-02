
public class ElevatorMotorLG extends ElevatorMotor
{
	
	public ElevatorMotorLG() {
		super();
	}

	@Override
	public void move(Floor currentFloor, Direction direction) {
		super.move(currentFloor, direction);
		System.out.println("move LG Motor, Direction: " + direction) ;
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("LG Elevator Motor Stop") ;
	}
}
