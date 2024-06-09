package com.elevator.system.display;

import com.elevator.system.controller.ElevatorStatusProvider;
import com.elevator.system.util.Floor;
import com.elevator.system.controller.ElevatorController;

public class ElevatorInsideDisplay implements DisplayObserver {
	private ElevatorStatusProvider statusProvider;
	public ElevatorInsideDisplay(ElevatorStatusProvider statusProvider) {
		this.statusProvider = statusProvider ;
	}

	@Override
	public void update() {
		Floor currentFloor = statusProvider.getCurrentFloor();
		System.out.println("Elevator Inside Display: " + currentFloor) ;
	}
}
