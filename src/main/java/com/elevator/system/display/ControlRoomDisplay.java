package com.elevator.system.display;

import com.elevator.system.controller.ElevatorController;
import com.elevator.system.util.Floor;

public class ControlRoomDisplay implements DisplayObserver {
	private ElevatorController elevatorController;

	public ControlRoomDisplay(ElevatorController elevatorController) {
		this.elevatorController = elevatorController;
	}

	@Override
	public void update() {
		Floor currentFloor = elevatorController.getCurrentFloor() ;
		System.out.println("Control Room: " + currentFloor) ;
	}
}
