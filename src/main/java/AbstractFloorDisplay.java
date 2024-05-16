
abstract class AbstractFloorDisplay {
	protected ElevatorController elevatorController;
	private IFloorDisplayImplementor floorDisplayImplementor;
	
	protected AbstractFloorDisplay(ElevatorController elevatorController,
								IFloorDisplayImplementor floorDisplayModule) {
		this.elevatorController = elevatorController;
		this.floorDisplayImplementor = floorDisplayModule;
	}
	public void update() {
		int currentFloor = elevatorController.getCurFlr() ;
		int currentDirection = elevatorController.getCurDir();
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
	protected abstract void showPosition(int flr);
	protected abstract void showDirection(int dir);
}
