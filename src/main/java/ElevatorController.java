import java.util.ArrayList;
import java.util.List;

class ElevatorController implements DisplaySubject, IDoorTimeout {
	private final ElevatorControllerKind kind; // 0: every floor stop, 1: demand only stop

	private final ElevatorMotor elevatorMotor;
	private final DoorController doorController ;
	private List<Floor> floorsToBeVisited = new ArrayList<>();
	private Floor currentFloor = new Floor(1);
	private Direction currentDirection = Direction.IDLE;

	private List<DisplayObserver> displayObservers = new ArrayList<>();
	
	public ElevatorController(ElevatorControllerKind kind, ElevatorMotor elevatorMotor,
							  DoorController doorController) {
		if (kind == null || elevatorMotor == null || doorController == null)
			throw new IllegalArgumentException("Controller 생성에 필요한 부품이 없습니다!");

		this.kind = kind;
		this.elevatorMotor = elevatorMotor;
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
		if (isNewDestination(destination))
			addDestination(destination);
		
		if (isElevatorMoving()) return;
			
		if ( hasNextDestination() ) {
			moveElevator(determineMovingDirection());
		}
			
	}

	private boolean isNewDestination(Floor destination) {
		return ! floorsToBeVisited.contains(destination);
		}

	private boolean addDestination(Floor destination) {
		return floorsToBeVisited.add(destination);
		}

	private boolean hasNextDestination() {
		return determineMovingDirection() != Direction.IDLE;
		}

	private Direction determineMovingDirection() {
		// introduce explaining variable
		final boolean noMoreDestinationFloors = floorsToBeVisited.isEmpty();
		if ( noMoreDestinationFloors ) return Direction.IDLE ;
		final Floor destination = floorsToBeVisited.get(0) ;
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
	 		elevatorMotor.stop();
	 		setCurrentDirection(Direction.IDLE);
	}

	private void moveElevator(Direction nextDirection) {
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
		else needToStop = getFloorsToBeVisited().contains(floor);
		return needToStop;
	}
	
	private boolean removeDestination(Floor floor) {
		return floorsToBeVisited.remove(floor);
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
	public List<Floor> getFloorsToBeVisited() {
		return floorsToBeVisited;
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

	public boolean isOpenedAt(Floor currentFloor) {
		return doorController.isOpenedAt(currentFloor);
	}
}