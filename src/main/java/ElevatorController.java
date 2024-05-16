import java.util.ArrayList;
import java.util.List;

class ElevatorController {
	private int k; // 0: every floor stop, 1: demand only stop

	private ElevatorManager elevatorManager;
	private ElevatorDoor elevatorDoor;
	private List<FloorDoor> floorDoors;
	private JavaDoorTimer dt;
	
	private List<Integer> floorstobeVisited = new ArrayList<>();
	private int curFlr = 1;
	private int curDir = 0;
	
	private ControlRoomDisplay controlRoomDisplay;
	private ElevatorInsideDisplay elevatorInsideDisplay;
	private AbstractFloorDisplay abstractFloorDisplay;
	
	public ElevatorController(int kind, ElevatorManager elevatorMotor,
							  ElevatorDoor elevatorDoor, List<FloorDoor> floorDoors,
							  JavaDoorTimer doorTimer) {
		this.k = kind;
		this.elevatorManager = elevatorMotor;
		this.elevatorDoor = elevatorDoor;
		this.floorDoors = floorDoors;
		this.dt = doorTimer;
		
		if ( doorTimer != null )
			doorTimer.setDoorTimeout(this);
	}
	public void stop() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null
		if ( getCurDir() != 0) {
			elevatorManager.stop();
			setCurDir(0);
		}
		// open doors
		elevatorDoor.open() ;
		floorDoors.get(getCurFlr()).open() ;
		if ( dt != null ) dt.start() ;
	}
	public void goTo(int dst) {
		// elevatorMotor should not be null
		if ( ! floorstobeVisited.contains(dst) )
			floorstobeVisited.add(dst) ;
		
		if ( getCurDir() == 0 ) {	
			int nxtDir;
			if ( floorstobeVisited.isEmpty() ) nxtDir = 0 ;
			
			if ( dst > curFlr ) nxtDir = 1 ;
			else nxtDir =  -1 ;
			if ( nxtDir != 0) {
				elevatorManager.move(getCurFlr(), nxtDir) ;
				setCurDir(nxtDir);
			}
		}
	}
	public void approaching(int flr) {
		// elevatorMotor, elevatorDoor, floorDoors should not be null
		System.out.println("\nApproaching " + flr + "th floor") ;
		setCurFlr(flr) ;
		
		boolean needToStop;
		if ( k == 0 )
			needToStop = true;
		else
			needToStop = getFloorstobeVisited().contains(flr);
			
		if ( needToStop ) {
			elevatorManager.stop() ;
			setCurDir(0);
			
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurFlr()).open() ;
			if ( dt != null ) dt.start() ;
			
			floorstobeVisited.remove(flr) ;
		}
	}
	public void doorTimeout() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null

		int nxtDir;
		if ( floorstobeVisited.isEmpty() ) nxtDir = 0 ;
		
		final int dst = floorstobeVisited.get(0) ;
		if ( dst > curFlr ) nxtDir = 1 ;
		else nxtDir =  -1 ;
		
		elevatorDoor.close() ;
		floorDoors.get(getCurFlr()).close() ;
		if ( dt != null ) dt.stop() ;
		
		if ( nxtDir != 0 ) {
			elevatorManager.move(getCurFlr(), nxtDir) ;
			setCurDir(nxtDir);
		}
	}
	public void openDoor() {
		// elevatorDoor, floorDoors should not be null

		if ( getCurDir() == 0  ) {
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurFlr()).open() ;
			if ( dt != null ) dt.start() ;
		}
	}
	public void closeDoor() {
		// elevatorDoor, floorDoors should not be null
		if ( getCurDir() == 0 ) {
			// closeDoor
			elevatorDoor.close() ;
			floorDoors.get(getCurFlr()).close() ;
			if ( dt != null ) dt.stop() ;
		}
	}
	public List<Integer> getFloorstobeVisited() {
		return floorstobeVisited;
	}
	public DoorStatus getDrSts(int floor) {
		// elevatorDoor, floorDoors should not be null
		
		DoorStatus elevatorDS = elevatorDoor.getDoorStatus();
		DoorStatus floorDS = floorDoors.get(floor).getDoorStatus();
		
		DoorStatus DS = DoorStatus.OPEN;
		if ( elevatorDS == DoorStatus.CLOSED && floorDS == DoorStatus.CLOSED )
			DS = DoorStatus.CLOSED;
		
		return DS;
	}
	public int getCurFlr() {
		return curFlr ;
	}
	public void setCurFlr(int curFlr) {
		this.curFlr = curFlr;
		
		controlRoomDisplay.update();
		elevatorInsideDisplay.update();
		abstractFloorDisplay.update();
	}
	public int getCurDir() {
		return curDir;
	}
	public void setCurDir(int curDir) {
		this.curDir = curDir;
		
		controlRoomDisplay.update();
		elevatorInsideDisplay.update();
		abstractFloorDisplay.update();
	}
	public ControlRoomDisplay getControlRoomDisplay() {
		return controlRoomDisplay;
	}
	public void setControlRoomDisplay(ControlRoomDisplay controlRoomDisplay) {
		this.controlRoomDisplay = controlRoomDisplay;
	}
	public ElevatorInsideDisplay getElevatorInsideDisplay() {
		return elevatorInsideDisplay;
	}
	public void setElevatorInsideDisplay(ElevatorInsideDisplay elevatorInsideDisplay) {
		this.elevatorInsideDisplay = elevatorInsideDisplay;
	}
	public AbstractFloorDisplay getAbstractFloorDisplay() {
		return abstractFloorDisplay;
	}
	public void setAbstractFloorDisplay(AbstractFloorDisplay abstractFloorDisplay) {
		this.abstractFloorDisplay = abstractFloorDisplay;
	}
}