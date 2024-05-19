public class BasicFloorDisplay extends AbstractFloorDisplay {
	public BasicFloorDisplay(ElevatorController elevatorController,
							 IFloorDisplayImplementor floorDisplayImplementor) {
		super(elevatorController, floorDisplayImplementor);
	}
	
	@Override
	public void showPosition(Floor floor) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showCurrentPosition(floor);
	}
	@Override
	public void showDirection(int direction) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showDirection(direction);
	}
}
