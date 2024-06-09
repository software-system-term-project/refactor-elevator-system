package com.elevator.system.controller;

import com.elevator.system.display.DisplayObserver;
import com.elevator.system.door.DoorController;
import com.elevator.system.motor.ElevatorMotor;
import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements IDoorTimeout {
	private final ElevatorControllerKind kind; // 0: every floor stop, 1: demand only stop

	private final ElevatorMotor elevatorMotor;
	private final DoorController doorController;
	private final FloorList floorsToBeVisited = new FloorList();
	private final ElevatorStatus elevatorStatus = new ElevatorStatus();

	private DisplayManager displayManager;
	
	public ElevatorController(ElevatorControllerKind kind, ElevatorMotor elevatorMotor,
							  DoorController doorController) {
		if (kind == null || elevatorMotor == null || doorController == null)
			throw new IllegalArgumentException("Controller 생성에 필요한 부품이 없습니다!");

		this.kind = kind;
		this.elevatorMotor = elevatorMotor;
		this.doorController = doorController;
	}

	public void setDisplayManager(DisplayManager displayManager) {
		if (displayManager == null) this.displayManager = displayManager;
	}

	public void stop() {
		if ( isElevatorMoving() ) {
			stopElevator();
		}

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

	public ElevatorStatus getElevatorStatus() {
		return elevatorStatus;
	}

	public void approaching(Floor floor) {
		doApproaching(floor);

		if (!needToStop(floor)) return;

		stopElevator();
		openDoor();

		removeDestination(floor);
	}

	public FloorList getFloorsToBeVisited() {
		return floorsToBeVisited;
	}

	public Floor getCurrentFloor() {
		return elevatorStatus.getCurrentFloor();
	}

	public Direction getCurrentDirection() {
		return elevatorStatus.getCurrentDirection();
	}

	public boolean isNewDestination(Floor destination) {
		return !floorsToBeVisited.contains(destination);
	}

	@Override
	public void doorTimeout() {
		closeDoor();
		if ( hasNextDestination() )
			moveElevator(determineMovingDirection());
	}

	private void setCurrentDirection(Direction currentDirection) {
		elevatorStatus.setCurrentDirection(currentDirection);

		displayManager.notifyObservers();
	}

	private void addDestination(Floor destination) {
		floorsToBeVisited.add(destination);
	}

	private boolean hasNextDestination() {
		return determineMovingDirection() != Direction.IDLE;
	}

	private Direction determineMovingDirection() {
		final boolean noMoreDestinationFloors = floorsToBeVisited.isEmpty();
		if ( noMoreDestinationFloors ) return Direction.IDLE ;
		final Floor destination = floorsToBeVisited.get(0) ;
		if ( destination.isHigherThan(elevatorStatus.getCurrentFloor()) ) return Direction.UP ;
		return Direction.DOWN ;
	}

	private boolean isElevatorMoving() {
		return getCurrentDirection() != Direction.IDLE;
	}


	private void stopElevator() {
		elevatorMotor.stop();
		setCurrentDirection(Direction.IDLE);
	}

	private void moveElevator(Direction nextDirection) {
		elevatorMotor.move(getCurrentFloor(), nextDirection) ;
		setCurrentDirection(nextDirection);
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

	private void setCurrentFloor(Floor currentFloor) {
		elevatorStatus.setCurrentFloor(currentFloor);

		displayManager.notifyObservers();
	}

	private void openDoor() {
		if ( getCurrentDirection() == Direction.IDLE  ) {
			doorController.openDoor(elevatorStatus.getCurrentFloor());
		}
	}

	private void closeDoor() {
		if ( getCurrentDirection() == Direction.IDLE ) {
			doorController.closeDoor(elevatorStatus.getCurrentFloor());
		}
	}
}