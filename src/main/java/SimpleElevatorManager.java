import java.util.Calendar;
import java.util.List;
class SimpleElevatorManager {
	private List<ElevatorController> ctrls ;

	public SimpleElevatorManager(List<ElevatorController> ctrls) {
		this.ctrls = ctrls;
	}	
	public void requestElevator(int dest, Direction dir) {
		int sel;
		// 0..23
		int hr = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		if ( hr < 12 ) { // ����; Response Time Scheduler
			sel = ctrls.size() -1;
			System.out.println("ResponseTimeScheduler selects " + sel);
		}
		else { // ����; Throughput Scheduler
			sel = 0;
			System.out.println("ThroughputScheduler selects " + sel);
		}
		ctrls.get(sel).goTo(dest) ;
	}
	public void emergencyStop(boolean goTo1stFloor) {
		for ( ElevatorController ctrl: ctrls )
			if ( goTo1stFloor ) {
				ctrl.getFloorstobeVisited().clear();
				ctrl.goTo(1);
			}
			else
				ctrl.stop();
	}
	public boolean isToBeVisistedFloor(int flr) {
		for ( ElevatorController ctrl: ctrls ) {
			if ( ctrl.getFloorstobeVisited().contains(flr) ) return true;
		}
		return false;
	}
	public void print() {
		for ( ElevatorController ctrl: ctrls ) {
			print(ctrl);
		}
	}
	private void print(ElevatorController ctrl) {
		System.out.println(ctrl.getCurFlr());
		System.out.println(ctrl.getCurDir());
		System.out.println(ctrl.getFloorstobeVisited());
	}
}