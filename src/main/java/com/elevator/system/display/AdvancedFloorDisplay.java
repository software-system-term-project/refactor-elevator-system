package com.elevator.system.display;

import com.elevator.system.controller.ElevatorStatusProvider;
import com.elevator.system.util.Direction;
import com.elevator.system.controller.ElevatorController;
import com.elevator.system.util.Floor;
import com.elevator.system.display.implementor.IFloorDisplayImplementor;

public class AdvancedFloorDisplay extends AbstractFloorDisplay {
	public AdvancedFloorDisplay(ElevatorStatusProvider statusProvider,
								IFloorDisplayImplementor floorDisplayImplementor) {
		super(statusProvider, floorDisplayImplementor);
	}
	@Override
	public void showPosition(Floor floor) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showCurrentPosition(floor);
		floorDisplayImplementor.expressCurrentPositionByVoice(floor);
	}
	@Override
	public void showDirection(Direction direction) {
		IFloorDisplayImplementor floorDisplayImplementor = getFloorDisplayImplementor();
		floorDisplayImplementor.showDirection(direction);
		floorDisplayImplementor.expressDirectionByVoice(direction);
	}
}
