
public class ElevatorMotorSamsung extends ElevatorMotor
{
	
	public ElevatorMotorSamsung() {
		super();
	}

	@Override
	public void move(Floor currentFloor, Direction direction) {
		super.move(currentFloor, direction);
		System.out.println("move Samsung Motor, Direction: " + direction) ;
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("Samsung Elevator Motor Stop") ;
	}
}
