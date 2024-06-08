package com.elevator.system.display;

import com.elevator.system.util.Direction;
import com.elevator.system.controller.ElevatorController;
import com.elevator.system.util.Floor;
import com.elevator.system.display.implementor.IFloorDisplayImplementor;

public class BasicFloorDisplay extends AbstractFloorDisplay {
	public BasicFloorDisplay(ElevatorController elevatorController,
							 IFloorDisplayImplementor floorDisplayImplementor) {
		super(elevatorController, floorDisplayImplementor);
	}
	
	@Override
	public void showPosition(Floor floor) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showCurrentPosition(floor);
	}
	@Override
	public void showDirection(Direction direction) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showDirection(direction);
	}
}