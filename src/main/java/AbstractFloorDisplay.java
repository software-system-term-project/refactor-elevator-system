
abstract class AbstractFloorDisplay implements DisplayObserver {
	protected ElevatorController elevatorController;
	private IFloorDisplayImplementor floorDisplayImplementor;
	
	protected AbstractFloorDisplay(ElevatorController elevatorController,
								IFloorDisplayImplementor floorDisplayModule) {
		this.elevatorController = elevatorController;
		this.floorDisplayImplementor = floorDisplayModule;
	}

	@Override
	public void update() {
		Floor currentFloor = elevatorController.getCurrentFloor() ;
		Direction currentDirection = elevatorController.getCurrentDirection();
		showPosition(currentFloor);
		showDirection(currentDirection);
	}
	public void displayOn() { floorDisplayImplementor.activateDisplay(); }
	public void displayOff() { floorDisplayImplementor.deactivateDisplay(); }

	public void activateDisplay() {
		floorDisplayImplementor.activateDisplay();
	}

	public void deactivateDisplay() {
		floorDisplayImplementor.deactivateDisplay();
	}

	protected IFloorDisplayImplementor getFloorDisplayImplementor() { return floorDisplayImplementor; }

	protected abstract void showPosition(Floor floor);
	protected abstract void showDirection(Direction dir);
}
