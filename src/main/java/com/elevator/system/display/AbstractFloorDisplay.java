package com.elevator.system.display;


import com.elevator.system.controller.ElevatorController;
import com.elevator.system.controller.ElevatorStatusProvider;
import com.elevator.system.display.implementor.IFloorDisplayImplementor;
import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

public abstract class AbstractFloorDisplay implements DisplayObserver {
	protected ElevatorStatusProvider statusProvider;
	private IFloorDisplayImplementor floorDisplayImplementor;
	
	protected AbstractFloorDisplay(ElevatorStatusProvider statusProvider,
								   IFloorDisplayImplementor floorDisplayModule) {
		this.statusProvider = statusProvider;
		this.floorDisplayImplementor = floorDisplayModule;
	}

	@Override
	public void update() {
		Floor currentFloor = statusProvider.getCurrentFloor() ;
		Direction currentDirection = statusProvider.getCurrentDirection();
		showPosition(currentFloor);
		showDirection(currentDirection);
	}
	public void displayOn() { floorDisplayImplementor.activateDisplay(); }
	public void displayOff() { floorDisplayImplementor.deactivateDisplay(); }

	public void activateDisplay() {
		floorDisplayImplementor.activateDisplay();
	}

	public void deactivateDisplay() {
		floorDisplayImplementor.deactivateDisplay();
	}

	protected IFloorDisplayImplementor getFloorDisplayImplementor() { return floorDisplayImplementor; }

	protected abstract void showPosition(Floor floor);
	protected abstract void showDirection(Direction dir);
}
