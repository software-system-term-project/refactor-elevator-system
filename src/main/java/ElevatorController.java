import java.util.ArrayList;
import java.util.List;

class ElevatorController {
	private int k; // 0: every floor stop, 1: demand only stop

	private ElevatorManager elevatorManager;
	private ElevatorDoor elevatorDoor;
	private List<FloorDoor> floorDoors;
	private JavaDoorTimer dt;
	
	private List<Floor> floorstobeVisited = new ArrayList<>();
	private Floor curFlr = new Floor(1);
	private Direction curDir = Direction.;
	
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
		if ( getCurDir() != Direction.STOP) {
			elevatorManager.stop();
			setCurDir(Direction.STOP);
		}
		// open doors
		elevatorDoor.open() ;
		floorDoors.get(getCurFlr().getValue()).open() ;
		if ( dt != null ) dt.start() ;
	}
	public void goTo(Floor dst) {
		// elevatorMotor should not be null
		if ( ! floorstobeVisited.contains(dst) )
			floorstobeVisited.add(dst) ;
		
		if ( getCurDir() == Direction.STOP ) {
			Direction nxtDir;
			if ( floorstobeVisited.isEmpty() ) nxtDir = Direction.STOP ;
			
			if ( dst.isHigherThan(curFlr) ) nxtDir = Direction.UP;
			else nxtDir =  Direction.DOWN;
			if ( nxtDir != Direction.STOP) {

				elevatorManager.move(getCurFlr(), nxtDir) ;
				setCurDir(nxtDir);
			}
		}
	}
	public void approaching(Floor flr) {
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
			setCurDir(Direction.STOP);
			
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurFlr().getValue()).open() ;
			if ( dt != null ) dt.start() ;
			
			floorstobeVisited.remove(flr) ;
		}
	}
	public void doorTimeout() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null

		Direction nxtDir;
		if ( floorstobeVisited.isEmpty() ) nxtDir = Direction.STOP;
		
		final Floor dst = floorstobeVisited.get(0) ;
		if ( dst.isHigherThan(curFlr) ) nxtDir = Direction.UP ;
		else nxtDir =  Direction.DOWN ;
		
		elevatorDoor.close() ;
		floorDoors.get(getCurFlr().getValue()).close() ;
		if ( dt != null ) dt.stop() ;
		
		if ( nxtDir != Direction.STOP ) {
			elevatorManager.move(getCurFlr(), nxtDir) ;
			setCurDir(nxtDir);
		}
	}
	public void openDoor() {
		// elevatorDoor, floorDoors should not be null

		if ( getCurDir() == Direction.STOP  ) {
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurFlr().getValue()).open() ;
			if ( dt != null ) dt.start() ;
		}
	}
	public void closeDoor() {
		// elevatorDoor, floorDoors should not be null
		if ( getCurDir() == Direction.STOP ) {
			// closeDoor
			elevatorDoor.close() ;
			floorDoors.get(getCurFlr().getValue()).close() ;
			if ( dt != null ) dt.stop() ;
		}
	}
	public List<Floor> getFloorstobeVisited() {
		return floorstobeVisited;
	}
	public DoorStatus getDrSts(Floor floor) {
		// elevatorDoor, floorDoors should not be null
		
		DoorStatus elevatorDS = elevatorDoor.getDoorStatus();
		DoorStatus floorDS = floorDoors.get(floor.getValue()).getDoorStatus();
		
		DoorStatus DS = DoorStatus.OPEN;
		if ( elevatorDS == DoorStatus.CLOSED && floorDS == DoorStatus.CLOSED )
			DS = DoorStatus.CLOSED;
		
		return DS;
	}
	public Floor getCurFlr() {
		return curFlr ;
	}
	public void setCurFlr(Floor curFlr) {
		this.curFlr = curFlr;
		
		controlRoomDisplay.update();
		elevatorInsideDisplay.update();
		abstractFloorDisplay.update();
	}
	public Direction getCurDir() {
		return curDir;
	}
	public void setCurDir(Direction curDir) {
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