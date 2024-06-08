package com.elevator.system;

import com.elevator.system.controller.ElevatorControllersList;
import com.elevator.system.scheduler.ElevatorScheduler;
import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

public class SimpleElevatorManager {
	private final ElevatorControllersList controllers;
	private final ElevatorScheduler scheduler;

	public SimpleElevatorManager(ElevatorControllersList controllers, ElevatorScheduler scheduler) {
		if (controllers == null || controllers.isEmpty() || scheduler == null)
			throw new IllegalArgumentException("ElevatorManager 생성에 필요한 부품이 없습니다!");

		this.controllers = controllers;
		this.scheduler = scheduler;
	}	

	public void requestElevator(Floor destination, Direction direction) {
		int selectedElevator = scheduler.selectElevator(controllers, destination, direction);
		controllers.moveElevator(selectedElevator, destination);
	}

	public boolean isToBeVisitedFloor(Floor floor) {
		return controllers.isToBeVisitedFloor(floor);
	}
	public void print() {
		controllers.print();
	}
}