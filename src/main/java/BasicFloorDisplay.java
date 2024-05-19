public class BasicFloorDisplay extends AbstractFloorDisplay {
	public BasicFloorDisplay(ElevatorController elevatorController,
							 IFloorDisplayImplementor floorDisplayImplementor) {
		super(elevatorController, floorDisplayImplementor);
	}
	
	@Override
	public void showPosition(int floor) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showCurrentPosition(floor);
	}
	@Override
	public void showDirection(Direction direction) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showDirection(direction);
	}
}
