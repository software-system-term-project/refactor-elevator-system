import java.util.List;

class SimpleElevatorManager {
	private final List<ElevatorController> controllers;
	private final ElevatorScheduler scheduler;

	public SimpleElevatorManager(List<ElevatorController> controllers, ElevatorScheduler scheduler) {
		if (controllers == null || controllers.isEmpty() || scheduler == null)
			throw new IllegalArgumentException("ElevatorManager 생성에 필요한 부품이 없습니다!");

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
				controller.getFloorsToBeVisited().clear();
				controller.goTo(new Floor(1));
			}
			else
				controller.stop();
	}
	public boolean isToBeVisitedFloor(Floor floor) {
		for ( ElevatorController controller: controllers) {
			if ( controller.getFloorsToBeVisited().contains(floor) ) return true;
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
		System.out.println(controller.getFloorsToBeVisited());
	}
}