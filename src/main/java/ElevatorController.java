import java.util.ArrayList;
import java.util.List;



class ElevatorController {
	private int k; // 0: every floor stop, 1: demand only stop

	private ElevatorManager elevatorManager;
	private ElevatorDoor elevatorDoor;
	private List<FloorDoor> floorDoors;
	private JavaDoorTimer doorTimer;
	private List<Floor> floorstobeVisited = new ArrayList<>();
	private Floor currentFloor = new Floor(1);
	private Direction currentDirection = Direction.STOP;
	
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
		this.doorTimer = doorTimer;
		
		if ( doorTimer != null )
			doorTimer.setDoorTimeout(this);
	}
	public void stop() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null
		if ( getCurrentDirection() != Direction.STOP) {
			elevatorManager.stop();
			setCurrentDirection(Direction.STOP);
		}
		// open doors
		elevatorDoor.open() ;
		floorDoors.get(getCurrentFloorToInt()).open() ;
		if ( doorTimer != null ) doorTimer.start() ;
	}
	public void goTo(Floor dst) {
		// elevatorMotor should not be null
		if ( ! floorstobeVisited.contains(dst) )
			floorstobeVisited.add(dst) ;
		
		if ( getCurrentDirection() == Direction.STOP ) {
			Direction nxtDir;
			if ( floorstobeVisited.isEmpty() ) nxtDir = Direction.STOP ;
			
			if ( dst.isHigherThan(currentFloor) ) nxtDir = Direction.UP;
			else nxtDir =  Direction.DOWN;
			if ( nxtDir != Direction.STOP) {

				elevatorManager.move(getCurrentFloor(), nxtDir) ;
				setCurrentDirection(nxtDir);
			}
		}
	}
	public void approaching(Floor flr) {
		// elevatorMotor, elevatorDoor, floorDoors should not be null
		System.out.println("\nApproaching " + flr + "th floor") ;
		setCurrentFloor(flr) ;
		
		boolean needToStop;
		if ( k == 0 )
			needToStop = true;
		else
			needToStop = getFloorstobeVisited().contains(flr);
			
		if ( needToStop ) {
			elevatorManager.stop() ;
			setCurrentDirection(Direction.STOP);
			
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurrentFloorToInt()).open() ;
			if ( doorTimer != null ) doorTimer.start() ;
			
			floorstobeVisited.remove(flr) ;
		}
	}
	public void doorTimeout() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null

		Direction nxtDir;
		if ( floorstobeVisited.isEmpty() ) nxtDir = Direction.STOP;
		
		final Floor dst = floorstobeVisited.get(0) ;
		if ( dst.isHigherThan(currentFloor) ) nxtDir = Direction.UP ;
		else nxtDir =  Direction.DOWN ;
		
		elevatorDoor.close() ;
		floorDoors.get(getCurrentFloorToInt()).close() ;
		if ( doorTimer != null ) doorTimer.stop() ;
		
		if ( nxtDir != Direction.STOP ) {
			elevatorManager.move(getCurrentFloor(), nxtDir) ;
			setCurrentDirection(nxtDir);
		}
	}
	public void openDoor() {
		// elevatorDoor, floorDoors should not be null

		if ( getCurrentDirection() == Direction.STOP  ) {
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurrentFloorToInt()).open() ;
			if ( doorTimer != null ) doorTimer.start() ;
		}
	}
	public void closeDoor() {
		// elevatorDoor, floorDoors should not be null
		if ( getCurrentDirection() == Direction.STOP ) {
			// closeDoor
			elevatorDoor.close() ;
			floorDoors.get(getCurrentFloorToInt()).close() ;
			if ( doorTimer != null ) doorTimer.stop() ;
		}
	}
	public List<Floor> getFloorstobeVisited() {
		return floorstobeVisited;
	}
	public DoorStatus getDoorStatus(Floor floor) {
		// elevatorDoor, floorDoors should not be null
		
		DoorStatus elevatorDoorState = elevatorDoor.getDoorStatus();
		DoorStatus floorDS = floorDoors.get(getCurrentFloorToInt()).getDoorStatus();
		
		DoorStatus DS = DoorStatus.OPEN;
		if ( elevatorDoorState == DoorStatus.CLOSED && floorDS == DoorStatus.CLOSED )
			DS = DoorStatus.CLOSED;
		
		return DS;
	}
	public Floor getCurrentFloor() {
		return currentFloor;
	}
	public int getCurrentFloorToInt() {
		return currentFloor.getValue() ;
	}


	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
		
		controlRoomDisplay.update();
		elevatorInsideDisplay.update();
		abstractFloorDisplay.update();
	}
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
		
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