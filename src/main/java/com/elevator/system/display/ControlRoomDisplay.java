package com.elevator.system.display;

import com.elevator.system.controller.ElevatorController;
import com.elevator.system.controller.ElevatorStatusProvider;
import com.elevator.system.util.Floor;

public class ControlRoomDisplay implements DisplayObserver {
	private ElevatorStatusProvider statusProvider;

	public ControlRoomDisplay(ElevatorStatusProvider statusProvider) {
		this.statusProvider = statusProvider;
	}

	@Override
	public void update() {
		Floor currentFloor = statusProvider.getCurrentFloor() ;
		System.out.println("Control Room: " + currentFloor) ;
	}
}
