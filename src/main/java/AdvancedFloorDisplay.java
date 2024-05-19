
public class AdvancedFloorDisplay extends AbstractFloorDisplay {
	public AdvancedFloorDisplay(ElevatorController elevatorController,
								IFloorDisplayImplementor floorDisplayImplementor) {
		super(elevatorController, floorDisplayImplementor);
	}
	@Override
	public void showPosition(Floor floor) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showCurrentPosition(floor);
		floorDisplayImplementor.expressCurrentPositionByVoice(floor);
	}
	@Override
	public void showDirection(Direction direction) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showDirection(direction);
		floorDisplayImplementor.expressDirectionByVoice(direction);
	}
}
