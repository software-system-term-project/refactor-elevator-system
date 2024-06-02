
public class ElevatorMotorHyundai extends ElevatorMotor
{
	
	public ElevatorMotorHyundai() {
		super();
	}

	@Override
	public void move(Floor currentFloor, Direction direction) {
		super.move(currentFloor, direction);
		System.out.println("move Hyundai Motor, Direction: " + direction) ;
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("Hyundai Elevator Motor Stop") ;
	}
}
