import java.util.Calendar;
import java.util.List;
class SimpleElevatorManager {
	private List<ElevatorController> controllers;

	public SimpleElevatorManager(List<ElevatorController> controllers) {
		this.controllers = controllers;
	}	
	public void requestElevator(Floor destination, int dir) {
		int sel;
		// 0..23
		int hr = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		if ( hr < 12 ) { // ����; Response Time Scheduler
			sel = controllers.size() -1;
			System.out.println("ResponseTimeScheduler selects " + sel);
		}
		else { // ����; Throughput Scheduler
			sel = 0;
			System.out.println("ThroughputScheduler selects " + sel);
		}
		controllers.get(sel).goTo(destination) ;
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
		System.out.println(controller.getCurFlr());
		System.out.println(controller.getCurDir());
		System.out.println(controller.getFloorstobeVisited());
	}
}