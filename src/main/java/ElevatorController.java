import java.util.ArrayList;
import java.util.List;

class ElevatorController implements DisplaySubject, IDoorTimeout {
	private ElevatorControllerKind kind; // 0: every floor stop, 1: demand only stop

	private ElevatorMotor elevatorMotor;
//	private ElevatorDoor elevatorDoor;
//	private List<FloorDoor> floorDoors;
//	private DoorTimer doorTimer;
	private DoorController doorController ;
	private List<Floor> floorstobeVisited = new ArrayList<>();
	private Floor currentFloor = new Floor(1);
	private Direction currentDirection = Direction.IDLE;

	private List<DisplayObserver> displayObservers;
	
	public ElevatorController(ElevatorControllerKind kind, ElevatorMotor elevatorMotor,
							  DoorController doorController) {
		this.kind = kind;
		this.elevatorMotor = elevatorMotor;
//		this.elevatorDoor = elevatorDoor;
//		this.floorDoors = floorDoors;
//		this.doorTimer = doorTimer;
		this.doorController = doorController;
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

	@Override
	public void attach(DisplayObserver observer) {
		displayObservers.add(observer);
	}

	@Override
	public void detach(DisplayObserver observer) {
		displayObservers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (DisplayObserver observer : displayObservers) {
			observer.update();
		}
	}

	//version 1
	private void stopElevator() {
	 		// introduce assertion
	 		assert elevatorMotor != null;
	 		elevatorMotor.stop() ;
	 		setCurrentDirection(Direction.IDLE);
	}

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

	private void doApproaching(Floor floor) {
		System.out.println("\nApproaching " + floor + "th floor") ;
		setCurrentFloor(floor) ;
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
		
	public void openDoor() {
		if ( getCurrentDirection() == Direction.IDLE  ) {
			doorController.openDoor(currentFloor);
		}
	}
	public void closeDoor() {
		if ( getCurrentDirection() == Direction.IDLE ) {
			doorController.closeDoor(currentFloor);
		}
	}
	public List<Floor> getFloorstobeVisited() {
		return floorstobeVisited;
	}
	public DoorStatus getDoorStatus(Floor floor) {
		return doorController.getDoorStatus(floor);
	}

	public Floor getCurrentFloor() {
		return currentFloor;
	}
	public int getCurrentFloorToInt() {
		return currentFloor.getValue() ;
	}


	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
		
		notifyObservers();
	}
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
		
		notifyObservers();
	}

	@Override
	public void doorTimeout() {
		closeDoor();
		if ( hasNextDestination() )
			moveElevator(determineMovingDirection());
	}
}