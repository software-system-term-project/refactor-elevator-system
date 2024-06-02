import java.util.List;

class SimpleElevatorManager {
	private List<ElevatorController> controllers;
	private ElevatorScheduler scheduler;

	public SimpleElevatorManager(List<ElevatorController> controllers, ElevatorScheduler scheduler) {
		this.controllers = controllers;
		this.scheduler = scheduler;
	}	

	public void requestElevator(Floor destination, Direction direction) {
		int selectedElevator = scheduler.selectElevator(controllers, destination, direction);
		controllers.get(selectedElevator).goTo(destination) ;
	}
	public void emergencyStop(boolean goTo1stFloor) {
		for ( ElevatorController controller: controllers)
			if ( goTo1stFloor ) {
				controller.getFloorstobeVisited().clear();
				controller.goTo(new Floor(1));
			}
			else
				controller.stop();
	}
	public boolean isToBeVisistedFloor(Floor floor) {
		for ( ElevatorController controller: controllers) {
			if ( controller.getFloorstobeVisited().contains(floor) ) return true;
		}
		return false;
	}
	public void print() {
		for ( ElevatorController controller: controllers) {
			print(controller);
		}
	}
	private void print(ElevatorController controller) {
		System.out.println(controller.getCurrentFloor());
		System.out.println(controller.getCurrentDirection());
		System.out.println(controller.getFloorstobeVisited());
	}
}