import java.util.ArrayList;
import java.util.List;



class ElevatorController {
	private ElevatorControllerKind kind; // 0: every floor stop, 1: demand only stop

	private ElevatorMotor elevatorMotor;
	private ElevatorDoor elevatorDoor;
	private List<FloorDoor> floorDoors;
	private JavaDoorTimer doorTimer;
	private List<Floor> floorstobeVisited = new ArrayList<>();
	private Floor currentFloor = new Floor(1);
	private Direction currentDirection = Direction.IDLE;
	
	private ControlRoomDisplay controlRoomDisplay;
	private ElevatorInsideDisplay elevatorInsideDisplay;
	private AbstractFloorDisplay abstractFloorDisplay;
	
	public ElevatorController(ElevatorControllerKind kind, ElevatorMotor elevatorMotor,
							  ElevatorDoor elevatorDoor, List<FloorDoor> floorDoors,
							  JavaDoorTimer doorTimer) {
		this.kind = kind;
		this.elevatorMotor = elevatorMotor;
		this.elevatorDoor = elevatorDoor;
		this.floorDoors = floorDoors;
		this.doorTimer = doorTimer;
		
		if ( doorTimer != null )
			doorTimer.setDoorTimeout(this);
	}
	public void stop() {
		// elevatorMotor, elevatorDoor, floorDoors should not be null
		if ( isElevatorMoving() ) {
			stopElevator();
		}

		// open doors
		openDoor();
	}
	public void goTo(Floor destination) {
		// elevatorMotor should not be null
		if ( isNewDestination(destination) )
		addDestination(destination) ;
		
		if ( isElevatorMoving() ) return; 
			
			if ( hasNextDestination() ) {
				moveElevator(determineMovingDirection());
			}
			
	}

	private boolean isNewDestination(Floor destination) {
		return ! floorstobeVisited.contains(destination);
		}

	private boolean addDestination(Floor destination) {
		return floorstobeVisited.add(destination);
		}

	private boolean hasNextDestination() {
		return determineMovingDirection() != Direction.IDLE;
		}

	private Direction determineMovingDirection() {
		// introduce explaining variable
		final boolean noMoreDestinationFloors = floorstobeVisited.isEmpty();
		if ( noMoreDestinationFloors ) return Direction.IDLE ;
		final Floor destination = floorstobeVisited.get(0) ;
		if ( destination.isHigherThan(currentFloor) ) return Direction.UP ;
		return Direction.DOWN ;
		}

	private boolean isElevatorMoving() {
			return getCurrentDirection() != Direction.IDLE;
			}
//version 1
	// private void stopElevator() {
	// 		// introduce assertion
	// 		assert elevatorMotor != null;
	// 		elevatorMotor.stop() ;
	// 		setCurrentDirection(Direction.IDLE);
	// 		}

	// private void doOpenDoor() {
	// 		// introduce assertion
	// 		assert elevatorDoor != null;
	// 		elevatorDoor.open() ;
	// 		openFloorDoor() ;
	// 		if ( hasDoorTimer() ) doorTimer.start();
	// 		}

	// private void openFloorDoor() {
	// 		// introduce assertion
	// 		assert floorDoors.get(getCurrentFloor().getFloor()) != null;
	// 		floorDoors.get(getCurrentFloor().getFloor()).open();
	// 		}

	private void moveElevator(Direction nextDirection) {
		// introduce assertion
		assert elevatorMotor != null;
		elevatorMotor.move(getCurrentFloor(), nextDirection) ;
		setCurrentDirection(nextDirection);
		}

	public void approaching(Floor floor) {

		doApproaching(floor) ;

		if ( ! needToStop(floor) ) return;

		stopElevator();
		openDoor();
		
		removeDestination(floor) ;
	}

	private boolean needToStop(Floor floor) {
		boolean needToStop;
		if ( kind == ElevatorControllerKind.EveryFloorStop ) needToStop = true;
		else needToStop = getFloorstobeVisited().contains(floor);
		return needToStop;
	}
	
	private boolean removeDestination(Floor floor) {
		return floorstobeVisited.remove(floor);
	}
	
	public void doorTimeout() {
		closeDoor();
		if ( hasNextDestination() )
		moveElevator(determineMovingDirection());
		}
		
	public void openDoor() {
		// elevatorDoor, floorDoors should not be null

		if ( getCurrentDirection() == Direction.IDLE  ) {
			// open doors
			elevatorDoor.open() ;
			floorDoors.get(getCurrentFloorToInt()).open() ;
			if ( doorTimer != null ) doorTimer.start() ;
		}
	}
	public void closeDoor() {
		// elevatorDoor, floorDoors should not be null
		if ( getCurrentDirection() == Direction.IDLE ) {
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